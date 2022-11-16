package modelsDAO;

import commons.DAO;
import models.Invoice;
import models.Organization;
import models.PositionInvoice;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"NotNullNullableValidation", "SqlNoDataSourceInspection", "SqlResolve"})
public final class PositionInvoiceDAO implements DAO<PositionInvoice> {

    private final @NotNull Connection connection;

    public PositionInvoiceDAO(@NotNull Connection connection) {
        this.connection = connection;
    }

    @NotNull
    @Override
    public PositionInvoice get(int id) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM position_invoice WHERE id = " + id)) {
            if (resultSet.next()) {
                return new PositionInvoice(
                    resultSet.getInt("id"),
                    resultSet.getInt("invoice_id"),
                    resultSet.getInt("product_id"),
                    resultSet.getInt("price"),
                    resultSet.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Record with id " + id + "not found");
    }

    @Override
    public @NotNull List<PositionInvoice> all() {
        final List<PositionInvoice> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM position_invoice")) {
            while (resultSet.next()) {
                result.add(
                        new PositionInvoice(
                            resultSet.getInt("id"),
                            resultSet.getInt("invoice_id"),
                            resultSet.getInt("product_id"),
                            resultSet.getInt("price"),
                            resultSet.getInt("count"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void save(@NotNull PositionInvoice entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO position_invoice(id, invoice_id, product_id, price, count) " +
                        "VALUES(?,?,?,?,?)")) {
            preparedStatement.setInt(1, entity.id());
            preparedStatement.setInt(2, entity.invoice_id());
            preparedStatement.setInt(3, entity.product_id());
            preparedStatement.setInt(4, entity.price());
            preparedStatement.setInt(5, entity.count());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(@NotNull PositionInvoice entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE position_invoice " +
                        "SET invoice_id = ?, product_id = ?, price = ?, count = ? " +
                        "WHERE id = ?"
        )){
            preparedStatement.setInt(1, entity.invoice_id());
            preparedStatement.setInt(2, entity.product_id());
            preparedStatement.setInt(3, entity.price());
            preparedStatement.setInt(4, entity.count());
            preparedStatement.setInt(5, entity.id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(@NotNull PositionInvoice entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM position_invoice " +
                "WHERE id = ?")) {
            preparedStatement.setInt(1, entity.id());

            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + entity.id() + " not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public @NotNull List<PositionInvoice> getAllByIdInvoice(int idInvoice) {
        List<PositionInvoice> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM position_invoice " +
                     "WHERE invoice_id = " + idInvoice)) {

            while (resultSet.next()) {
                result.add(new PositionInvoice(
                        resultSet.getInt("id"),
                        resultSet.getInt("invoice_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("price"),
                        resultSet.getInt("count")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
