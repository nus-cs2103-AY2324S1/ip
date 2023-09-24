package Forgotten.Parser;

import Forgotten.Ui.Ui;
import Forgotten.TaskList.TaskList;
import Forgotten.Exception.TaskListEmptyException;
import Forgotten.Exception.DescriptionIncompleteException;
import Forgotten.Exception.IllegalCommandException;
import java.time.format.DateTimeParseException;

/**
 * A class which deals with making sense of the user command
 */
public class Parser {
    /**
     * This static method takes in user command and
     * performs tasks specific to that command.
     *
     * @param userCommand User command
     * @param taskList A taskList object
     * @param ui An Ui object
     */
    public static String parse(String userCommand, TaskList taskList, Ui ui) {
        String response = "";
        String[] splitMessage = userCommand.split(" ", 2);
        String instruction = splitMessage[0];

        switch (instruction) {
            case "bye":
                response = "Bye. Hope to see you again soon!";
                break;
            case "list":
                // Prints out the list
                response = taskList.listAllTasks();
                break;
            case "mark":
                response = taskList.markTaskDone(splitMessage[1]);
                break;
            case "unmark":
                response = taskList.unmarkTaskDone(splitMessage[1]);
                break;
            case "delete":
                try {
                    response = taskList.deleteTask(splitMessage[1]);
                } catch (TaskListEmptyException e) {
                    response = e.getMessage();
                }
                break;
            case "find":
                response = taskList.findTask(splitMessage[1]);
                break;
            case "prioritize":
                response = taskList.prioritizeTask(splitMessage[1]);
                break;
            default:
                // Add new tasks to the task list
                try {
                    response = taskList.addNewTask(userCommand);
                } catch (DescriptionIncompleteException | IllegalCommandException e1) {
                    response = e1.getMessage();
                } catch (DateTimeParseException e2) {
                    response += "OOPS!!! You have entered a wrong date format.\nPlease follow this format:\nyyyy-mm-dd";
                }
        }
        assert !response.equals(""): "The bot response should not be empty";
        return response;
    }
}
