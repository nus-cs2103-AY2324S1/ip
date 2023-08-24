import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.intro();
        duke.run();
        duke.exit();
    }

    private void lines() {
        System.out.println("_".repeat(50));
    }
    public void intro() {
        lines();
        System.out.println("Hello! I'm sillyBot\nWhat can I do for you?");
        lines();
    }

    public void exit() {
        lines();
        System.out.println("Bye. Hope to see you again soon!");
        lines();
    }

    public void list() {
        lines();
        if (taskCount == 0) {
            System.out.println("You have no tasks in your list.");
            lines();
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        lines();
    }

    public void mark(String description) {
        lines();
        int index = Integer.parseInt(description) - 1;
        tasks.get(index).mark();
        System.out.println(tasks.get(index));
        lines();
    }

    public void unmark(String description) {
        lines();
        int index = Integer.parseInt(description) - 1;
        tasks.get(index).unmark();
        System.out.println(tasks.get(index));
        lines();
    }

    public void run() {
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                commandGiven(input);
            } catch (Duke.DukeException e) {
                System.out.println(e);
            }
            input = sc.nextLine();
        }
    }

    public void commandGiven(String input) throws DukeException {
        String[] split = input.split(" ", 2);
        String command = split[0];
        if (split.length == 1 && !command.equals("list")) {
            if (command.equals("mark") || command.equals("unmark") || command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                throw new IncompleteInput("I don't know what you want to do!");
            }
            throw new InvalidInput("I don't know what you want to do!");
        }

        if (command.equals("list")) {
            list();
            return;
        }
        String description = split[1];

        switch (command) {
            case "todo":
                tasks.add(new Todo(description));
                tasks.get(taskCount++).print();
                break;
            case "deadline":
                String[] deadlineSplit = description.split(" /by ");
                String deadlineDescription = deadlineSplit[0];
                String deadlineBy = deadlineSplit[1];
                tasks.add(new Deadline(deadlineDescription, deadlineBy));
                tasks.get(taskCount++).print();
                break;
            case "event":
                String[] eventSplit = description.split(" /from ");
                String eventDescription = eventSplit[0];
                String eventFrom = eventSplit[1].split(" /to ")[0];
                String eventTo = eventSplit[1].split(" /to ")[1];
                tasks.add(new Events(eventDescription, eventFrom, eventTo));
                tasks.get(taskCount++).print();
                break;
            case "mark":
                mark(description);
                break;
            case "unmark":
                unmark(description);
                break;
            default:
                throw new InvalidInput("what's this?");
        }
    }

    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); //return tick or X symbols
        }

        public void mark() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }

        public void unmark() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }

        public String getDescription() {
            return this.description;
        }

        public void print() {
            System.out.println(this);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            lines();
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.getDescription();
        }
    }

    public class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
            System.out.println("Got it. I've added this task:");
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + this.by + ")";
        }
    }

    public class Events extends Task {
        protected String from;
        protected String to;

        public Events(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
            System.out.println("Got it. I've added this task:");
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
        }
    }

    public class Todo extends Task {
        public Todo (String description) {
            super(description);
            System.out.println("Got it. I've added this task:");

        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }

        @Override
        public String toString() {
            return "OOPS!!! " + super.getMessage();
        }
    }

    public class InvalidInput extends DukeException {
        public InvalidInput(String message) {
            super(message);
        }

        @Override
        public String toString() {
            return "Why like this ah? Don't even know what it means!? " + super.getMessage();
        }
    }

    public class IncompleteInput extends DukeException {
        public IncompleteInput(String message) {
            super(message);
        }

        @Override
        public String toString() {
            return "Where the rest BOSSMAN!? " + super.getMessage();
        }
    }
}

