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
        label:
        while (true) {
            // Prints out the bye message
            String[] splitMessage = userInput.split(" ");
            String instruction = splitMessage[0];
            switch (instruction) {
                case "bye":
                    Kevin.printByeMessage();
                    break label;
                case "list":
                    // Prints out the list
                    Kevin.listAllTasks();
                    break;
                case "mark": {
                    Kevin.markTaskDone(splitMessage[1]);
                    break;
                }
                case "unmark": {
                    Kevin.unmarkTaskDone(splitMessage[1]);
                    break;
                }
                default:
                    // Add new tasks to the task list
                    Kevin.addNewTask(userInput);
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
        System.out.println(bye);
    }

    public static void listAllTasks() {
        int count = 1;
        System.out.println(line + "\n" + "Here are the tasks in your list:");
        for (Task task: taskList) {
            String string = String.format("%d.%s", count, task);
            System.out.println(string);
            count++;
        }
        System.out.println(line);
    }

    public static void markTaskDone(String taskNumber) {
        Task currentTask = taskList.get(Integer.parseInt(taskNumber) - 1);
        currentTask.setIsDone();
        System.out.println(line + "\n");
        System.out.println("Nice! I've marked this task as done:\n" + currentTask);
        System.out.println(line);
    }

    public static void unmarkTaskDone(String taskNumber) {
        Task currentTask = taskList.get(Integer.parseInt(taskNumber) - 1);
        currentTask.setIsDone();
        System.out.println(line + "\n");
        System.out.println("Nice! I've marked this task as done:\n" + currentTask);
        System.out.println(line);
    }

    public static void addNewTask(String userInput) {
        String[] splitMessage = userInput.split(" ", 2);
        String instruction = splitMessage[0];
        Task task;
        if (instruction.equals("todo")) {
            task = Todo.createNewTodoTask(splitMessage[1]);
        } else if (instruction.equals("deadline")) {
            task = Deadline.createNewDeadlineTask(splitMessage[1]);
        } else {
            task = Event.createNewEventTask(splitMessage[1]);
        }

        taskList.add(task);
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
}
