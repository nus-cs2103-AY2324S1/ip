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
                    String listCounter = String.valueOf(i + 1) + ".";
                    String checkBox = "[" + currTask.getStatusIcon() + "]";
                    String taskDescription = currTask.getDescription();
                    System.out.println(listCounter + checkBox + " " + taskDescription);
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
                    String checkBox = "[" + currTask.getStatusIcon() + "]";
                    String taskDescription = currTask.getDescription();
                    System.out.println( checkBox + " " + taskDescription + "\n");
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
                    String checkBox = "[" + currTask.getStatusIcon() + "]";
                    String taskDescription = currTask.getDescription();
                    System.out.println( checkBox + " " + taskDescription + "\n");
                }
            }  else {
                System.out.println("added " + tmp + "\n");
                taskList[counter] = new Task(tmp);
                counter++;
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
