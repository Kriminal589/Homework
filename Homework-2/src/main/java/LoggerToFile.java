import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public final class LoggerToFile implements BaseClass {
    private final String tag;

    public LoggerToFile(@NotNull String tag) {
        this.tag = tag;
    }
    @Override
    public void waitForInput() {
        int N = 0;

        try (Scanner scanner = new Scanner(System.in);
             FileWriter writer = new FileWriter(
                Objects.requireNonNull(Main.class.getResource("logs.txt")).getFile(), false)
        ) {
            System.out.println("Log to File. Waiting for new lines. Key in 'q' to exit.");
            while (true) {
                String line = scanner.nextLine();
                if (line.equals("q")) {
                    break;
                }

                writer.write(tag + line + " " + ++N + tag);
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
