package modelsDAO;

import commons.DAO;
import models.Invoice;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"NotNullNullableValidation", "SqlNoDataSourceInspection", "SqlResolve"})
public final class InvoiceDAO implements DAO<Invoice> {

    private final @NotNull Connection connection;

    public InvoiceDAO(@NotNull Connection connection) {
        this.connection = connection;
    }

    @NotNull
    @Override
    public Invoice get(int id) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM invoice WHERE id = " + id)) {
            if (resultSet.next()) {
                return new Invoice(resultSet.getInt("id"),
                        resultSet.getInt("organization_id"),
                        resultSet.getDate("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Record with id " + id + "not found");
    }

    @Override
    public @NotNull List<Invoice> all() {
        final List<Invoice> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM invoice")) {
            while (resultSet.next()) {
                result.add(
                        new Invoice(resultSet.getInt("id"),
                                resultSet.getInt("organization_id"),
                                resultSet.getDate("date"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void save(@NotNull Invoice entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO invoice(id, organization_id, date) VALUES(?,?,?)")) {
            preparedStatement.setInt(1, entity.id());
            preparedStatement.setInt(2, entity.organization_id());
            preparedStatement.setDate(3, entity.date());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(@NotNull Invoice entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE invoice SET organization_id = ?, date = ? WHERE id = ?"
        )){
            preparedStatement.setInt(1, entity.organization_id());
            preparedStatement.setDate(2, entity.date());
            preparedStatement.setInt(3, entity.id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(@NotNull Invoice entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM invoice WHERE id = ?")) {
            preparedStatement.setInt(1, entity.id());

            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + entity.id() + " not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public @NotNull List<Invoice> getAllByIdOrganization(int idOrganization) {
        List<Invoice> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM invoice WHERE organization_id = " + idOrganization)) {

            while (resultSet.next()) {
                result.add(new Invoice(
                        resultSet.getInt("id"),
                        resultSet.getInt("organization_id"),
                        resultSet.getDate("date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public @NotNull List<Invoice> getAllByPeriod(String start, String end) {
        List<Invoice> result = new ArrayList<>();
        String query = "SELECT * FROM invoice " +
                "WHERE date < '" + end + "' and date > '" + start + "'";

        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                result.add(
                        new Invoice(resultSet.getInt("id"),
                                resultSet.getInt("organization_id"),
                                resultSet.getDate("date"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public @NotNull List<Invoice> getAllByIdOrganizationAndDate(int idOrganization, String start, String end) {
        List<Invoice> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM invoice " +
                     "WHERE organization_id = " + idOrganization + " and date > '" + start + "' and " +
                     "date < '" + end + "'")) {
            while (resultSet.next()) {
                result.add(new Invoice(
                        resultSet.getInt("id"),
                        resultSet.getInt("organization_id"),
                        resultSet.getDate("date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
