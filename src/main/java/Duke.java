import java.util.Scanner;
public class Duke {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount= 0;
    private static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++]  = new Task(taskDescription);
            System.out.println("added: " + taskDescription);
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
    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < taskCount;
    }
    private static void markTask(String userCommand) {
        try {
            int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
            if (isValidTaskIndex(taskIndex)) {
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskIndex]);
            } else {
                System.out.println("Invalid task number. Please enter a valid task number.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command format. Please use 'mark [task number]'.");
        }
    }
    private static void unmarkTask(String userCommand) {
        try {
            int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
            if (isValidTaskIndex(taskIndex)) {
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[taskIndex]);
            } else {
                System.out.println("Invalid task number. Please enter a valid task number.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command format. Please use 'unmark [task number]'.");
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
                System.out.println("Here are the tasks in your list:");
                listTasks();
            } else if (userCommand.startsWith("mark ")) {
                markTask(userCommand);
            } else if (userCommand.startsWith("unmark ")) {
                unmarkTask(userCommand);
            } else {
                addTask(userCommand);
            }
        }
    }
}
