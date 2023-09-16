package Kevin.Parser;

import Kevin.TaskList.TaskList;
import Kevin.Ui.Ui;
import Kevin.Exception.TaskListEmptyException;
import Kevin.Exception.DescriptionIncompleteException;
import Kevin.Exception.IllegalCommandException;
import java.time.format.DateTimeParseException;

/**
 * A class which deals with making sense of the user command
 */
public class Parser {
    private static boolean isExit = false;

    /**
     * This static method takes in user command and
     * performs tasks specific to that command.
     *
     * @param userCommand User command
     * @param taskList A taskList object
     * @param ui An Ui object
     */
    public static void parse(String userCommand, TaskList taskList, Ui ui) {
        String[] splitMessage = userCommand.split(" ");
        String instruction = splitMessage[0];
        switch (instruction) {
            case "bye":
                isExit = true;
                ui.printByeMessage();
                break;
            case "list":
                // Prints out the list
                taskList.listAllTasks();
                break;
            case "mark": {
                taskList.markTaskDone(splitMessage[1]);
                break;
            }
            case "unmark": {
                taskList.unmarkTaskDone(splitMessage[1]);
                break;
            }
            case "delete": {
                try {
                    taskList.deleteTask(splitMessage[1]);
                } catch (TaskListEmptyException e) {
                    System.out.println(Ui.line);
                    System.out.println(e.getMessage());
                    System.out.println(Ui.line);
                }
                break;
            }
            default:
                // Add new tasks to the task list
                try {
                    taskList.addNewTask(userCommand);
                } catch (DescriptionIncompleteException | IllegalCommandException e1) {
                    System.out.println(Ui.line);
                    System.out.println(e1.getMessage());
                    System.out.println(Ui.line);
                } catch (DateTimeParseException e2) {
                    System.out.println(Ui.line);
                    System.out.println("OOPS!!! You have entered a wrong date format.");
                    System.out.println("Please follow this format:");
                    System.out.println("yyyy-mm-dd");
                    System.out.println(Ui.line);
                }
        }
    }

    public static boolean getIsExit() {
        return Parser.isExit;
    }
}
