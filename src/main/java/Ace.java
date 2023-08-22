import java.util.ArrayList;

public class Ace {
    private String addLine(String message) {
        String horizontal = "_____________________________________________________\n";
        return horizontal + message + "\n" + horizontal;
    }

    private String greet() {
        return "Hello! I'm Ace\nWhat can I do for you?";
    }

    private String goodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public String sendMessage(String keyWord) {
        switch(keyWord) {
            case "start":
                return addLine(greet());
            case "bye":
                return addLine(goodbye());
            default:
                return addLine((keyWord));
        }
    }
}
