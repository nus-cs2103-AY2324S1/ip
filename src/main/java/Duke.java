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
            System.out.println((i + 1) + "." + Duke.tasks[i]);
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

    private static void printAddTaskInfo(Task task){
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + Duke.taskAmount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void todo(String description){
        Todo newTask = new Todo(description);
        Duke.tasks[Duke.taskAmount] = newTask;
        Duke.taskAmount += 1;
        Duke.printAddTaskInfo(newTask);
    }

    private static void deadline(String description, String by){
        Deadline newTask = new Deadline(description, by);
        Duke.tasks[Duke.taskAmount] = newTask;
        Duke.taskAmount += 1;
        Duke.printAddTaskInfo(newTask);
    }

    private static void event(String description, String from, String to){
        Event newTask = new Event(description, from, to);
        Duke.tasks[Duke.taskAmount] = newTask;
        Duke.taskAmount += 1;
        Duke.printAddTaskInfo(newTask);
    }

    public static void main(String[] args) {
        Duke.greet();

        int taskIndex;
        String description;

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            String[] inputParts = userInput.split(" ", 2);
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

            case "todo":
                description = inputParts[1];
                Duke.todo(description);
                break;

            case "deadline":
                inputParts = inputParts[1].split(" /by ", 2);
                description = inputParts[0];
                String by = inputParts[1];
                Duke.deadline(description, by);
                break;

            case "event":
                inputParts = inputParts[1].split(" /from ", 2);
                description = inputParts[0];
                inputParts = inputParts[1].split(" /to ", 2);
                String from = inputParts[0];
                String to = inputParts[1];
                Duke.event(description, from, to);
                break;

            default:
                System.out.println("____________________________________________________________");
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }
            userInput = scanner.nextLine();
        }
        Duke.farewell();
    }
}
