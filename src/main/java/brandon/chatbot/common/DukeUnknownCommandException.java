package brandon.chatbot.common;
public class DukeUnknownCommandException extends DukeException {

    public DukeUnknownCommandException() {
        super("    I am not intelligent enough to understand what that means...\n--------------------------------");
    }
}

