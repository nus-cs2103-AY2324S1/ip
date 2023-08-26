package duke.message;

public class WelcomeMessage extends Message {
    @Override
    public void send() {
        String hiMessage = "Hello! I'm ChatGPA 5.0\nWhat can I do for you?";
        System.out.println(createMessage(horizontalLine, hiMessage, horizontalLine));
    }
}
