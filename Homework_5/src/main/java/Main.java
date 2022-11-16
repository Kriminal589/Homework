import commons.ConnectionInitializer;
import commons.DBInfo;
import controller.Controller;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Scanner;

public class Main {


    public static void main(@NotNull String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите адрес БД вместе с названием (Например: ://127.0.0.1:5434/test).");
        String url = scanner.next();
        System.out.print("login: ");
        String login = scanner.next();
        System.out.print("password: ");
        String password = scanner.next();

        DBInfo CREDS = new DBInfo(url, login, password);

        ConnectionInitializer connectionInitializer = new ConnectionInitializer(CREDS);
        connectionInitializer.initDB();

        try (Connection connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
            Controller controller = new Controller(connection);

            help();
            int n = 0;
            String start;
            String end;

            while (n != 6) {
                n = scanner.nextInt();

                switch (n) {
                    case 1:
                        System.out.println(controller.bestTen());
                        break;
                    case 2:
                        System.out.print("Введите кол-во товара: ");
                        n = scanner.nextInt();
                        System.out.println(controller.rating(n));
                        break;
                    case 3:
                        System.out.println("Введите период в формате 'YYYY-MM-DD'. Помните, что дата вводиться не включительно" +
                                "То есть если вы хотите получить статистику за 15 число, то нужно ввести 14)");
                        start = scanner.next();
                        end = scanner.next();
                        System.out.println(controller.statistics(start, end));
                        break;
                    case 4:
                        System.out.println("Введите период в формате 'YYYY-MM-DD'. Помните, что дата вводиться не включительно" +
                                "То есть если вы хотите получить статистику за 15 число, то нужно ввести 14)");
                        start = scanner.next();
                        end = scanner.next();
                        System.out.println(controller.averageCost(start, end));
                        break;
                    case 5:
                        System.out.println("Введите период в формате 'YYYY-MM-DD'. Помните, что дата вводиться не включительно" +
                                "То есть если вы хотите получить статистику за 15 число, то нужно ввести 14)");
                        start = scanner.next();
                        end = scanner.next();
                        System.out.println(controller.organizationStat(start, end));
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Вы ввели неправильную цифру запроса. Возможно вам стоит напомнить все запросы.");
                        help();
                }

                System.out.print("Введите следующий запрос: ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void help() {
        System.out.println("""
                    Введите цифру одного из запросов:\s
                    1 - Вывод первых 10 поставщиков по количеству поставленного товара;
                    2 - Выбор поставщиков с количеством поставленного товара выше указанного значения;
                    3 - За каждый день для каждого товара рассчитать количество и сумму полученного товара в указанном периоде, посчитать итоги за период;
                    4 - Рассчитать среднюю цену по каждому товару за период;
                    5 - Вывести список товаров, поставленных организациями за период;
                    6 - Прекратить ввод.
                    """);
    }
}
