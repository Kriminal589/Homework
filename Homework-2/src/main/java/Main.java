import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;


public class Main {
    public static void main(String @NotNull [] args) {
        final Injector injector = Guice.createInjector(new InjectionModule(args[0]));
        injector.getInstance(BaseClass.class).waitForInput();
    }
}
