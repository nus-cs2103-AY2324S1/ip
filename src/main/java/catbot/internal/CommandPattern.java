package catbot.internal;

import java.util.function.Consumer;

public abstract class CommandPattern<T> {

    protected Consumer<String> invalidCommandInput;

    public abstract void ifParsableElseDefault(String args, Consumer<T> consumer);

}
