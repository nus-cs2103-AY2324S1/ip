import java.util.function.Consumer;
public abstract class Command implements Consumer<Parser> {
    public abstract void accept(Parser input);
}
