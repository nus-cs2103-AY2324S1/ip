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
                case "delete": {
                    try {
                        Kevin.deleteTask(splitMessage[1]);
                    } catch (TaskListEmptyException e) {
                        System.out.println(line);
                        System.out.println(e.getMessage());
                        System.out.println(line);
                    }
                    break;
                }
                default:
                    // Add new tasks to the task list
                    try {
                        Kevin.addNewTask(userInput);
                    } catch (DescriptionIncompleteException e1) {
                        System.out.println(line);
                        System.out.println(e1.getMessage());
                        System.out.println(line);
                    } catch (IllegalCommandException e2) {
                        System.out.println(line);
                        System.out.println(e2.getMessage());
                        System.out.println(line);
                    }
            }
            userInput = scanner.nextLine();
        }
        scanner.close();
    }

    public static void printGreetMessage() {
        String greet = line + "\n"
                + "Hello! I'm Kevin.\n"
                + "What can I do for you?\n"
                + line;
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
        currentTask.setNotDone();
        System.out.println(line + "\n");
        System.out.println("Nice! I've unmarked this task as done:\n" + currentTask);
        System.out.println(line);
    }

    public static void addNewTask(String userInput) throws DescriptionIncompleteException, IllegalCommandException{
        String[] splitMessage = userInput.split(" ", 2);
        String instruction = splitMessage[0];
        Task task = null;

        if (!(instruction.equals("todo")||instruction.equals("deadline")||instruction.equals("event"))) {
            throw new IllegalCommandException("OOPS!!! I'm sorry, but I don't know what that means :-()");
        }

        if (splitMessage.length < 2) {
            throw new DescriptionIncompleteException("OOPS!!! The description of an instruction cannot be empty.");
        }

        switch (instruction) {
            case "todo":
                task = Todo.createNewTodoTask(splitMessage[1]);
                break;
            case "deadline":
                task = Deadline.createNewDeadlineTask(splitMessage[1]);
                break;
            case "event":
                task = Event.createNewEventTask(splitMessage[1]);
                break;
        }

        taskList.add(task);
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }

    public static void deleteTask(String taskNumber) throws TaskListEmptyException {
        if (taskList.size() < 1) {
            throw new TaskListEmptyException("OOPS!!! You cannot delete an empty list.");
        }
        int number = Integer.parseInt(taskNumber);
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(number - 1));
        taskList.remove(number - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
}
