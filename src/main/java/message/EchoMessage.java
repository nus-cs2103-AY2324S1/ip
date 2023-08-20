package message;

public class EchoMessage extends Message {
    private final String echo;
    public EchoMessage(String echo) {
        this.echo = echo;
    }
    @Override
    public void send() {
        System.out.println(createMessage(echo, horizontalLine));
    }
}
