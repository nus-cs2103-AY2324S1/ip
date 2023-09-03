import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        int taskCount = 0;
        String[] tasks =  new String[100];
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
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i + 1 + " . " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("added:" + userInput);
                tasks[taskCount] = userInput;
                taskCount++;
                System.out.println("____________________________________________________________");
            }
        }
    }
}
