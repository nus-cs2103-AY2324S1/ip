import java.util.ArrayList;

public class Ace {
    private static Task[] toDoList = new Task[100];
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
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tracker; i++) {
            if (toDoList[i] != null) {
                output += (i + 1) + "." + toDoList[i].printTask() + "\n";
            }
        }
        return output;
    }

    private String addTask(String task) {
        toDoList[tracker] = new Task(task);
        tracker++;
        return "added: " + task;
    }

    private String markTask(int index) {
        Task curr = toDoList[index - 1];
        curr.taskDone();
        return "Nice! I've marked this task as done:\n" + "\t" + curr.printTask();
    }

    private String unmarkTask(int index) {
        Task curr = toDoList[index - 1];
        curr.taskUndone();
        return "OK, I've marked this task as not done yet:\n" + "\t" + curr.printTask();
    }

    public String sendMessage(String keyWord, String details) {
        switch(keyWord) {
            case "start":
                return addLine(greet());
            case "bye":
                return addLine(goodbye());
            case "list":
                return addLine(printList());
            case "mark":
                return addLine(markTask(Integer.parseInt(details)));
            case "unmark":
                return addLine(unmarkTask(Integer.parseInt(details)));
            default:
                return addLine(addTask(keyWord + " " + details));
        }
    }
}
