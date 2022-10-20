import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class LoggerToConsole implements BaseClass {
    int N = 0;

    @Override
    public void waitForInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Waiting for new lines. Key in q to exit.");
            while (true) {
                String line = scanner.nextLine();

                if (Objects.equals(line, "q")) {
                    break;
                }
                N++;
                System.out.println(line + " " + N);

            }
        } catch (IllegalStateException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
