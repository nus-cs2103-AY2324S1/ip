import java.util.Scanner;
public class Duke {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount= 0;
    private static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++]  = task;
            System.out.println("added: " + task);
        } else {
            System.out.println("Sorry, the task list is full.");
        }
    }
    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println("The task list is empty!");
        } else {
            for(int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }
    public static void main(String[] args) {
        String greeting = "Hi, I'm BiuBiu.\nWhat can I do for you?";
        System.out.println(greeting);
        String exit = "Bye. Have a great day!";

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String userCommand = scanner.nextLine();
            if(userCommand.equalsIgnoreCase("bye")) {
                System.out.println(exit);
                break;
            } else if (userCommand.equalsIgnoreCase("list")) {
                listTasks();
            }
            else {
                addTask(userCommand);
            }
        }
    }
}
