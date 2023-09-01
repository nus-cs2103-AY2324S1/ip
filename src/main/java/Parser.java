import java.util.HashMap;
import java.util.Map;

public class Parser {

    @FunctionalInterface
    interface CheckedConsumer<T> {
        void accept(T t) throws Exception;
    }

    private Map<String, CheckedConsumer<String[]>> commandHandlers = new HashMap<>();

    public void registerHandler(String command, CheckedConsumer<String[]> handler) {
        this.commandHandlers.put(command, handler);
    }

    public void handleCommand(String input) throws Exception {
        String[] tokens = input.split(" ");
        CheckedConsumer<String[]> cmdHandler = this.commandHandlers.get(tokens[0]);
        if (cmdHandler != null) {
            cmdHandler.accept(tokens);
        } else {
            throw new DeterministicParrotException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
