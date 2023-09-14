package Kevin.Parser;

import Kevin.TaskList.TaskList;
import Kevin.Ui.Ui;
import Kevin.Exception.TaskListEmptyException;
import Kevin.Exception.DescriptionIncompleteException;
import Kevin.Exception.IllegalCommandException;
import java.time.format.DateTimeParseException;

public class Parser {
    private static boolean isExit = false;

    public static void parse(String userCommand, TaskList taskList) {
        String[] splitMessage = userCommand.split(" ");
        String instruction = splitMessage[0];
        switch (instruction) {
            case "bye":
                isExit = true;
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
