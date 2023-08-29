import java.util.ArrayList;
import java.util.Scanner;

public class Nobita {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int size = 0;

    public static void main(String[] args) {
        System.out.println("Hello! I'm Nobita");
        System.out.println("What can I do for you?");
        program();
        exit();
    }

    private static void program() {
        Scanner sc = new Scanner(System.in);
        boolean live = true;
        while (live) {
            String[] input = sc.nextLine().split(" ",2);

            try {
                switch (input[0].toLowerCase()) {
                case "bye":
                    live = false;
                    break;
                case "list":
                    int listNum = 1;
                    for (Task task: tasks) {
                        printMessage(listNum + ". " + task);
                        ++listNum;
                    }
                    break;
                case "mark": {
                    int target = Integer.parseInt(input[1]) - 1;
                    tasks.get(target).markComplete();
                    break;
                }
                case "unmark": {
                    int target = Integer.parseInt(input[1]) - 1;
                    tasks.get(target).markIncomplete();
                    break;
                }
                case "todo":
                    if (input.length < 2) {
                        throwDescriptionException("todo");
                    }
                    ToDo newToDo = new ToDo(input[1]);
                    addTask(newToDo);
                    break;
                case "deadline":
                    if (input.length < 2) {
                        throwDescriptionException("deadline");
                    }
                    String[] deadlineFields = input[1].split(" /by ");
                    Deadline newDeadline = new Deadline(deadlineFields[0], deadlineFields[1]);
                    addTask(newDeadline);
                    break;
                case "event":
                    if (input.length < 2) {
                        throwDescriptionException("event");
                    }
                    String[] eventFields = input[1].split(" /from ");
                    String[] fromAndTo = eventFields[1].split(" /to ");
                    Event newEvent = new Event(eventFields[0], fromAndTo[0], fromAndTo[1]);
                    addTask(newEvent);
                    break;
                case "delete":
                    int target = Integer.parseInt(input[1]) - 1;
                    deleteTask(target);
                    break;
                default:
                    throw new NobitaException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (NobitaException e) {
                printMessage(e.toString());
            }
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        printMessage("Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() +" tasks in the list.");
    }

    private static void deleteTask(int targetTask) {
        Task task = tasks.remove(targetTask);
        printMessage("Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.size() +" tasks in the list.");
    }

    private static void throwDescriptionException(String command) throws NobitaException {
        throw new NobitaException("The description of a " + command + " cannot be empty.\n"
                    + "Please specify a description.");
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    private static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
