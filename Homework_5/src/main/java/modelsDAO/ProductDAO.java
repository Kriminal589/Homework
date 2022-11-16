package modelsDAO;

import commons.DAO;
import models.PositionInvoice;
import models.Product;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"NotNullNullableValidation", "SqlNoDataSourceInspection", "SqlResolve"})
public final class ProductDAO implements DAO<Product> {

    private final @NotNull Connection connection;

    public ProductDAO(@NotNull Connection connection) {
        this.connection = connection;
    }

    @NotNull
    @Override
    public Product get(int id) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product WHERE id = " + id)) {
            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("code")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Record with id " + id + "not found");
    }

    @Override
    public @NotNull List<Product> all() {
        final List<Product> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product")) {
            while (resultSet.next()) {
                result.add(
                        new Product(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("code")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void save(@NotNull Product entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO product(id, name, code) " +
                        "VALUES(?,?,?)")) {
            preparedStatement.setInt(1, entity.id());
            preparedStatement.setString(2, entity.name());
            preparedStatement.setInt(3, entity.code());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(@NotNull Product entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE product " +
                        "SET name = ?, code = ? " +
                        "WHERE id = ?"
        )){
            preparedStatement.setString(1, entity.name());
            preparedStatement.setInt(2, entity.code());
            preparedStatement.setInt(3, entity.id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(@NotNull Product entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product " +
                "WHERE id = ?")) {
            preparedStatement.setInt(1, entity.id());

            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + entity.id() + " not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
