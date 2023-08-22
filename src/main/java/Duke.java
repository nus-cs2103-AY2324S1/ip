import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm May");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int count = 0;

        while (true) {
            String command = scanner.nextLine();

            if (command.isEmpty()) {
                try {
                    throw new DukeException("☹ OOPS!!! The description cannot be empty.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + list[i].toString());
                }
            } else if (command.toLowerCase().startsWith("mark")) {
                int number = Integer.parseInt(command.split(" ")[1]) - 1;
                list[number].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[number].toString());

            } else if (command.toLowerCase().startsWith("unmark")) {
                int number = Integer.parseInt(command.split(" ")[1]) - 1;
                list[number].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list[number].toString());

            } else if (command.toLowerCase().startsWith("todo")) {
                String todo = command.substring(4).trim();
                if (todo.isEmpty()) {
                    try {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    Todo add = new Todo(todo);
                    list[count] = add;
                    count++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(add.toString());
                    System.out.println("Now you have " + count + " tasks in the list.");
                }

            } else if (command.toLowerCase().startsWith("deadline")) {
                String deadline = command.substring(8).trim();

                if (deadline.isEmpty()) {
                    try {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {

                    String[] sub = deadline.split("/by");

                    String description = sub[0].trim();
                    String by = sub[1].trim();

                    Deadline add = new Deadline(description, by);
                    list[count] = add;
                    count++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(add.toString());
                    System.out.println("Now you have " + count + " tasks in the list.");
                }

            } else if (command.toLowerCase().startsWith("event")) {
                String event = command.substring(5).trim();

                if (event.isEmpty()) {
                    try {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {

                    String[] sub = event.split("/from");

                    String description = sub[0].trim();
                    String timing = sub[1].trim();

                    String[] fromTo = timing.split("/to");
                    String from = fromTo[0].trim();
                    String to = fromTo[1].trim();

                    Event add = new Event(description, from, to);
                    list[count] = add;
                    count++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(add.toString());
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
            } else {
//                Task task = new Task(command);
//                list[count] = task;
//                count++;
//                System.out.println("Got it. I've added this task:");
//                System.out.println(task.toString());
//                System.out.println("Now you have " + count + " tasks in the list.");
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]"); // mark done task with X
        }

        public String getDescription() {
            return this.description;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsNotDone() {
            this.isDone = false;
        }

        public String toString() {
            return this.getStatusIcon() + " " + this.description;
        }


    }

    public static class Deadline extends Task {

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

    public static class Todo extends Task {

        protected String by;

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Event extends Task {

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

    public static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }



}
