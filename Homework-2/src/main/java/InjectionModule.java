import com.google.inject.AbstractModule;

import java.util.Objects;


public class InjectionModule extends AbstractModule {
    private final String type;
    InjectionModule(String type) {
        this.type = type;
    }

    @Override
    protected void configure() {
        if (Objects.equals(type, "File")) {
            bind(BaseClass.class).to(LoggerToFile.class);
        } else if (Objects.equals(type, "Console")) {
            bind(BaseClass.class).to(LoggerToConsole.class);
        } else {
            bind(BaseClass.class).to(LoggerComposite.class);
        }
    }
}
