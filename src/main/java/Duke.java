import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        int taskCount = 0;
        Task[] tasks =  new Task[100];
        System.out.println("Hello! I'm Chatty\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        while (isRunning) {
            String userInput = scanner.nextLine();
            System.out.println("____________________________________________________________");
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isRunning = false;
                System.out.println("____________________________________________________________");
            } else if (userInput.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i + 1 + " .[" + tasks[i].getStatusIcon() + "] " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("mark ")) {
                System.out.println("Nice! I've marked this task as done:");
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                tasks[taskIndex].switchCheck();
                System.out.println("[" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex]);
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("unmark ")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                tasks[taskIndex].switchCheck();
                System.out.println("[" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex]);
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("added:" + userInput);
                tasks[taskCount] = new Task(userInput);
                taskCount++;
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
