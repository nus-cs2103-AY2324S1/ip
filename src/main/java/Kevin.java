import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kevin{

    private static final List<Task> taskList = new ArrayList<>();
    private static final String line = "____________________________________________________________";
    public static void main(String[] args) {
        Kevin.printGreetMessage();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (true) {
            // Prints out the bye message
            String[] splitMessage = userInput.split(" ");
            if (splitMessage[0].equals("bye")) {
                Kevin.printByeMessage();
                break;
            } else if (splitMessage[0].equals("list")) {
                // Prints out the list
                int count = 1;
                System.out.println(line + "\n" + "Here are the tasks in your list:");
                for (Task task: taskList) {
                    String string = String.format("%d.%s", count, task);
                    System.out.println(string);
                    count++;
                }
                System.out.println(line);
            } else if (splitMessage[0].equals("mark")) {
                Task currentTask = taskList.get(Integer.parseInt(splitMessage[1]) - 1);
                currentTask.setIsDone();
                System.out.println(line + "\n");
                System.out.println("Nice! I've marked this task as done:\n" + currentTask);
                System.out.println(line);
            } else if (splitMessage[0].equals("unmark")) {
                Task currentTask = taskList.get(Integer.parseInt(splitMessage[1]) - 1);
                currentTask.setNotDone();
                System.out.println(line + "\n");
                System.out.println("OK, I've marked this task as not done yet:\n" + currentTask);
                System.out.println(line);
            } else {
                // Add new tasks to the task list
                Task task = new Task(userInput);
                taskList.add(task);
                String message = line + "\n"
                        + "added: " + userInput + "\n"
                        + line + "\n";
                System.out.println(message);
            }
            userInput = scanner.nextLine();
        }
        scanner.close();
    }

    public static void printGreetMessage() {
        String greet = line + "\n"
                + "Hello! I'm Kevin\n"
                + "What can I do  for you?\n"
                + line + "\n";
        System.out.println(greet);
    }

    public static void printByeMessage() {
        String bye = line + "\n"
                + "Bye. Hope to see you again soon!\n"
                + line;
    }
}
