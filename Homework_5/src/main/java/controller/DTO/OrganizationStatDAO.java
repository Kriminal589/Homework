package controller.DTO;

import models.Organization;
import models.PositionInvoice;
import models.Product;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrganizationStatDAO {
    private final @NotNull Organization organization;
    private final List<Product> products;

    public OrganizationStatDAO(@NotNull Organization organization, List<Product> products) {
        this.organization = organization;
        this.products = products;
    }
}
