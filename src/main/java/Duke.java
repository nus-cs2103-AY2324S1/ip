import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int taskAmount = 0;

    private static void greet() {
        String greetings = "____________________________________________________________\n" +
                " Hello! I'm Tackie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greetings);
    }

    private static void farewell() {
        String farewell = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(farewell);
    }

    private static void list(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Duke.taskAmount; i++) {
            System.out.println(Integer.toString(i + 1) + "." + Duke.tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    private static void mark(int taskIndex){
        Task targetTask = Duke.tasks[taskIndex];
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        targetTask.markStatus();
        System.out.println(targetTask);
        System.out.println("____________________________________________________________");
    }

    private static void unmark(int taskIndex){
        Task targetTask = Duke.tasks[taskIndex];
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        targetTask.unmarkStatus();
        System.out.println(targetTask);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            String[] inputParts = userInput.split(" ", 2);
            int taskIndex;
            switch (inputParts[0]) {
            case "list":
                Duke.list();
                break;

            case "mark":
                taskIndex = Integer.parseInt(inputParts[1]) - 1;
                Duke.mark(taskIndex);
                break;

            case "unmark":
                taskIndex = Integer.parseInt(inputParts[1]) - 1;
                Duke.unmark(taskIndex);
                break;

            default:
                System.out.println("____________________________________________________________");
                System.out.println("Added: " + userInput);
                System.out.println("____________________________________________________________");
                Duke.tasks[Duke.taskAmount] = new Task(userInput);
                Duke.taskAmount += 1;
            }
            userInput = scanner.nextLine();
        }
        Duke.farewell();
    }
}
