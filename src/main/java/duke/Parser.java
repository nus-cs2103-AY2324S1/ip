package duke;

import duke.exceptions.IncompleteInputException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.WrongMarkException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.Objects;

public class Parser {
    public Parser() {
    }

    public static boolean checkCommandType(String commandGiven, TaskList tasks, Ui ui) throws IncompleteInputException, WrongMarkException, InvalidInputException {
        commandGiven = commandGiven.trim();
        String[] splittedCommand = commandGiven.split(" ");
        String commandType = splittedCommand[0];
        switch (commandType) {
        case "list":
            ui.separatorLines();
            tasks.printTasks();
            break;
        case "mark":
            try {
                Task task = tasks.get(Integer.parseInt(splittedCommand[1]) - 1);
                if (Objects.equals(task.getStatusIcon(), "X")) {
                    ui.showAlreadyDone();
                } else {
                    ui.separatorLines();
                    task.setAsDone(task);
                }
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                ui.showInvalidIndex();
            }
            break;
        case "unmark":
            try {
                Task task = tasks.get(Integer.parseInt(splittedCommand[1]) - 1);
                if (Objects.equals(task.getStatusIcon(), " ")) {
                    ui.showAlreadyUndone();
                } else {
                    ui.separatorLines();
                    task.setAsUndone(task);
                }
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                ui.showInvalidIndex();
            }
            break;
        case "delete":
            try {
                Task task = tasks.get(Integer.parseInt(splittedCommand[1]) - 1);
                tasks.deleteTask(Integer.parseInt(splittedCommand[1]));
                ui.deleteTaskMessage(task);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                ui.showInvalidIndex();
            }
            break;
        case "bye":
            return true;
        default:
            try {
                Task task = Task.createTask(commandGiven);
                tasks.addTask(task);
                ui.addTaskMessage(task);
            } catch (InvalidInputException e) {
                ui.separatorLines();
                throw new InvalidInputException("I dont understand! " + e);
            } catch (IncompleteInputException e) {
                ui.separatorLines();
                throw new IncompleteInputException("Incomplete input eh! " + e);
            }
        }
        return false;
    }
}
