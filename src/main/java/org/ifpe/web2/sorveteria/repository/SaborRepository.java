package org.ifpe.web2.sorveteria.repository;

import org.ifpe.web2.sorveteria.model.entity.Sabor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class SaborRepository implements Repository<Sabor> {

    @Override
    public void create(Sabor sabor) throws SQLException {
        String sql = "INSERT INTO tb_sabor (nome, descricao) VALUES (?, ?)";

        try (Connection conn = ConnectionManager.getNewConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, sabor.getNome());
            statement.setString(2, sabor.getDescricao());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    sabor.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to retrieve auto-generated ID.");
                }
            }
        }
    }

    @Override
    public void update(Sabor sabor) throws SQLException {
        String sql = "UPDATE tb_sabor SET nome = ?, descricao = ? WHERE id = ?";

        try (Connection conn = ConnectionManager.getNewConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, sabor.getNome());
            statement.setString(2, sabor.getDescricao());
            statement.setInt(3, sabor.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Nenhum registro atualizado. ID não encontrado: " + sabor.getId());
            }
        }
    }

    @Override
    public Sabor read(int codigo) throws SQLException {
        String sql = "SELECT id, nome, descricao FROM tb_sabor WHERE id = ?";

        try (Connection conn = ConnectionManager.getNewConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, codigo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractSaborFromResultSet(resultSet);
                } else {
                    return null; // Nenhum resultado encontrado
                }
            }
        }
    }

    @Override
    public void delete(int codigo) throws SQLException {
        String sql = "DELETE FROM tb_sabor WHERE id = ?";

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
    public List<Sabor> readAll() throws SQLException {
        String sql = "SELECT id, nome, descricao FROM tb_sabor";

        try (Connection conn = ConnectionManager.getNewConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Sabor> sabores = new ArrayList<>();

            while (resultSet.next()) {
                sabores.add(extractSaborFromResultSet(resultSet));
            }

            return sabores;
        }
    }

    private Sabor extractSaborFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        String descricao = resultSet.getString("descricao");

        return new Sabor(id, nome, descricao);
    }
}
