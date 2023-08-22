import java.util.ArrayList;

public class Ace {
    private static ArrayList<Task> toDoList = new ArrayList<>();

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
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i) != null) {
                output += "\n" + (i + 1) + "." + toDoList.get(i).toString();
            }
        }
        return output;
    }

    private String addTask(String name) {
        toDoList.add(new Task(name));
        return "added: " + name;
    }

    private String markTask(int index) {
        Task curr = toDoList.get(index - 1);
        curr.taskDone();
        return "Nice! I've marked this task as done:\n" + "\t" + curr.toString();
    }

    private String unmarkTask(int index) {
        Task curr = toDoList.get(index - 1);
        curr.taskUndone();
        return "OK, I've marked this task as not done yet:\n" + "\t" + curr.toString();
    }

    private String addTodo(String name) {
        Task curr = new Todo(name);
        toDoList.add(curr);
        return "Got it. I've added this task:\n" + "\t" + curr.toString() + "\n"
                + "Now you have " + Integer.toString(toDoList.size()) + " tasks in the list.";
    }

    private String addDeadline(String name, String time) {
        Task curr = new Deadline(name, time);
        toDoList.add(curr);
        return "Got it. I've added this task:\n" + "\t" + curr.toString() + "\n"
                + "Now you have " + Integer.toString(toDoList.size()) + " tasks in the list.";
    }

    private String addEvent(String name, String start, String end) {
        Task curr = new Event(name, start, end);
        toDoList.add(curr);
        return "Got it. I've added this task:\n" + "\t" + curr.toString() + "\n"
                + "Now you have " + Integer.toString(toDoList.size()) + " tasks in the list.";
    }

    private String deleteTask(int index) {
        Task curr = toDoList.get(index - 1);
        toDoList.remove(curr);
        return "Noted. I've removed this task:\n" + "\t" + curr.toString() + "\n"
                + "Now you have " + Integer.toString(toDoList.size()) + " tasks in the list.";
    }

    public String sendMessage(String keyWord, String details) {
        try {
            switch (keyWord) {
                case "start":
                    return addLine(greet());
                case "bye":
                    return addLine(goodbye());
                case "list":
                    return addLine(printList());
                case "mark":
                    int markIndex = Integer.parseInt(details);
                    if (markIndex > toDoList.size() || markIndex < 0) throw new DukeException("OOPS!! Task does not exist");
                    return addLine(markTask(markIndex));
                case "unmark":
                    int unmarkIndex = Integer.parseInt(details);
                    if (unmarkIndex > toDoList.size() || unmarkIndex < 0) throw new DukeException("OOPS!! Task does not exist");
                    return addLine(unmarkTask(unmarkIndex));
                case "todo":
                    if (details == "") throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
                    return addLine(addTodo(details));
                case "deadline":
                    if (details == "") throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
                    String[] partDeadline = details.split("/by");
                    return addLine(addDeadline(partDeadline[0], partDeadline[1]));
                case "event":
                    if (details == "") throw new DukeException("OOPS!!! The description of a event cannot be empty.\n");
                    String[] partFrom = details.split("/from");
                    String[] partTo = partFrom[1].split("/to");
                    return addLine(addEvent(partFrom[0], partTo[0], partTo[1]));
                case "delete":
                    int deleteIndex = Integer.parseInt(details);
                    if (deleteIndex > toDoList.size() || deleteIndex < 0) throw new DukeException("OOPS!! Task does not exist");
                    return addLine(deleteTask(deleteIndex));
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            return addLine(e.getMessage());
        }
    }
}
