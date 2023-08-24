import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Tasks {
    public Line line = new Line();
    private List<Task> tasks = new ArrayList<>();


    public void handle(String text) {
        String[] parsedText = checkValid(text);
        String action = parsedText[0];
        String restOfText = parsedText[1];
        switch (action) {
            case "mark":
                this.handleMark(restOfText, true);
                break;
            case "unmark":
                this.handleMark(restOfText, false);
                break;
            case "list":
                this.listTasks();
                break;
            case "delete":
                this.handleDelete(restOfText);
                break;
            case "todo":
            case "deadline":
            case "event":
                this.addTask(action, restOfText);
                break;
            default:
                System.out.println(line);
                System.out.println("    Internal error...");
                System.out.println(line);
                break;
        }
    }

    private void handleDelete(String id) {
        try {
            int number = Integer.parseInt(id);
            Task task = this.getTask(number);
            if (task != null) {
                Task t = this.tasks.remove(number - 1);
                System.out.println("    Noted. I've removed this task:");
                System.out.println(t);
                System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
            } else {
                System.out.println(line);
                System.out.println("    Unknown task number! Please try again :-)");
                System.out.println(line);
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
            System.out.println(line);
            System.out.println("    Please enter a number for the task! Please try again :-)");
            System.out.println(line);
        }
    }

    private void listTasks() {

        System.out.println(line);
        if (tasks.size() == 0) {
            System.out.println("    You do not have any tasks currently!");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task task = this.tasks.get(i);
                String s = String.format("    %s.%s", i + 1, task);
                System.out.println(s);
            }
        }
        System.out.println(line);
    }

    private Task getTask(int id) {
        if (id > this.tasks.size() || id <= 0) {
            return null;
        }

        return this.tasks.get(id - 1);
    }

    private void handleMark(String id, boolean val) {
        try {
            int number = Integer.parseInt(id);
            Task task = this.getTask(number);
            if (task != null) {
                task.mark(val);
            } else {
                System.out.println(line);
                System.out.println("    Unknown task number! Please try again :-)");
                System.out.println(line);
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
            System.out.println(line);
            System.out.println("    Please enter a number for the task! Please try again :-)");
            System.out.println(line);
        }
    }

    private void addTask(String action, String text) {
        // Check if input text is valid
        Task task;
        switch (action) {
            case "todo":
                task = new Todo(text);
                break;
            case "deadline":
                String[] deadline = checkDeadline(text);
                task = new Deadline(deadline[0], deadline[1]);
                break;
            case "event":
                String[] event = checkEvent(text);
                task = new Event(event[0], event[1]);
                break;
            default:
                task = new Task(text);
                break;
        }

        this.tasks.add(task);
        System.out.println(line);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    private String[] checkValid(String text)  {
        String[] words = text.split(" ");
        String action = words[0];
        if (!action.equals("todo")
                && !action.equals("deadline")
                && !action.equals("event")
                && !action.equals("mark")
                && !action.equals("unmark")
                && !action.equals("list")) {
            // ignore
        }
        String[] remaining = Arrays.copyOfRange(words, 1, words.length);
        String restOfText = String.join(" ", remaining);

        if (!action.equals("list") && restOfText.equals("")) {
            System.out.println("Invalid");
        }

        return new String[] {action, restOfText};
    }

    private String[] checkDeadline(String text)  {
        String[] deadline = text.split(" /by ");
        if (deadline.length != 2) {
            System.out.println("Invalid");

        }
        return deadline;
    }

    private String[] checkEvent(String text) {
        String[] event = text.split(" /at ");
        if (event.length != 2) {
            System.out.println("Invalid");
        }
        return event;
    }
}
