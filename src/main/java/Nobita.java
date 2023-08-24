import java.util.Scanner;

public class Nobita {
    private static Task[] store = new Task[100];
    private static int size = 0;


    public static void main(String[] args) {
        System.out.println("Hello! I'm Nobita");
        System.out.println("What can I do for you?");
        program();
        exit();
    }

    private static void program() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String[] input = sc.nextLine().split(" ",2);

            try {
                switch (input[0].toLowerCase()) {
                    case "bye":
                        break;
                    case "list":
                        for (int i = 0; i < size; ++i) {
                            System.out.println(i + 1 + ". " + store[i]);
                        }
                        break;
                    case "mark": {
                        int target = Integer.parseInt(input[1]) - 1;
                        store[target].markComplete();
                        break;
                    }
                    case "unmark": {
                        int target = Integer.parseInt(input[1]) - 1;
                        store[target].markIncomplete();
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
                    default:
                        throw new NobitaException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (NobitaException e) {
                printMessage(e.toString());
            }
        }
    }

    private static void addTask(Task task) {
        store[size++] = task;
        printMessage("Got it. I've added this task:\n" + task + "\nNow you have " + size +" tasks in the list.");
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
