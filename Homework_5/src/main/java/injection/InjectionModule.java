package injection;

import com.google.inject.AbstractModule;
import commons.DAO;
import modelsDAO.InvoiceDAO;
import modelsDAO.OrganizationDAO;
import modelsDAO.PositionInvoiceDAO;
import modelsDAO.ProductDAO;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;

public class InjectionModule extends AbstractModule {
    private final @NotNull Connection connection;

    public InjectionModule(@NotNull Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void configure() {
        bind(DAO.class).toInstance(new ProductDAO(connection));
        bind(DAO.class).toInstance(new OrganizationDAO(connection));
        bind(DAO.class).toInstance(new InvoiceDAO(connection));
        bind(DAO.class).toInstance(new PositionInvoiceDAO(connection));
    }
}
