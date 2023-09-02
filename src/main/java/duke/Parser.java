package duke;

import duke.exceptions.IncompleteInputException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.WrongMarkException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;
import java.util.Objects;

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
     * @return true if the command given is "bye", false otherwise.
     * @throws IncompleteInputException If the command given is incomplete.
     * @throws WrongMarkException       If the command given is wrong.
     * @throws InvalidInputException    If the command given is invalid.
     */
    public static boolean checkCommandType(String commandGiven,
                                           TaskList tasks,
                                           Ui ui,
                                           Storage storage)
            throws IncompleteInputException,
            WrongMarkException,
            InvalidInputException {
        commandGiven = commandGiven.trim();
        String[] splittedCommands = commandGiven.split(" ");
        String commandType = splittedCommands[0];
        switch (commandType) {
        case "list":
            ui.separatorLines();
            tasks.printTasks();
            break;
        case "mark":
            try {
                Task task = tasks.get(Integer.parseInt(splittedCommands[1]) - 1);
                if (Objects.equals(task.getStatusIcon(), "X")) {
                    ui.showAlreadyDone();
                } else {
                    ui.separatorLines();
                    task.setAsDone(task);
                }
                storage.save(tasks);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                ui.showInvalidIndex();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            break;
        case "unmark":
            try {
                Task task = tasks.get(Integer.parseInt(splittedCommands[1]) - 1);
                if (Objects.equals(task.getStatusIcon(), " ")) {
                    ui.showAlreadyUndone();
                } else {
                    ui.separatorLines();
                    task.setAsUndone(task);
                }
                storage.save(tasks);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                ui.showInvalidIndex();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            break;
        case "delete":
            try {
                Task task = tasks.get(Integer.parseInt(splittedCommands[1]) - 1);
                tasks.deleteTask(Integer.parseInt(splittedCommands[1]));
                ui.deleteTaskMessage(task);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                ui.showInvalidIndex();
            }
            break;
        case "find":
            try {
                ui.separatorLines();
                tasks.findTasks(splittedCommands[1]);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                throw new IncompleteInputException("find what my mann! ");
            }
            break;
        case "bye":
            try {
                storage.save(tasks);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        default:
            try {
                Task task = Task.createTask(commandGiven);
                tasks.addTask(task);
                ui.addTaskMessage(task);
                storage.save(tasks);
            } catch (InvalidInputException e) {
                ui.separatorLines();
                throw new InvalidInputException("I dont understand! " + e);
            } catch (IncompleteInputException e) {
                ui.separatorLines();
                throw new IncompleteInputException("Incomplete input eh! " + e);
            } catch (IOException e) {
                ui.showSaveError();
            }
        }
        return false;
    }
}
