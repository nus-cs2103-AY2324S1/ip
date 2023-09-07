package duke.Utils;

/**
 * The Session class represents a user session in the Duke application.
 * It manages user input and responses during the session.
 */
public class Session {
    private Response response;
    private Input input;

    /**
     * Constructs a new Session object.
     */
    public Session() {}

    /**
     * Starts the user session, initializing it with a greeting message
     * and processing user commands until termination.
     */
    public void start() {
        print(Response.GREETINGS);
        this.input = new Input();
        while (this.response != Response.TERMINATE) {
            this.response = this.input.command();
            print(this.response);
        }
    }
    
    /**
     * Prints a response to the console.
     *
     * @param r The response to be printed.
     */
    private void print(Response r) {
        System.out.println(r);
    }
}
