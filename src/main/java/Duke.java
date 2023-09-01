import DukePackage.ChatUI;
import DukePackage.Parser;
import DukePackage.Storage;
import DukePackage.Task;
import DukePackage.TaskType;
import java.util.Objects;


/**
 * The Duke class is the main class that runs the Duke chatbot application.
 */
public class Duke {

    /**
     * The main method that starts the Duke application.
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // standard response
        String horizontalLine = "   ------------------------------------------------------------------------";
        String noDescError = "     ☹ OOPS!!! The description of a todo cannot be empty.";
        String noCommandError = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

        // initialise
        Storage storage = new Storage();
        Parser parser = new Parser();
        ChatUI ui = new ChatUI();

        // read from txt file and create tasks and put into storage
        storage.loadListFromFile();
        ui.printIntro();

        while (true) {
            String input = parser.getInput();
            String[] parts = input.split(" ");
            ui.printDivider();

            switch (parts[0]) {
                case "bye":
                    ui.printOutro();
                    ui.printDivider();
                    // write the changes into the file duke.txt
                    storage.writeTasksToFile();
                    return;
                case "list":
                    storage.printTaskList();
                    break;
                case "mark":
                    int id = Integer.parseInt(parts[1]) - 1;
                    try {
                        storage.changeTaskMarking(id, true);
                        storage.printTaskMarking(id);
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                    }
                    break;
                case "unmark":
                    int id2 = Integer.parseInt(parts[1]) - 1;
                    try {
                        storage.changeTaskMarking(id2, false);
                        storage.printTaskMarking(id2);
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                    }
                    break;
                case "delete":
                    int id3 = Integer.parseInt(parts[1]) - 1;
                    storage.deleteTask(id3);
                    break;
                case "find":
                    int indexOfFind = input.indexOf("find");
                    String toFindString = input.substring(indexOfFind + 5);
                    storage.printMatchingList(toFindString);
                    break;
                case "todo":
                    int indexOfTodo = input.indexOf("todo");
                    String taskDesc = input.substring(indexOfTodo + 5);
                    if (Objects.equals(taskDesc, "")) {
                        System.out.println(horizontalLine);
                        System.out.println(noDescError);
                        System.out.println(horizontalLine);
                        break;
                    }
                    Task task = new Task(taskDesc, TaskType.TODO, "", "");
                    storage.addList(task);
                    storage.printTaskEntry(task);
                    break;
                case "deadline":
                    int indexOfDeadline = input.indexOf("deadline");
                    int indexOfBy = input.indexOf("/by");
                    taskDesc = input.substring(indexOfDeadline + 9, indexOfBy);
                    String deadlinePart = "";
                    deadlinePart = input.substring(indexOfBy + 3).trim();
                    task = new Task(taskDesc, TaskType.DEADLINE, deadlinePart, "");
                    storage.addList(task);
                    storage.printTaskEntry(task);
                    break;
                case "event":
                    int indexOfEvent = input.indexOf("event");
                    int indexOfFrom = input.indexOf("/from");
                    int indexOfTo = input.indexOf("/to");
                    taskDesc = input.substring(indexOfEvent + 6, indexOfFrom);
                    String fromPart = "";
                    fromPart = input.substring(indexOfFrom + 5, indexOfTo).trim();
                    String toPart = "";
                    toPart = input.substring(indexOfTo + 3).trim();
                    task = new Task(taskDesc, TaskType.EVENT, fromPart, toPart);
                    storage.addList(task);
                    storage.printTaskEntry(task);
                    break;
                default:
                    System.out.println(noCommandError);
            }
            ui.printDivider();
        }
    }
}