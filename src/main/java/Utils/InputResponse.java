package Utils;

import java.util.ArrayList;

public class InputResponse extends Response {
    private String command;

    public InputResponse(String command) {
        super();
        this.command = command;
        this.add(command);
    }
}
