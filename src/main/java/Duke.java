import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Task {
    String description; // the toString handles the space after the [ ] or [X]
    boolean isDone = false;
    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + this.description : "[ ] " + this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public String addTaskString (int currNumOfTask) {
        return "Got it. I've added this task:\n  "
                + this.toString() + "\n" // not sure if this toString will call this toString or the object's toString
                + "Now you have " + currNumOfTask + " tasks in the list.\n";
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

    @Override
    public String addTaskString (int currNumOfTask) {
        return super.addTaskString(currNumOfTask);
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

    @Override
    public String addTaskString (int currNumOfTask) {
        return super.addTaskString(currNumOfTask);
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String addTaskString (int currNumOfTask) {
        return super.addTaskString(currNumOfTask);
    }
}

public class Duke {

    public static void main(String[] args) {
        String openingStr =
                "____________________________________________________________\n" +
                " Hello! I'm JEOE\n" +
                " What can I do for you?\n" +
                " type :\n" +
                " list => to list out items in storage\n" +
                " _Anything else_ => store in storage\n" +
                "____________________________________________________________\n";
        System.out.println(openingStr);

        Scanner scanner = new Scanner(System.in); // makes it such that the scanner takes in inputs from the console
        List<Task> storage = new ArrayList<>();
        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0]; // even if its empty string, the 0 index will still be ""

            String reply = "____________________________________________________________\n";
            if (command.equals("bye")) {
                isRunning = false;
                reply += " Bye. Hope to see you again soon!\n";
            } else if (command.equals("list")) {
                reply += "Here are the tasks in your list:\n";

                for (int i = 0; i < storage.size(); i++) {
                    String num = String.valueOf(i + 1) + ". ";
                    Task task = storage.get(i);
                    reply += num + task + "\n";
                }
            } else if (command.equals("todo")) {
                // create the actual task
                String description = input.replaceFirst("todo ", "");
                Todo todo = new Todo(description);
                // add to the storage
                storage.add(todo);
                // add to the reply
                reply += todo.addTaskString(storage.size());

            } else if (command.equals("deadline")) {
                String[] arr = input.replaceFirst("deadline ", "").split(" /by ");
                String description = arr[0];
                String by = arr[1];
                Deadline deadline = new Deadline(description, by);
                // add to storage
                storage.add(deadline);
                //add to the reply
                reply += deadline.addTaskString(storage.size());

            } else if (command.equals("event")) {
                String[] arr = input.replaceFirst("event ", "").split(" /from "); // arr have description
                String description = arr[0];
                String[] arr2 = arr[1].split(" /to "); // arr2 have the from & to
                String from = arr2[0];
                String to = arr2[1];
                Event event = new Event(description, from, to);
                // add to storage
                storage.add(event);
                // add to the reply
                reply += event.addTaskString(storage.size());

            } else if (command.equals("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                storage.get(index).isDone = true;
                reply +=
                        ("Nice! I've marked this task as done:\n" +
                        "[X] " + storage.get(index).description + "\n");

            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                storage.get(index).isDone = false;
                reply +=
                        ("OK, I've marked this task as not done yet:\n" +
                        "[] " + storage.get(index).description + "\n");
            } else {
                // nothing happens, since i only want the 3 specific types of tasks for now
//                storage[++filledIndex] = new Task (input); // storage array starts filling from index 1
//                reply += ("added: " + input + "\n");
            }

            reply += "____________________________________________________________\n";
            System.out.println(reply);
        }
        scanner.close();
    }
}
