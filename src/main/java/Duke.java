import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataStorage store = new DataStorage();
        store.loadTasks();
        ArrayList<Task> taskList = store.taskData;



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
            try {
                String userInput = scanner.nextLine();
                System.out.println(lineSpacer);

                if (userInput.equals("bye")) {
                    store.saveTasks(taskList);
                    System.out.println("Goodbye! Have a paw-some day :-) \n" + logo);
                    break;

                } else if (userInput.equals("list")) {
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + ". " + taskList.get(i).toString());
                    }

                } else if (userInput.contains("unmark")) {
                    if (userInput.substring(6).isBlank()) {
                        throw new DukeException("More information is required to unmark a task");
                    } else {
                        String[] unparsedTaskIndex = userInput.split(" ");
                        int taskIndex = Integer.parseInt(unparsedTaskIndex[1]) - 1;
                        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
                            throw new DukeException("There are only " + taskList.size() + " tasks");
                        } else {
                            taskList.get(taskIndex).markAsUndone();
                            System.out.println("That's ruff! I've unmarked this task:  \n" +
                                    taskList.get(taskIndex).toString());
                        }
                    }

                } else if (userInput.contains("mark")) {
                    if (userInput.substring(4).isBlank()) {
                        throw new DukeException("More information is required to mark a task");
                    } else {
                        String[] unparsedTaskIndex = userInput.split(" ");
                        int taskIndex = Integer.parseInt(unparsedTaskIndex[1]) - 1;
                        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
                            throw new DukeException("There are only " + taskList.size() + " tasks");
                        } else {
                            taskList.get(taskIndex).markAsDone();
                            System.out.println("Furtastic job completing this task: \n" +
                                taskList.get(taskIndex).toString());
                        }
                    }

                } else if (userInput.contains("delete")) {
                    if (userInput.substring(6).isBlank()) {
                        throw new DukeException("More information is required to delete a task");
                    } else {
                        String[] unparsedTaskIndex = userInput.split(" ");
                        int taskIndex = Integer.parseInt(unparsedTaskIndex[1]) - 1;
                        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
                            throw new DukeException("There are only " + taskList.size() + " tasks");
                        } else {
                            System.out.println("Okay! Another dog-gone task down:  \n" +
                                    taskList.get(taskIndex).toString());
                            taskList.remove(taskIndex);
                            System.out.println("You now have " + taskList.size() + " tasks in the list");
                        }
                    }

                } else if (userInput.contains("todo")) {
                    String taskInfo = userInput.substring(4);
                    if (taskInfo.isBlank()) {
                        throw new DukeException("The description of a todo cannot be empty");
                    } else {
                        String[] splitCommands = userInput.split(" ");
                        Task task = new ToDo(splitCommands[1]);
                        taskList.add(task);
                        System.out.println("Woof luck with your new task: \n" + task.toString());
                        System.out.println("You now have " + taskList.size() + " tasks in the list");
                    }

                } else if (userInput.contains("deadline")) {
                    String taskInfo = userInput.substring(8);
                    if (taskInfo.isBlank() || !userInput.contains("/by")
                            || !userInput.contains("deadline ")
                            || userInput.substring(userInput.indexOf("/by") + 3).isBlank()) {
                        throw new DukeException("The description of deadline needs more information");
                    } else {
                        String[] splitCommands = userInput.split(" /by ");
                        String[] taskName = splitCommands[0].split(" ");
                        Task task = new Deadline(taskName[1], splitCommands[1]);
                        taskList.add(task);
                        System.out.println("Woof luck with your new task: \n" + task.toString());
                        System.out.println("You now have " + taskList.size() + " tasks in the list");
                    }

                } else if (userInput.contains("event")) {
                    String taskInfo = userInput.substring(5);
                    if (taskInfo.isBlank() || !userInput.contains("/from")
                            || !userInput.contains("/to") || !userInput.contains("event ")
                            || userInput.substring(userInput.indexOf("/from") + 5).isBlank()
                            || userInput.substring(userInput.indexOf("/to") + 3).isBlank()) {
                        throw new DukeException("The description of event needs more information");
                    } else {
                        String[] splitCommands = userInput.split(" /from ");
                        String[] taskName = splitCommands[0].split(" ");
                        String[] fromTo = splitCommands[1].split(" /to ");
                        Task task = new Event(taskName[1], fromTo[0], fromTo[1]);
                        taskList.add(task);
                        System.out.println("Woof luck with your new task: \n" + task.toString());
                        System.out.println("You now have " + taskList.size() + " tasks in the list");
                    }

                } else {
                    throw new DukeException("Un-fur-tunately I don't know what you mean :-(");
                }
                System.out.println(lineSpacer);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(lineSpacer);
            }
        }

        System.out.println(lineSpacer);

    }
}
