import java.util.List;

public class Message {
    final private static String horizontalLine = "____________________________________________________________";
    final private String content;

    public Message(String content) {
        this.content = content;
    }

    public static Message GenerateGreeting(String name) {
        return new Message("Hello, I'm " + name + ".\n" +
                "What can I do for you?");
    }

    public static Message GenerateExit(){
        return new Message("Bye. Hope to see you again soon!");
    }

    public Message ChainTo(Message message, String splitString) {
        return new Message(this.content + splitString + message.content);
    }

    public static Message ChainList(List<? extends Message> messages, String splitString){
        Message chainedMessage = new Message("");
        for (Message message : messages)
        {
            chainedMessage = chainedMessage.ChainTo(message, splitString);
        }
        return chainedMessage;
    }

    public void Print() {
        System.out.println(horizontalLine);
        System.out.println(this.ToString());
        System.out.println(horizontalLine);
    }
    public String ToString() {
        return content;
    }

}
