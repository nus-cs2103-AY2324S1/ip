import java.util.Scanner;

public class Sally {
    public static void main(String[] args) {
        System.out.println("Hey! It's Sally here!\n" + "How can I help you today?\n \n");

        Task[] tasks = new Task[100];
        int taskCount = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye! See you again next time.");
                break;
            } else if (input.equals("list")) {
                System.out.println("My List:");
                for (int i = 0; i < taskCount; i++) {
                    Task task = tasks[i];
                    System.out.println((i + 1) + ". " + task.getStatusIcon() + " " + task.getTask());
                }
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex < 0 || taskIndex >= taskCount) {
                    System.out.println("Invalid task number!");
                } else {
                    Task task = tasks[taskIndex];
                    task.mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + task.getStatusIcon() + " " + task.getTask());
                }
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex < 0 || taskIndex >= taskCount) {
                    System.out.println("Invalid task number!");
                } else {
                    Task task = tasks[taskIndex];
                    task.unmark();
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println(" " + task.getStatusIcon() + " " + task.getTask());
                }
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("Added to My List: " + input);
            }
        }
    }
}
