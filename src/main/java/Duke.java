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
            System.out.println(lineSpacer);

            if (userInput.equals("bye")) {
                System.out.println("Goodbye! Have a paw-some day :-)");
                break;

            } else if (userInput.equals("list")) {
                for (int i = 0; i < taskList.size(); i++ ) {
                    System.out.println((i + 1) + ". " + taskList.get(i).toString());
                }

            } else if (userInput.contains("unmark")) {
                String[] unparsedTaskIndex = userInput.split(" ");
                int taskIndex = Integer.parseInt(unparsedTaskIndex[1]);
                taskList.get(taskIndex - 1).markAsUndone();
                System.out.println("That's ruff! I've unmarked this task:  \n" +
                        taskList.get(taskIndex - 1).toString());

            } else if (userInput.contains("mark")) {
                String[] unparsedTaskIndex = userInput.split(" ");
                int taskIndex = Integer.parseInt(unparsedTaskIndex[1]);
                taskList.get(taskIndex - 1).markAsDone();
                System.out.println("Furtastic job completing this task: \n" +
                        taskList.get(taskIndex - 1).toString());

            } else if (userInput.contains("todo")) {
                String[] splitCommands = userInput.split(" ");
                Task task = new ToDo(splitCommands[1]);
                taskList.add(task);
                System.out.println("Woof luck with your new task: \n" + task.toString());
                System.out.println("You now have " + taskList.size() + " tasks in the list");

            } else if (userInput.contains("deadline")) {
                String[] splitCommands = userInput.split(" /by ");
                String[] taskName = splitCommands[0].split(" ");
                Task task = new Deadline(taskName[1] ,splitCommands[1]);
                taskList.add(task);
                System.out.println("Woof luck with your new task: \n" + task.toString());
                System.out.println("You now have " + taskList.size() + " tasks in the list");

            } else if (userInput.contains("event")) {
                String[] splitCommands = userInput.split(" /from ");
                String[] taskName = splitCommands[0].split(" ");
                String[] fromTo = splitCommands[1].split(" /to ");
                Task task = new Event(taskName[1], fromTo[0], fromTo[1]);
                taskList.add(task);
                System.out.println("Woof luck with your new task: \n" + task.toString());
                System.out.println("You now have " + taskList.size() + " tasks in the list");

            } else {
                Task task = new Task(userInput);
                taskList.add(task);
                System.out.println("Woof luck with your new task: " + userInput);
            }
            System.out.println(lineSpacer);
        }

        System.out.println(lineSpacer);

    }
}
