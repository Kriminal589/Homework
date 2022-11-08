import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Flyway flyway = initialization(scanner);
        flyway.migrate();
    }

    private static Flyway initialization(@NotNull Scanner scanner) throws Exception {
        try {
            String connection, userName, password;
            connection = "jdbc:postgresql://";

            System.out.println("Введите адрес БД с именем. Пример: 127.0.0.1:5433/db1");
            connection += scanner.nextLine();

            System.out.print("Введите логин: ");
            userName = scanner.nextLine();

            System.out.print("Введите пароль от " + userName + ": ");
            password = scanner.nextLine();

            return Flyway
                    .configure()
                    .dataSource(connection, userName, password)
                    .locations("db")
                    .load();
        } catch (Exception e) {
            throw new Exception();
        }

    }
}
