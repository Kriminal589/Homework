import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerComposite extends Application {
    private final String tag;

    public LoggerComposite(String tag) {
        this.tag = tag;
    }

    @Override
    void log(Scanner scanner) {
        try (FileWriter writer = new FileWriter("logs.txt", false);
             FileInputStream config = new FileInputStream(
                     Objects.requireNonNull(Main.class.getResource("consoleLogger.config")).getFile())) {
            System.out.println("Composite log. Waiting for new lines. Key in 'q' to exit.");
            LogManager.getLogManager().readConfiguration(config);
            logger = Logger.getLogger(Main.class.getName());
            while (true) {
                String line = scanner.nextLine();
                if (line.equals("q")) {
                    break;
                }

                logger.info(tag + line + " " + (++N) + tag);
                writer.write(tag + line + " " + (N + 1) + tag);
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}