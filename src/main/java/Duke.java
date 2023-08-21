import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String name = "Botty";
        String tmp = "";
        Task[] taskList = new Task[100];
        int counter = 0;
        Scanner scanner = new Scanner(System.in);
        greet(name);
        while (true) {
            tmp = scanner.nextLine();
            if (tmp.equals("bye")) {
                break;
            } else if (tmp.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    Task currTask = taskList[i];
                    String listCounter = (i + 1) + ".";
                    System.out.println(listCounter + currTask.toString());
                }
                System.out.println("\n");
            } else if (tmp.contains("unmark")) {
                String[] stringArray = tmp.split(" ");
                if (stringArray.length != 2) {
                    System.out.println("Error unexpected number or arguments");
                } else {
                    System.out.println("OK, I've marked this task as not done yet:");
                    int taskListIndex = Integer.parseInt(stringArray[1]) - 1;
                    Task currTask = taskList[taskListIndex];
                    currTask.markUndone();
                    System.out.println(currTask + "\n");
                }
            } else if (tmp.contains("mark")) {
                String[] stringArray = tmp.split(" ");
                if (stringArray.length != 2) {
                    System.out.println("Error unexpected number or arguments");
                } else {
                    System.out.println("Nice! I've marked this task as done:");
                    int taskListIndex = Integer.parseInt(stringArray[1]) - 1;
                    Task currTask = taskList[taskListIndex];
                    currTask.markDone();
                    System.out.println(currTask + "\n");
                }
            } else if (tmp.contains("todo")) {
                String[] stringArray = tmp.split(" ", 2);
                if (stringArray.length != 2) {
                    System.out.println("Error unexpected number or arguments");
                } else {
                    Todo toAdd = new Todo(stringArray[1]);
                    taskList[counter] = toAdd;
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + toAdd);
                    System.out.println("Now you have " + counter + " tasks in the list.\n");
                }
            } else if (tmp.contains("deadline")) {
                String[] stringArray = tmp.split(" ", 2);
                if (stringArray.length != 2) {
                    System.out.println("Error unexpected number or arguments");
                } else {
                    String[] splitDeadline = stringArray[1].split(" /by ", 2);
                    Deadline toAdd = new Deadline(splitDeadline[0], splitDeadline[1]);
                    taskList[counter] = toAdd;
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + toAdd);
                    System.out.println("Now you have " + counter + " tasks in the list.\n");
                }
            } else if (tmp.contains("event")) {
                String[] stringArray = tmp.split(" ", 2);
                if (stringArray.length != 2) {
                    System.out.println("Error unexpected number or arguments");
                } else {
                    String[] splitFrom = stringArray[1].split(" /from ", 2);
                    String description = splitFrom[0];
                    String[] splitTo = splitFrom[1].split(" /to ", 2);
                    String from = splitTo[0];
                    String to = splitTo[1];
                    Event toAdd = new Event(description, from, to);
                    taskList[counter] = toAdd;
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + toAdd);
                    System.out.println("Now you have " + counter + " tasks in the list.\n");
                }
            } else {
                System.out.println("I don't recognise this command");
            }
        }
        bye();
    }
    public static void greet(String name) {
        System.out.println("Hello! I'm " + name + "\n" +
                "What can I do for you?\n");
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
