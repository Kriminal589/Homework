import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public class LoggerComposite implements BaseClass {
    private final String tag;
    private int N = 0;

    public LoggerComposite(String tag) {
        this.tag = tag;
    }

    @Override
    public void waitForInput() {
        Logger logger = Logger.getLogger(LoggerComposite.class.getName());
        System.out.println("Composite logger. Waiting for new lines. Key in 'q' to exit.");
        try (Scanner scanner = new Scanner(System.in);
             FileWriter writer = new FileWriter(
                     Objects.requireNonNull(Main.class.getResource("logs.txt")).getFile(), false)
        ) {
            while (true) {
                String line = scanner.nextLine();
                if (line.equals("q")) {
                    break;
                }

                writer.write(tag + line + " " + (++N + 1) + tag);
                writer.append('\n');
                logger.info(line + " " + N);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
