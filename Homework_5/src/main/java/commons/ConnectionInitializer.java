package commons;

import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;

public class ConnectionInitializer {

    private final @NotNull DBInfo CREDS;

    public ConnectionInitializer(@NotNull DBInfo CREDS) {
        this.CREDS = CREDS;
    }

    public void initDB() {
        final Flyway flyway = Flyway
                .configure()
                .dataSource(
                        CREDS.url(),
                        CREDS.login(),
                        CREDS.password()
                )
                .locations("db")
                .load();
        flyway.migrate();
    }
}
