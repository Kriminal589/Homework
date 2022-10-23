import com.google.inject.AbstractModule;
import org.jetbrains.annotations.NotNull;


public class InjectionModule extends AbstractModule {
    private final String[] type;
    InjectionModule(@NotNull String[] type) {
        this.type = type;
    }

    @Override
    protected void configure() {
        if ("File".equals(type[0])) {
            bind(Application.class).toInstance(new LoggerToFile(type[1]));
        } else if ("Console".equals(type[0])) {
            bind(Application.class).to(LoggerToConsole.class);
        } else {
            bind(Application.class).toInstance(new LoggerComposite(type[1]));
        }
    }
}