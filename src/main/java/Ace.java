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
                output += (i + 1) + "." + toDoList[i].toString() + "\n";
            }
        }
        return output;
    }

    private String addTask(String name) {
        toDoList[tracker] = new Task(name);
        tracker++;
        return "added: " + name;
    }

    private String markTask(int index) {
        Task curr = toDoList[index - 1];
        curr.taskDone();
        return "Nice! I've marked this task as done:\n" + "\t" + curr.toString();
    }

    private String unmarkTask(int index) {
        Task curr = toDoList[index - 1];
        curr.taskUndone();
        return "OK, I've marked this task as not done yet:\n" + "\t" + curr.toString();
    }

    private String addTodo(String name) {
        toDoList[tracker] = new Todo(name);
        Task curr = toDoList[tracker];
        tracker++;
        return "Got it. I've added this task:\n" + "\t" + curr.toString() + "\n"
                + "Now you have " + Integer.toString(tracker) + " tasks in the list";
    }

    private String addDeadline(String name, String time) {
        toDoList[tracker] = new Deadline(name, time);
        Task curr = toDoList[tracker];
        tracker++;
        return "Got it. I've added this task:\n" + "\t" + curr.toString() + "\n"
                + "Now you have " + Integer.toString(tracker) + " tasks in the list";
    }

    private String addEvent(String name, String start, String end) {
        toDoList[tracker] = new Event(name, start, end);
        Task curr = toDoList[tracker];
        tracker++;
        return "Got it. I've added this task:\n" + "\t" + curr.toString() + "\n"
                + "Now you have " + Integer.toString(tracker) + " tasks in the list";
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
            case "todo":
                return addLine(addTodo(details));
            case "deadline":
                String[] partDeadline = details.split("/by");
                return addLine(addDeadline(partDeadline[0], partDeadline[1]));
            case "event":
                String[] partFrom = details.split("/from");
                String[] partTo = partFrom[1].split("/to");
                return addLine(addEvent(partFrom[0], partTo[0], partTo[1]));
            default:
                return addLine(addTask(keyWord + " " + details));
        }
    }
}
