import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int taskAmount = 0;

    private static void display(String... text) {
        System.out.println("____________________________________________________________");
        for (String i : text)
            System.out.println(i);
        System.out.println("____________________________________________________________");
    }

    private static void greet() {
        Duke.display("Hello! I'm Tackie", "What can I do for you?");
    }

    private static void farewell() {
        Duke.display("Bye. Hope to see you again soon!");
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
        targetTask.markStatus();
        Duke.display("Nice! I've marked this task as done:", targetTask.toString());
    }

    private static void unmark(int taskIndex){
        Task targetTask = Duke.tasks[taskIndex];
        targetTask.unmarkStatus();
        Duke.display("OK, I've marked this task as not done yet:", targetTask.toString());
    }

    private static void printAddTaskInfo(Task task){
        Duke.display("Got it. I've added this task:",
                task.toString(),
                "Now you have " + Duke.taskAmount + " tasks in the list.");
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

        String[] inputParts;
        int taskIndex;
        String description;
        String by;
        String from;
        String to;

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            inputParts = userInput.split(" ", 2);
            switch (inputParts[0]) {
            case "list":
                Duke.list();
                break;

            case "mark":
                try {
                    taskIndex = Integer.parseInt(inputParts[1]) - 1;
                } catch (ArrayIndexOutOfBoundsException error) {
                    Duke.display("OOPS!!! The index of mark cannot be empty.");
                    break;
                }
                Duke.mark(taskIndex);
                break;

            case "unmark":
                try {
                    taskIndex = Integer.parseInt(inputParts[1]) - 1;
                } catch (ArrayIndexOutOfBoundsException error) {
                    Duke.display("OOPS!!! The index of unmark cannot be empty.");
                    break;
                }
                Duke.unmark(taskIndex);
                break;

            case "todo":
                try {
                    description = inputParts[1];
                } catch (ArrayIndexOutOfBoundsException error) {
                    Duke.display("OOPS!!! The description of a todo cannot be empty.");
                    break;
                }
                Duke.todo(description);
                break;

            case "deadline":
                try {
                    inputParts = inputParts[1].split(" /by ", 2);
                } catch (ArrayIndexOutOfBoundsException error) {
                    Duke.display("OOPS!!! The description of a deadline cannot be empty.");
                    break;
                }
                description = inputParts[0];
                try {
                    by = inputParts[1];
                } catch (ArrayIndexOutOfBoundsException error) {
                    Duke.display("OOPS!!! The by of a deadline cannot be empty.");
                    break;
                }
                Duke.deadline(description, by);
                break;

            case "event":
                try {
                    inputParts = inputParts[1].split(" /from ", 2);
                } catch (ArrayIndexOutOfBoundsException error) {
                    Duke.display("OOPS!!! The description of an event cannot be empty.");
                    break;
                }
                description = inputParts[0];
                try {
                    inputParts = inputParts[1].split(" /to ", 2);
                } catch (ArrayIndexOutOfBoundsException error) {
                    Duke.display("OOPS!!! The from of an event cannot be empty.");
                    break;
                }
                from = inputParts[0];
                try {
                    to = inputParts[1];
                } catch (ArrayIndexOutOfBoundsException error) {
                    Duke.display("OOPS!!! The to of an event cannot be empty.");
                    break;
                }
                Duke.event(description, from, to);
                break;

            default:
                Duke.display("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            userInput = scanner.nextLine();
        }
        Duke.farewell();
    }
}
