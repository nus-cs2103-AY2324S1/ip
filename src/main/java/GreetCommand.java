import java.util.stream.Stream;

public class GreetCommand extends Command {
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.respond(Stream.of("Hello! I'm A-CAT (Automated Chatbot Assistant for Tasks)", "What do you want to do today?"));
    }
}
