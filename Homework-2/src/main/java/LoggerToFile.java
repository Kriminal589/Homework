import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public final class LoggerToFile extends Application {
    private final String tag;

    public LoggerToFile(@NotNull String tag) {
        this.tag = tag;
    }

    @Override
    void log(Scanner scanner) {
        try (FileWriter writer = new FileWriter("logs.txt", false)) {
            System.out.println("File log. Waiting for new lines. Key in 'q' to exit.");
            while (true) {
                String line = scanner.nextLine();
                if (line.equals("q")) {
                    break;
                }

                writer.write(tag + line + " " + (++N) + tag);
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}