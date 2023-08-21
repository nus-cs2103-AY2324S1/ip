import java.util.Scanner;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }
}

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "________________________________________________________________";
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            System.out.println(horizontalLine);

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
                }
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + tasks[index].getStatusIcon() + " " + tasks[index].getDescription());
                }
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" " + tasks[index].getStatusIcon() + " " + tasks[index].getDescription());
                }
            } else {
                taskCount = addTask(tasks, taskCount, userInput);
                System.out.println("added: " + userInput);
            }
            System.out.println(horizontalLine);
        }
        scanner.close();
    }

    public static int addTask(Task[] tasks, int count, String taskDescription) {
        tasks[count] = new Task(taskDescription);
        return count + 1;
    }
}
