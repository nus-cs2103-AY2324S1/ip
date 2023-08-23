import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        class Task {
            final protected String description;
            protected boolean isDone;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }

            public void mark() {
                this.isDone = true;
            }

            public void unmark() {
                this.isDone = false;
            }

            private String getStatusIcon() {
                return (isDone ? "X" : " "); // mark done task with X
            }

            @Override
            public String toString() {
                return ("[" + this.getStatusIcon() + "] " + this.description);
            }
        }

        class Todo extends Task {
            public Todo (String desc) {
                super(desc.substring(5));
            }

            @Override
            public String toString() {
                return "[T]" + super.toString();
            }
        }

        class Deadline extends Task {
            final String by;
            public Deadline(String desc) {
                super(desc.substring(9, desc.indexOf("/by")));
                this.by = desc.substring(desc.indexOf("/by") + 4);
            }

            @Override
            public String toString() {
                return "[D]" + super.toString() + "(by: " + this.by + ")";
            }
        }

        class Event extends Task {
            final String from;
            final String to;
            public Event(String desc) {
                super(desc.substring(6, desc.indexOf("/from")));
                int fromIndex = desc.indexOf("/from");
                int toIndex = desc.indexOf("/to");
                this.from = desc.substring(fromIndex + 6, toIndex);
                this.to = desc.substring(toIndex + 4);
            }

            @Override
            public String toString() {
                return "[E]" + super.toString() + "(from: " + this.from + "to: " + this.to +")";
            }
        }

        Scanner scan = new Scanner(System.in);
        Task[] tasks = new Task[100];
        String name = "Chaty";
        int taskNo = 0;
        System.out.println("Hello! I'm " + name + "\n" +
                "What can I do for you?" + "\n\n");
        String next = scan.nextLine();
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                for (int i = 0; i < taskNo ; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
            } else if (next.startsWith("mark")) {
                int tasknum = Integer.parseInt(next.split(" ")[1]) - 1;
                tasks[tasknum].mark();
                System.out.println("Nice! I've marked this task as done:\n" + tasks[tasknum]);
            } else if (next.startsWith("unmark")) {
                int tasknum = Integer.parseInt(next.split(" ")[1]) - 1;
                tasks[tasknum].unmark();
                System.out.println(" OK, I've marked this task as not done yet:\n" + tasks[tasknum]);
            } else if (next.startsWith("deadline")){
                    Task nextTask = new Deadline(next);
                    tasks[taskNo] = nextTask;
                    taskNo++;
                    System.out.println("Got it. I've added this task: \n" + nextTask + "\nnow you have " + taskNo + " tasks in the list");
            } else if (next.startsWith("event")){
                Task nextTask = new Event(next);
                tasks[taskNo] = nextTask;
                taskNo++;
                System.out.println("Got it. I've added this task: \n" + nextTask + "\nnow you have " + taskNo + " tasks in the list");
            } else if (next.startsWith("todo")){
                Task nextTask = new Todo(next);
                tasks[taskNo] = nextTask;
                taskNo++;
                System.out.println("Got it. I've added this task: \n" + nextTask + "\nnow you have " + taskNo + " tasks in the list");
            }
            next = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}