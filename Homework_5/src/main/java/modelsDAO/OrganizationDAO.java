package modelsDAO;

import commons.DAO;
import models.Organization;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"NotNullNullableValidation", "SqlNoDataSourceInspection", "SqlResolve"})
public final class OrganizationDAO implements DAO<Organization> {

    private final @NotNull Connection connection;

    public OrganizationDAO(@NotNull Connection connection) {
        this.connection = connection;
    }

    @NotNull
    @Override
    public Organization get(int id) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM organization WHERE id = " + id)) {
            if (resultSet.next()) {
                return new Organization(resultSet.getInt("id"), resultSet.getLong("inn"),
                        resultSet.getLong("checking_account"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Record with id " + id + "not found");
    }

    @Override
    public @NotNull List<Organization> all() {
        final List<Organization> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM organization")) {
            while (resultSet.next()) {
                result.add(
                        new Organization(resultSet.getInt("id"), resultSet.getLong("inn"),
                                resultSet.getLong("checking_account"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void save(@NotNull Organization entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO organization(id, inn, checking_account) VALUES(?,?,?)")) {
            preparedStatement.setInt(1, entity.id());
            preparedStatement.setLong(2, entity.inn());
            preparedStatement.setLong(3, entity.checking_account());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(@NotNull Organization entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE organization SET inn = ?, checking_account = ? WHERE id = ?"
        )){
            preparedStatement.setLong(1, entity.inn());
            preparedStatement.setLong(2, entity.checking_account());
            preparedStatement.setInt(3, entity.id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(@NotNull Organization entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM organization WHERE id = ?")) {
            preparedStatement.setInt(1, entity.id());

            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + entity.id() + " not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
