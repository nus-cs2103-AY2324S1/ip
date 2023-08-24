import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        String lineSpacer = "________________________________________";
        String logo = "   / \\__\n"
                + "  (    @\\___\n"
                + "  /          O\n"
                + " /   (_____/\n"
                + "/_____/   \n";

        System.out.println(lineSpacer);
        System.out.println("Hello I'm Barkley\n" + logo);
        System.out.println("Howl can I help you?");
        System.out.println(lineSpacer);

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(lineSpacer);
                System.out.println("Goodbye! Have a paw-some day :-)");
                System.out.println(lineSpacer);
                break;
            } else if (userInput.equals("list")) {
                System.out.println(lineSpacer);
                for (int i = 0; i < taskList.size(); i++ ) {
                    System.out.println((i + 1) + ". " + taskList.get(i).toString());
                }
                System.out.println(lineSpacer);
            } else if (userInput.contains("unmark")) {
                System.out.println(lineSpacer);
                String[] unparsedTaskIndex = userInput.split(" ");
                int taskIndex = Integer.parseInt(unparsedTaskIndex[1]);
                taskList.get(taskIndex - 1).markAsUndone();
                System.out.println("That's ruff! I've unmarked this task:  \n" +
                        taskList.get(taskIndex - 1).toString());
                System.out.println(lineSpacer);
            } else if (userInput.contains("mark")) {
                System.out.println(lineSpacer);
                String[] unparsedTaskIndex = userInput.split(" ");
                int taskIndex = Integer.parseInt(unparsedTaskIndex[1]);
                taskList.get(taskIndex - 1).markAsDone();
                System.out.println("Furtastic job completing this task: \n" +
                        taskList.get(taskIndex - 1).toString());
                System.out.println(lineSpacer);
            } else {
                System.out.println(lineSpacer);
                Task task = new Task(userInput);
                taskList.add(task);
                System.out.println("Woof luck with your new task: " + userInput);
                System.out.println(lineSpacer);
            }
        }

    }
}
