import java.util.Scanner;

public class Duke {

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            if (!this.isDone) {
                this.isDone = true;
            }
        }

        public void unmark() {
            if (this.isDone) {
                this.isDone = false;
            }
        }

    }

    public static void main(String[] args) {
        int itemsAdded = 0;
        Task[] taskList = new Task[100]; // assume there will be <= 100 tasks
        System.out.println("Hello friend :> My name is John, nice to meet you!\n" +
                "What do you have to do today?\n");

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Reply John: ");
            String command = sc.nextLine(); // using .next() is wrong - only reads first word

            if (command.equals("bye")) {
                System.out.println("Bye for now, hope to see you soon.");
                sc.close();
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                
                for (int i = 1; i <= itemsAdded; i++) {
                    Task task = taskList[i - 1];
                    System.out.println(i + ". " + "[" + task.getStatusIcon() + "] " + task.description);
                }
            } else if (command.startsWith("mark ")) { // space behind is needed!
                int taskPos = Integer.parseInt(command.substring(5)) - 1; // convert substring to int

                // only numbers >= 0 and < total number are valid
                if (taskPos >= 0 && taskPos < itemsAdded) {
                    taskList[taskPos].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "[X] " + taskList[taskPos].description);
                } else {
                    System.out.println("Invalid.");
                }

            } else if (command.startsWith("unmark ")) { // space behind is needed!
                int taskPos = Integer.parseInt(command.substring(7)) - 1; // convert substring to int

                // only numbers >= 0 and < total number are valid
                if (taskPos >= 0 && taskPos < itemsAdded) {
                    taskList[taskPos].unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "[ ] " + taskList[taskPos].description);
                } else {
                    System.out.println("Invalid.");
                }

            } else {
                taskList[itemsAdded] = new Task(command); // add new command
                System.out.println("added: " + command); // show user that it is added
                itemsAdded++; // increment number of items
            }
        }

    }
}

/*
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
 */

