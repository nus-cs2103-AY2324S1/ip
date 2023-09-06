package catbot.internal;

import java.util.function.Consumer;

public interface CommandPatternGenerator<T> {
    public CommandPattern<T> generate(Consumer<String> invalidInput);
}
