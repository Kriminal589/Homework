package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.DTO.AverageCostDTO;
import controller.DTO.BestsOrganization;
import controller.DTO.OrganizationStatDAO;
import controller.DTO.ProductStatistics;
import models.Invoice;
import models.Organization;
import models.PositionInvoice;
import models.Product;
import modelsDAO.InvoiceDAO;
import modelsDAO.OrganizationDAO;
import modelsDAO.PositionInvoiceDAO;
import modelsDAO.ProductDAO;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final static Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private final @NotNull Connection connection;
    private final @NotNull ProductDAO productDAO;
    private final @NotNull InvoiceDAO invoiceDAO;
    private final @NotNull PositionInvoiceDAO positionInvoiceDAO;
    private final @NotNull OrganizationDAO organizationDAO;


    public Controller(@NotNull Connection connection) {
        this.connection = connection;
        productDAO = new ProductDAO(connection);
        invoiceDAO = new InvoiceDAO(connection);
        positionInvoiceDAO = new PositionInvoiceDAO(connection);
        organizationDAO = new OrganizationDAO(connection);
    }

    public String bestTen() {
        List<BestsOrganization> result = new ArrayList<>();
        List<Organization> allOrganization = organizationDAO.all();
        int index = 0;

        for (Organization organization : allOrganization) {
            int count = getCount(organization);

            if (index < 10) {
                index++;
                result.add(new BestsOrganization(organization, count));
                sort(result);
            } else {
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).count < count) {
                        swap(result, new BestsOrganization(organization, count), i);
                        break;
                    }
                }
            }
        }

        return GSON.toJson(result);
    }

    public String rating(int min) {
        List<Organization> result = new ArrayList<>();
        List<Organization> allOrganization = organizationDAO.all();

        for (Organization organization : allOrganization) {
            int count = getCount(organization);

            if (count >= min) {
                result.add(organization);
            }
        }

        return GSON.toJson(result);
    }

    private int getCount(@NotNull Organization organization) {
        int count = 0;

        List<Invoice> invoices = invoiceDAO.getAllByIdOrganization(organization.id());
        for (Invoice invoice : invoices) {
            List<PositionInvoice> positionInvoiceList = positionInvoiceDAO.getAllByIdInvoice(invoice.id());
            for (PositionInvoice positionInvoice : positionInvoiceList) {
                count += positionInvoice.count();
            }
        }

        return count;
    }

    private void sort(@NotNull List<BestsOrganization> organizations) {
        for (int i = 0; i < organizations.size() - 1; i++) {
            for (int j = i + 1; j < organizations.size(); j++) {
                if (organizations.get(i).count < organizations.get(j).count) {
                    BestsOrganization tmp = organizations.get(i);
                    organizations.set(i, organizations.get(j));
                    organizations.set(j, tmp);
                }
            }
        }
    }

    private void swap(@NotNull List<BestsOrganization> organizations, @NotNull BestsOrganization organization, int index) {
        organizations.add(index, organization);
        organizations.remove(organizations.size() - 1);
    }

    public String statistics(String start, String end) {
        List<ProductStatistics> result = getStats(start, end);
        return GSON.toJson(result);
    }

    private List<ProductStatistics> getStats(String start, String end) {
        List<ProductStatistics> result = new ArrayList<>();

        List<Invoice> invoices = invoiceDAO.getAllByPeriod(start, end);

        for (Invoice invoice : invoices) {

            List<PositionInvoice> positionInvoiceList = positionInvoiceDAO.getAllByIdInvoice(invoice.id());
            for (PositionInvoice positionInvoice : positionInvoiceList) {
                boolean find = false;
                Product product = productDAO.get(positionInvoice.product_id());

                for (ProductStatistics productStat:result) {
                    if (productStat.getCode() == product.code() && productStat.getDate().equals(invoice.date())) {
                        productStat.setCount(positionInvoice.count());
                        productStat.setCost(positionInvoice.price());
                        find = true;
                        break;
                    }
                }

                if (!find) {
                    result.add(new ProductStatistics(
                            product.name(),
                            invoice.date(),
                            positionInvoice.count(),
                            product.code(),
                            positionInvoice.price()
                    ));
                }

            }
        }

        for (ProductStatistics product:result) {
            product.setResult();
        }

        return result;
    }

    public String averageCost(String start, String end) {
        List<ProductStatistics> productStatistics = getStats(start, end);
        List<AverageCostDTO> result = new ArrayList<>();

        for (ProductStatistics product : productStatistics) {
            result.add(new AverageCostDTO(
                    product.getName(),
                    product.getCode(),

                    //Будет очень небольшое значение, из-за того, что в insert стоит небольшая стоимость заказа
                    (float)product.getCost() / product.getCount()
            ));
        }

        return GSON.toJson(result);
    }

    public String organizationStat(String start, String end) {
        List<OrganizationStatDAO> result = new ArrayList<>();
        List<Organization> organizations = organizationDAO.all();

        for (Organization organization : organizations) {
            List<Product> products = new ArrayList<>();
            List<Invoice> invoices = invoiceDAO.getAllByIdOrganizationAndDate(organization.id(), start, end);

            for (Invoice invoice : invoices) {
                List<PositionInvoice> positionInvoiceList = positionInvoiceDAO.getAllByIdInvoice(invoice.id());
                boolean find = false;

                for (PositionInvoice positionInvoice : positionInvoiceList) {
                    for (Product product : products) {
                        if (product.id() == positionInvoice.product_id()) {
                            find = true;
                            break;
                        }
                    }

                    if (!find) {
                        products.add(productDAO.get(positionInvoice.product_id()));
                    }
                }
            }

            result.add(new OrganizationStatDAO(
                    organization,
                    products
            ));
        }

        return GSON.toJson(result);
    }
}
