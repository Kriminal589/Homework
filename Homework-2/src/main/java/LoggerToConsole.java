import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerToConsole extends Application {
    @Override
    void log(Scanner scanner) {
        try(FileInputStream config = new FileInputStream(
                Objects.requireNonNull(Main.class.getResource("consoleLogger.config")).getFile())) {
            System.out.println("Console log. Waiting for new lines. Key in 'q' to exit.");
            LogManager.getLogManager().readConfiguration(config);
            logger = Logger.getLogger(Main.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            String line = scanner.nextLine();
            if (line.equals("q")) {
                break;
            }

            logger.info(line + " " + (++N));
        }
    }
}