package Utils;

import java.util.ArrayList;

public abstract class Response {
    private ArrayList<String> messageList;
    private static final String LINE = "____________________________________________________________";
    private static final String TAB = "    ";

    public Response() {
        this.messageList = new ArrayList<>();
    }

    protected void add(String message) {
        messageList.add(Response.TAB + message);
    }

    public void print() {
        System.out.println(Response.TAB + Response.LINE);
        for (String message : messageList) {
            System.out.println(message);
        }
        System.out.println(Response.TAB + Response.LINE);
        System.out.println("");
    }
}
