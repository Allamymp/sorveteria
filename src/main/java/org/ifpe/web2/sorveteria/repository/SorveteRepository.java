package org.ifpe.web2.sorveteria.repository;

import org.ifpe.web2.sorveteria.model.entity.Sabor;
import org.ifpe.web2.sorveteria.model.entity.Sorvete;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class SorveteRepository implements Repository<Sorvete> {
    @Autowired
    TipoSorveteRepository tipoSorveteRepository;

  /*  public void create(Sorvete sorvete) throws SQLException {
        String insertSorveteSQL = "INSERT INTO tb_sorvete (data_compra, tipo_sorvete_id) VALUES (?, ?)";
        String insertSorveteSaborSQL = "INSERT INTO public.tb_sorvete_sabor (sorvete_id, sabor_id) VALUES (?, ?)";

        try (Connection conn = ConnectionManager.getNewConnection();
             PreparedStatement sorveteStatement = conn.prepareStatement(insertSorveteSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement sorveteSaborStatement = conn.prepareStatement(insertSorveteSaborSQL)) {

            // Inserir dados básicos do Sorvete
            sorveteStatement.setDate(1, Date.valueOf(LocalDate.now()));
            sorveteStatement.setInt(2, sorvete.getTipoSorvete().getId());

            int rowsAffectedSorvete = sorveteStatement.executeUpdate();

            if (rowsAffectedSorvete > 0) {
                // Recuperar o ID gerado para o Sorvete
                try (ResultSet generatedKeys = sorveteStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int sorveteId = generatedKeys.getInt(1);

                        // Inserir dados na tabela de relacionamento sorvete_sabor
                        for (Sabor sabor : sorvete.getSabores()) {
                            sorveteSaborStatement.setInt(1, sorveteId);
                            sorveteSaborStatement.setInt(2, sabor.getId());
                            sorveteSaborStatement.executeUpdate();
                        }
                    }
                }
            } else {
                throw new SQLException("Falha ao inserir Sorvete. Nenhum registro afetado.");
            }
        }
    } */


    public void create(Sorvete sorvete) throws SQLException {
        // Obter o número de bolas permitido para o tipo de sorvete
        int numBolasPermitido = tipoSorveteRepository.read(sorvete.getTipoSorvete().getId()).getQuantBolas();

        String insertSorveteSQL = "INSERT INTO tb_sorvete (data_compra, tipo_sorvete_id) VALUES (?, ?)";
        String insertSorveteSaborSQL = "INSERT INTO public.tb_sorvete_sabor (sorvete_id, sabor_id, quantidade) VALUES (?, ?, 1) " +
                "ON CONFLICT (sorvete_id, sabor_id) DO UPDATE SET quantidade = tb_sorvete_sabor.quantidade + 1";

        try (Connection conn = ConnectionManager.getNewConnection();
             PreparedStatement sorveteStatement = conn.prepareStatement(insertSorveteSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement sorveteSaborStatement = conn.prepareStatement(insertSorveteSaborSQL)) {

            // Inserir dados  do Sorvete
            sorveteStatement.setDate(1, Date.valueOf(LocalDate.now()));
            sorveteStatement.setInt(2, sorvete.getTipoSorvete().getId());

            int rowsAffectedSorvete = sorveteStatement.executeUpdate();

            if (rowsAffectedSorvete > 0) {
                // Recuperar o ID gerado para o Sorvete
                try (ResultSet generatedKeys = sorveteStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int sorveteId = generatedKeys.getInt(1);

                        // Inserir dados na tabela de relacionamento sorvete_sabor
                        for (int i = 0; i < numBolasPermitido && i < sorvete.getSabores().size(); i++) {
                            Sabor sabor = sorvete.getSabores().get(i);
                            sorveteSaborStatement.setInt(1, sorveteId);
                            sorveteSaborStatement.setInt(2, sabor.getId());
                            sorveteSaborStatement.executeUpdate();
                        }
                    }
                }
            } else {
                throw new SQLException("Falha ao inserir Sorvete. Nenhum registro afetado.");
            }
        }
    }


    @Override
    public void update(Sorvete sorvete) throws SQLException {
        String updateSorveteSQL = "UPDATE tb_sorvete SET data_compra = ?, tipo_sorvete_id = ? WHERE id = ?";
        String deleteSorveteSaborSQL = "DELETE FROM tb_sorvete_sabor WHERE sorvete_id = ?";
        String insertSorveteSaborSQL = "INSERT INTO tb_sorvete_sabor (sorvete_id, sabor_id) VALUES (?, ?)";

        try (Connection conn = ConnectionManager.getNewConnection();
             PreparedStatement updateSorveteStatement = conn.prepareStatement(updateSorveteSQL);
             PreparedStatement deleteSorveteSaborStatement = conn.prepareStatement(deleteSorveteSaborSQL);
             PreparedStatement insertSorveteSaborStatement = conn.prepareStatement(insertSorveteSaborSQL)) {

            // Atualizar dados básicos do Sorvete
            updateSorveteStatement.setDate(1, Date.valueOf(LocalDate.now()));
            updateSorveteStatement.setInt(2, sorvete.getTipoSorvete().getId());
            updateSorveteStatement.setInt(3, sorvete.getId());

            int rowsAffectedSorvete = updateSorveteStatement.executeUpdate();

            if (rowsAffectedSorvete > 0) {
                // Deletar registros existentes na tabela de relacionamento sorvete_sabor
                deleteSorveteSaborStatement.setInt(1, sorvete.getId());
                deleteSorveteSaborStatement.executeUpdate();

                // Inserir dados atualizados na tabela de relacionamento sorvete_sabor
                for (Sabor sabor : sorvete.getSabores()) {
                    insertSorveteSaborStatement.setInt(1, sorvete.getId());
                    insertSorveteSaborStatement.setInt(2, sabor.getId());
                    insertSorveteSaborStatement.executeUpdate();
                }
            } else {
                throw new SQLException("Falha ao atualizar Sorvete. Nenhum registro afetado.");
            }
        }
    }

    @Override
    public Sorvete read(int codigo) throws SQLException {
        String selectSorveteSQL = "SELECT s.id AS id, s.data_compra, ts.id AS tipo_sorvete_id, ts.tipo " +
                "FROM tb_sorvete s " +
                "JOIN public.tb_tiposorvete ts ON s.tipo_sorvete_id = ts.id " +
                "WHERE s.id = ?";
        String selectSaboresSQL = "SELECT sa.id AS sabor_id, sa.nome, sa.descricao " +
                "FROM tb_sorvete_sabor ss " +
                "JOIN tb_sabor sa ON ss.sabor_id = sa.id " +
                "WHERE ss.sorvete_id = ?";

        try (Connection conn = ConnectionManager.getNewConnection();
             PreparedStatement selectSorveteStatement = conn.prepareStatement(selectSorveteSQL);
             PreparedStatement selectSaboresStatement = conn.prepareStatement(selectSaboresSQL)) {

            // Consulta para recuperar dados básicos do sorvete
            selectSorveteStatement.setInt(1, codigo);
            try (ResultSet sorveteResultSet = selectSorveteStatement.executeQuery()) {
                if (sorveteResultSet.next()) {
                    Sorvete sorvete = extractSorveteFromResultSet(sorveteResultSet);

                    // Consulta para recuperar sabores associados ao sorvete
                    selectSaboresStatement.setInt(1, sorvete.getId());
                    try (ResultSet saboresResultSet = selectSaboresStatement.executeQuery()) {
                        List<Sabor> sabores = new ArrayList<>();
                        while (saboresResultSet.next()) {
                            sabores.add(extractSaborFromResultSet(saboresResultSet));
                        }
                        sorvete.setSabores(sabores);

                        return sorvete;
                    }
                } else {
                    return null; // Nenhum resultado encontrado
                }
            }
        }
    }

    private Sorvete extractSorveteFromResultSet(ResultSet resultSet) throws SQLException {
        int sorveteId = resultSet.getInt("id");
        LocalDate dataCompra = resultSet.getDate("data_compra").toLocalDate();
        int tipoSorveteId = resultSet.getInt("tipo_sorvete_id");

        // Consulta para recuperar sabores associados ao sorvete
        List<Sabor> sabores = getSaboresBySorveteId(sorveteId);

        return new Sorvete(sorveteId, dataCompra, tipoSorveteRepository.read(tipoSorveteId), sabores);
    }

    private Sabor extractSaborFromResultSet(ResultSet resultSet) throws SQLException {
        int saborId = resultSet.getInt("sabor_id");
        String nome = resultSet.getString("nome");
        String descricao = resultSet.getString("descricao");

        return new Sabor(saborId, nome, descricao);
    }


    @Override
    public void delete(int codigo) throws SQLException {
        String deleteSaborSql = "DELETE FROM tb_sorvete_sabor WHERE sorvete_id = ?";
        String deleteSorveteSql = "DELETE FROM tb_sorvete WHERE id = ?";

        try (Connection conn = ConnectionManager.getNewConnection()) {
            // Excluir registros da tabela de relacionamento
            try (PreparedStatement deleteSaborStatement = conn.prepareStatement(deleteSaborSql)) {
                deleteSaborStatement.setInt(1, codigo);
                deleteSaborStatement.executeUpdate();
            }

            // Excluir o sorvete
            try (PreparedStatement deleteSorveteStatement = conn.prepareStatement(deleteSorveteSql)) {
                deleteSorveteStatement.setInt(1, codigo);
                int rowsDeleted = deleteSorveteStatement.executeUpdate();

                if (rowsDeleted == 0) {
                    throw new SQLException("Nenhum registro excluído. ID não encontrado: " + codigo);
                }
            }
        }
    }


    @Override
    public List<Sorvete> readAll() throws SQLException {
        String sql = "SELECT id, data_compra, tipo_sorvete_id FROM tb_sorvete";

        try (Connection conn = ConnectionManager.getNewConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Sorvete> sorvetes = new ArrayList<>();

            while (resultSet.next()) {
                sorvetes.add(extractSorveteFromResultSet(resultSet));
            }

            return sorvetes;
        }
    }

    private List<Sabor> getSaboresBySorveteId(int sorveteId) throws SQLException {
        String selectSaboresSQL = "SELECT sa.id AS sabor_id, sa.nome, sa.descricao " +
                "FROM tb_sorvete_sabor ss " +
                "JOIN tb_sabor sa ON ss.sabor_id = sa.id " +
                "WHERE ss.sorvete_id = ?";

        try (Connection conn = ConnectionManager.getNewConnection();
             PreparedStatement selectSaboresStatement = conn.prepareStatement(selectSaboresSQL)) {

            // Consulta para recuperar sabores associados ao sorvete
            selectSaboresStatement.setInt(1, sorveteId);
            try (ResultSet saboresResultSet = selectSaboresStatement.executeQuery()) {
                List<Sabor> sabores = new ArrayList<>();
                while (saboresResultSet.next()) {
                    sabores.add(extractSaborFromResultSet(saboresResultSet));
                }
                return sabores;
            }
        }
    }
}

