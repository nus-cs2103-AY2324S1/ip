package duke.message;

public class ByeMessage extends Message {
    @Override
    public void send() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(createMessage(byeMessage, horizontalLine));
    }
}
