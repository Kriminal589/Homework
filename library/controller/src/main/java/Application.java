import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FactoryLibrary library = new Library(new BufferedReader(
                new FileReader(
                        Objects.requireNonNull(Application.class.getResource("library.txt")).getFile()
                )
        ));
        System.out.print("Введите имя автора: ");
        String name = scanner.nextLine();
        Author author = new Author(name);
        library.find(author);
    }
}
