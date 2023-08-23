import java.util.Scanner;

public class Duke {
    private static Task[] store = new Task[100];
    private static int size = 0;


    public static void main(String[] args) {
        System.out.println("Hello! I'm Elgin");
        System.out.println("What can I do for you?");
        program();
        exit();
    }

    private static void program() {
        label:
        while (true) {
            Scanner sc = new Scanner(System.in);
            String[] input = sc.nextLine().split(" ",2);

            switch (input[0]) {
                case "bye":
                    break label;
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
                    ToDo newToDo = new ToDo(input[1]);
                    addTask(newToDo);
                    break;
                case "deadline":
                    String[] deadlinFields = input[1].split(" /by ");
                    Deadline newDeadline = new Deadline(deadlinFields[0], deadlinFields[1]);
                    addTask(newDeadline);
                    break;
                case "event":
                    String[] eventFields = input[1].split(" /from ");
                    String[] fromAndTo = eventFields[1].split(" /to ");
                    Event newEvent= new Event(eventFields[0], fromAndTo[0], fromAndTo[1]);
                    addTask(newEvent);
                    break;
                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }
    }

    private static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(Task task) {
        store[size++] = task;
        System.out.println(" Got it. I've added this task:");
        System.out.println(task);
        System.out.printf(String.format("Now you have %d tasks in the list\n", size));
    }
}
