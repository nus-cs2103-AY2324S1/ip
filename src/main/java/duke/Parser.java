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
            tasks.printTasks();
            break;
        case "mark":
            try {
                Task task = tasks.get(Integer.parseInt(splittedCommand[1]) - 1);
                if (Objects.equals(task.getStatusIcon(), "X")) {
                    ui.showAlreadyDone();
                } else {
                    task.setAsDone();
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
                    task.setAsUndone();
                }
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                ui.showInvalidIndex();
            }
            break;
        case "delete":
            tasks.deleteTask(Integer.parseInt(splittedCommand[1]));
            ui.deleteTaskMessage();
            break;
        case "bye":
            return true;
        default:
            try {
                Task task = Task.createTask(commandGiven);
                tasks.add(task);
                ui.addTaskMessage(task);
            } catch (IncompleteInputException e) {
                throw new IncompleteInputException("Wrong input eh " + e);
            } catch (InvalidInputException e) {
                throw new InvalidInputException("I dont understand eh " + e);
            }
        }
        return false;
    }
}
