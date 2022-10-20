import lombok.Data;

@Data
public class Author {
    private String email;
    private final String name;

    public Author(String name) {
        this.name = name;
    }
}
