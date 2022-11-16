package commons;

import org.jetbrains.annotations.NotNull;

public class DBInfo {
    private static final @NotNull String PREFIX = "jdbc:postgresql";

    private final @NotNull String address;
    private final @NotNull String login;
    private final @NotNull String password;

    public DBInfo(@NotNull String address, @NotNull String login,
                  @NotNull String password) {
        this.address = address;
        this.login = login;
        this.password = password;
    }



    public @NotNull String url() {
        return PREFIX + address;
    }

    public @NotNull String login() {
        return login;
    }

    public @NotNull String password() {
        return password;
    }
}
