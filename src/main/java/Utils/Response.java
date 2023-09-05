package Utils;

import java.util.List;

public class Response {
    private String messageOutput;
    private static final String LINE = "    ____________________________________________________________\n";
    private static final String TAB = "    ";
    protected static final Response GREETINGS = Response.greeting();
    protected static final Response TERMINATE = Response.terminate();

    private Response() {
        this.messageOutput = "";
    }

    private void add(String message) {
        this.messageOutput += Response.TAB + message + "\n";
    }

    protected static Response generate(String message) {
        Response response = new Response();
        response.add(message);
        return response;
    }

    protected static Response generate(String[] messageArray) {
        Response response = new Response();
        for (int i = 0; i < messageArray.length; i++) {
            response.add(messageArray[i]);
        }
        return response;
    }

    protected static Response generate(List<String> messageList) {
        Response response = new Response();
        for (String s : messageList) {
            response.add(s);
        }
        return response;
    }

    protected static Response greeting() {
        Response response = new Response();
        response.add("Hello! I'm Duke");
        response.add("What can I do for you?");
        return response;
    }

    protected static Response terminate() {
        Response response = new Response();
        response.add("Bye. Hope to see you again soon!");
        return response;
    }

    @Override
    public String toString() {
        return Response.LINE + this.messageOutput + Response.LINE;
    }
}
