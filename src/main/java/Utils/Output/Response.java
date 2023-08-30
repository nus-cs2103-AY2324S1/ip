package Utils.Output;

import java.util.ArrayList;

public abstract class Response {
    protected ArrayList<String> messageList;
    protected static final String LINE = "____________________________________________________________";

    public Response(ArrayList<String> messageList) {
        this.messageList = messageList;
    }

    public void print() {
        for (String message : messageList) {
            System.out.println(message);
        }
    }
}
