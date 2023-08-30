package Utils.Output;

import java.util.ArrayList;

public class GreetingResponse extends Response {
    public GreetingResponse() {
        super(new ArrayList<>());

        this.messageList.add(Response.LINE);
        this.messageList.add("Hello! I'm Duke");
        this.messageList.add("What can I do for you?");
        this.messageList.add(Response.LINE);
        this.messageList.add("Bye. Hope to see you again soon!");
        this.messageList.add(Response.LINE);
    }
}


