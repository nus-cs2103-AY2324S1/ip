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

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + list[i].getStatusIcon()
                            + " " + list[i].getDescription());
                }
            } else if (command.toLowerCase().startsWith("mark")) {
                int number = Integer.parseInt(command.split(" ")[1]) - 1;
                list[number].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("   " + list[number].getStatusIcon() + " "
                        + list[number].getDescription());

            } else if (command.toLowerCase().startsWith("unmark")) {
                int number = Integer.parseInt(command.split(" ")[1]) - 1;
                list[number].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("   " + list[number].getStatusIcon() + " "
                        + list[number].getDescription());
            } else {
                Task task = new Task(command);
                list[count] = task;
                count++;
                System.out.println("added: " + command);
            }

            //System.out.println("   " + command);
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



    }

}
