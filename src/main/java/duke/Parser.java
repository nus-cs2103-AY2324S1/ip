package duke;

import java.io.IOException;
import java.util.Objects;

import duke.exceptions.IncompleteInputException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.WrongMarkException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents a Parser object that deals with making sense of the user command.
 */
public class Parser {
    /**
     * Creates a Parser object.
     */
    public Parser() {
    }

    /**
     * Checks the type of command given by the user.
     * Executes the command given by the user.
     *
     * @param commandGiven The command given by the user.
     * @param tasks        The TaskList object containing the tasks.
     * @param ui           The Ui object to deal with user input and output.
     * @param storage      The Storage object to deal with loading tasks from the file and saving tasks in the file.
     * @return The String containing the output of the command.
     * @throws IncompleteInputException If the command given is incomplete.
     * @throws WrongMarkException       If the command given is wrong.
     * @throws InvalidInputException    If the command given is invalid.
     */
    public static String checkCommandType(String commandGiven,
                                           TaskList tasks,
                                           Ui ui,
                                           Storage storage)
            throws IncompleteInputException,
            WrongMarkException,
            InvalidInputException {
        commandGiven = commandGiven.trim();
        String[] splittedCommands = commandGiven.split(" ");
        String commandType = splittedCommands[0];
        String command = "";
        switch (commandType) {
        case "list":
            ui.separatorLines();
            command = tasks.printTasks();
            break;
        case "mark":
            try {
                Task task = tasks.get(Integer.parseInt(splittedCommands[1]) - 1);
                if (Objects.equals(task.getStatusIcon(), "X")) {
                    command = ui.showAlreadyDone();
                } else {
                    ui.separatorLines();
                    command = task.setAsDone(task);
                }
                storage.save(tasks);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                command = ui.showInvalidIndex();
            } catch (IOException e) {
                command = String.valueOf(e);
                throw new RuntimeException(e);
            }
            break;
        case "unmark":
            try {
                Task task = tasks.get(Integer.parseInt(splittedCommands[1]) - 1);
                if (Objects.equals(task.getStatusIcon(), " ")) {
                    command = ui.showAlreadyUndone();
                } else {
                    ui.separatorLines();
                    command = task.setAsUndone(task);
                }
                storage.save(tasks);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                command = ui.showInvalidIndex();
            } catch (IOException e) {
                command = String.valueOf(e);
                throw new RuntimeException(e);
            }
            break;
        case "delete":
            try {
                Task task = tasks.get(Integer.parseInt(splittedCommands[1]) - 1);
                tasks.deleteTask(Integer.parseInt(splittedCommands[1]));
                command = ui.deleteTaskMessage(task);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                command = ui.showInvalidIndex();
            }
            break;
        case "find":
            ui.separatorLines();
            try {
                String keyword = splittedCommands[1];
                command = tasks.findTasks(keyword);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                command = "find what eh! ";
                throw new IncompleteInputException("find what eh! ");
            }
            break;
        case "bye":
            try {
                storage.save(tasks);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            command = "bye";
            break;
        default:
            try {
                Task task = Task.createTask(commandGiven);
                tasks.addTask(task);
                command = ui.addTaskMessage(task);
                storage.save(tasks);
            } catch (InvalidInputException e) {
                ui.separatorLines();
                command = "I dont understand! " + e;
                throw new InvalidInputException("I dont understand! " + e);
            } catch (IncompleteInputException e) {
                ui.separatorLines();
                command = "Incomplete input eh! " + e;
                throw new IncompleteInputException("Incomplete input eh! " + e);
            } catch (IOException e) {
                command = ui.showSaveError();
            }
        }
        return command.trim();
    }
}
