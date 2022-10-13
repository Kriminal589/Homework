import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FactoryLibrary library = new Library(new BufferedReader(
                new FileReader("/Users/AndreyNovikov/Desktop/Информатика/sorted.txt")
        ));
        System.out.print("Введите имя автора: ");
        String name = scanner.nextLine();
        Author author = new Author(name);
        library.find(author);
    }
}
