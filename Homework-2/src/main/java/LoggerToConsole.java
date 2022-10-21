import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public class LoggerToConsole implements BaseClass {
    int N = 0;

    @Override
    public void waitForInput() {
        Logger logger = Logger.getLogger(LoggerToConsole.class.getName());

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Console log. Waiting for new lines. Key in 'q' to exit.");
            while (true) {
                String line = scanner.nextLine();

                if (Objects.equals(line, "q")) {
                    break;
                }
                N++;
                logger.info(line + " " + N);
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
