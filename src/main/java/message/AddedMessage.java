package message;

public class AddedMessage extends Message {
    private final String echo;
    public AddedMessage(String echo) {
        this.echo = "added: " + echo;
    }
    @Override
    public void send() {
        System.out.println(createMessage(echo, horizontalLine));
    }
}
