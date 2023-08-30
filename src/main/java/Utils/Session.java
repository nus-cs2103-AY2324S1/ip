package Utils;

public class Session {
    private final GreetingResponse GREETINGS = new GreetingResponse();
    private Response response;
    private Input input;

    public Session() {}

    public void start() {
        GREETINGS.print();
        this.input = new Input();
        while (this.input.isActive()) {
            this.input.readCommand();
            this.response = this.input.executeCommand();
            this.response.print();
        }
    }
}
