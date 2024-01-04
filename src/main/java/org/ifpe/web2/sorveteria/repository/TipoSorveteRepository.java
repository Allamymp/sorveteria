package org.ifpe.web2.sorveteria.repository;

import org.ifpe.web2.sorveteria.model.entity.TipoSorvete;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class TipoSorveteRepository implements Repository<TipoSorvete> {

    @Override
    public void create(TipoSorvete tipoSorvete) throws SQLException {
        String sql = "INSERT INTO tb_tiposorvete (tipo, quantBolas, peso, descricao, valor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getNewConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, tipoSorvete.getTipo());
            statement.setInt(2, tipoSorvete.getQuantBolas());
            statement.setDouble(3, tipoSorvete.getPeso());
            statement.setString(4, tipoSorvete.getDescricao());
            statement.setDouble(5, tipoSorvete.getValor());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tipoSorvete.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to retrieve auto-generated ID.");
                }
            }
        }
    }

    @Override
    public void update(TipoSorvete tipoSorvete) throws SQLException {
        String sql = "UPDATE  tb_tipoSorvete SET tipo = ?, quantbolas = ?, peso = ?, descricao = ?, valor = ? WHERE id = ?";

        try (Connection conn = ConnectionManager.getNewConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, tipoSorvete.getTipo());
            statement.setInt(2, tipoSorvete.getQuantBolas());
            statement.setDouble(3, tipoSorvete.getPeso());
            statement.setString(4, tipoSorvete.getDescricao());
            statement.setDouble(5, tipoSorvete.getValor());
            statement.setInt(6, tipoSorvete.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Nenhum registro atualizado. ID não encontrado: " + tipoSorvete.getId());
            }
        }
    }

    @Override
    public TipoSorvete read(int codigo) throws SQLException {
        String sql = "SELECT id, tipo, quantbolas, peso, descricao, valor FROM  tb_tipoSorvete WHERE id = ?";

        try (Connection conn = ConnectionManager.getNewConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, codigo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractTipoSorveteFromResultSet(resultSet);
                } else {
                    return null; // Nenhum resultado encontrado
                }
            }
        }
    }

    @Override
    public void delete(int codigo) throws SQLException {
        String sql = "DELETE FROM  tb_tipoSorvete WHERE id = ?";

        try (Connection conn = ConnectionManager.getNewConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, codigo);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted == 0) {
                throw new SQLException("Nenhum registro excluído. ID não encontrado: " + codigo);
            }
        }
    }

    @Override
    public List<TipoSorvete> readAll() throws SQLException {
        String sql = "SELECT id, tipo, quantbolas, peso, descricao, valor FROM tb_tipoSorvete";

        try (Connection conn = ConnectionManager.getNewConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<TipoSorvete> tiposSorvete = new ArrayList<>();

            while (resultSet.next()) {
                tiposSorvete.add(extractTipoSorveteFromResultSet(resultSet));
            }

            return tiposSorvete;
        }
    }

    private TipoSorvete extractTipoSorveteFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String tipo = resultSet.getString("tipo");
        int quantBolas = resultSet.getInt("quantbolas");
        double peso = resultSet.getDouble("peso");
        String descricao = resultSet.getString("descricao");
        double valor = resultSet.getDouble("valor");

        return new TipoSorvete(id, tipo, quantBolas, peso, descricao, valor);
    }
}
