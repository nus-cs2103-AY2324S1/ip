import java.util.ArrayList;

public class Ace {
    private static String[] toDoList = new String[100];
    private static int tracker = 0;

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

    private String printList() {
        String output = "";
        for (int i = 0; i < tracker; i++) {
            if (!toDoList[i].isEmpty()) {
                output += (i + 1) + ". " + toDoList[i] + "\n";
            }
        }
        return output;
    }

    private String addTask(String task) {
        toDoList[tracker] = task;
        tracker++;
        return "added: " + task;
    }

    public String sendMessage(String keyWord) {
        switch(keyWord) {
            case "start":
                return addLine(greet());
            case "bye":
                return addLine(goodbye());
            case "list":
                return addLine(printList());
            default:
                return addLine(addTask(keyWord));
        }
    }
}
