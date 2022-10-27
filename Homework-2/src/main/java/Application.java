import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

abstract class Application {
    protected Logger logger = null;
    protected int N = 0;

    void waitForInput(){
        try (Scanner scanner = new Scanner(System.in)) {
            log(scanner);
        } catch (IllegalStateException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    abstract void log(Scanner scanner);
}