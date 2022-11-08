import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Library implements FactoryLibrary {
    ArrayList<Book> books;
    public Library(BufferedReader bufferedReader) throws IOException {
        books = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null){
            String[] l = line.split(" ", 3);
            Author author = new Author(l[0] + " " + l[1]);
            Book book = new Book(l[2], author);
            books.add(book);
        }
    }

    @Override
    public void find(Author author) {
        for (Book book : books){
            if (Objects.equals(book.author().name(), author.name())){
                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();
                System.out.println(gson.toJson(book));
            }
        }
    }
}
