import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.intro();
        duke.run();
        duke.exit();
    }

    public void lines() {
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

    public void run() {
        ArrayList<Task> tasks = new ArrayList<>();
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                lines();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                lines();
            } else if (input.contains("unmark")) {
                lines();
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                tasks.get(index).unmark();
                System.out.println(tasks.get(index));
                lines();
            } else if (input.contains("mark")) {
                lines();
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                tasks.get(index).mark();
                System.out.println(tasks.get(index));
                lines();
            } else if (input.contains("todo")) {
                lines();
                String[] split = input.split(" ");
                String description = "";
                for (int i = 1; i < split.length; i++) {
                    description += split[i] + " ";
                }
                description = description.trim();
                tasks.add(new Todo(description));
                System.out.println(tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                lines();
            } else if (input.contains("deadline")) {
                lines();
                String[] split = input.split(" ");
                String description = "";
                String by = "";
                for (int i = 1; i < split.length; i++) {
                    if (split[i].equals("/by")) {
                        for (int j = i + 1; j < split.length; j++) {
                            by += split[j] + " ";
                        }
                        break;
                    }
                    description += split[i] + " ";
                }
                description = description.trim();
                by = by.trim();
                tasks.add(new Deadline(description, by));
                System.out.println(tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                lines();
            } else if (input.contains("event")) {
                lines();
                String[] split = input.split(" ");
                String description = "";
                String from = "";
                String to = "";
                for (int i = 1; i < split.length; i++) {
                    if (split[i].equals("/from")) {
                        for (int j = i + 1; j < split.length; j++) {
                            if (split[j].equals("/to")) {
                                for (int k = j + 1; k < split.length; k++) {
                                    to += split[k] + " ";
                                }
                                break;
                            }
                            from += split[j] + " ";
                        }
                        break;
                    }
                    description += split[i] + " ";
                }
                description = description.trim();
                from = from.trim();
                to = to.trim();
                tasks.add(new Events(description, from, to));
                System.out.println(tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                lines();
            } else {
                lines();
                System.out.println("added: " + input);
                tasks.add(new Task(input));
                lines();
            }
            input = sc.nextLine();
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
}
