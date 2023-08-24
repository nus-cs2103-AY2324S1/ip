import java.util.Scanner;
class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + getDescription();
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

public class EchoBot {
    public static int extractTaskNum(String userInput, String command) {
        String taskNumberStr = userInput.substring(command.length()).trim();
        return Integer.parseInt(taskNumberStr);
    }
    public static String extractTaskDesc(String userInput, String command) {
        return userInput.substring(command.length()).trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        String horizontalLine = "   ___________________________________________________\n";

        //Array to store the tasks
        Task[] tasks = new Task[100];
        int numOfTask = 0;

        System.out.println(horizontalLine + "    Hello! I'm EchoBot\n" + logo);
        System.out.println("    What can I do for you?\n" + horizontalLine);

        while(true) {
            // Read the user input
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(horizontalLine + "    Bye. Hope to see you again soon!\n" + horizontalLine);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println(horizontalLine + "    Here are the tasks in your list:");

                for (int i = 0; i < numOfTask; i++) {
                    Task task = tasks[i];
                    System.out.println("    " + (i + 1) + "." + task);
                }

                System.out.println(horizontalLine);
            } else if (userInput.startsWith("todo")) {
                String taskDescription = extractTaskDesc(userInput, "todo");
                tasks[numOfTask] = new Todo(taskDescription);
                numOfTask++;

                System.out.println(horizontalLine + "    Got it. I've added this task:\n" + "     " + tasks[numOfTask - 1].toString());
                System.out.println("    Now you have " + numOfTask + " tasks in the list.\n" + horizontalLine);
            } else if (userInput.startsWith("deadline")) {
                String taskDescription = extractTaskDesc(userInput, "deadline");
                int indexOfBy = taskDescription.indexOf("/by");
                String deadlineDescription = taskDescription.substring(0, indexOfBy).trim();
                String by = taskDescription.substring(indexOfBy + 3).trim();
                tasks[numOfTask] = new Deadline(deadlineDescription, by);
                numOfTask++;

                System.out.println(horizontalLine + "    Got it. I've added this task:\n" + "     " + tasks[numOfTask - 1].toString());
                System.out.println("    Now you have " + numOfTask + " tasks in the list.\n" + horizontalLine);
            } else if (userInput.startsWith("event")) {
                String taskDescription = extractTaskDesc(userInput, "event");
                int indexOfFrom = taskDescription.indexOf("/from");
                int indexOfTo = taskDescription.indexOf("/to");
                String eventDescription = taskDescription.substring(0, indexOfFrom).trim();
                String from = taskDescription.substring(indexOfFrom + 5, indexOfTo).trim();
                String to = taskDescription.substring(indexOfTo + 3).trim();
                tasks[numOfTask] = new Event(eventDescription, from, to);
                numOfTask++;

                System.out.println(horizontalLine + "    Got it. I've added this task:\n" + "     " + tasks[numOfTask - 1].toString());
                System.out.println("    Now you have " + numOfTask + " tasks in the list.\n" + horizontalLine);
            } else if (userInput.startsWith("mark")) {
                int taskNum = extractTaskNum(userInput, "mark");

                if (taskNum >= 1 && taskNum <= numOfTask) {
                    Task task = tasks[taskNum-1];
                    task.mark();

                    System.out.println(horizontalLine + "    Nice! I've marked this task as done:");
                    System.out.println("      " + "[" + task.getStatusIcon() + "]" + " " + task.getDescription());
                    System.out.println(horizontalLine);
                }
            } else if (userInput.startsWith("unmark")) {
                int taskNum = extractTaskNum(userInput, "unmark");

                if (taskNum >= 1 && taskNum <= numOfTask) {
                    Task task = tasks[taskNum-1];
                    task.unmark();

                    System.out.println(horizontalLine + "    OK, I've marked this task as not done yet:");
                    System.out.println("      " + "[" + task.getStatusIcon() + "]" + " " + task.getDescription());
                    System.out.println(horizontalLine);
                }
            }
        }
    }
}
