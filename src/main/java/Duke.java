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
            } else {
                    Task nextTask = new Task(next);
                    tasks[taskNo] = nextTask;
                    taskNo++;
                    System.out.println("added: " + next);
            }
            next = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}