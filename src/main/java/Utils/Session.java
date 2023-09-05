package Utils;

public class Session {
    private Response response;
    private Input input;

    public Session() {}

    public void start() {
        print(Response.GREETINGS);
        this.input = new Input();
        while (this.response != Response.TERMINATE) {
            this.response = this.input.command();
            print(this.response);
        }
    }

    private void print(Response r) {
        System.out.println(r);
    }
}
