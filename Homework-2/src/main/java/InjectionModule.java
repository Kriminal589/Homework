import com.google.inject.AbstractModule;
import org.jetbrains.annotations.NotNull;


public class InjectionModule extends AbstractModule {
    private final String[] type;
    InjectionModule(@NotNull String[] type) {
        this.type = type;
    }

    @Override
    protected void configure() {
        if (type[0].equals("File")) {
            bind(BaseClass.class).toInstance(new LoggerToFile(type[1]));
        } else if (type[0].equals("Console")) {
            bind(BaseClass.class).to(LoggerToConsole.class);
        } else {
            bind(BaseClass.class).toInstance(new LoggerComposite(type[1]));
        }
    }
}
