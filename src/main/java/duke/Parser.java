package duke;

import duke.exceptions.IncompleteInputException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.WrongMarkException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;
import java.util.Objects;

public class Parser {
    public Parser() {
    }

    public static boolean checkCommandType(String commandGiven,
                                           TaskList tasks,
                                           Ui ui,
                                           Storage storage)
            throws IncompleteInputException,
            WrongMarkException,
            InvalidInputException {
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
                storage.save(tasks);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                ui.showInvalidIndex();
            } catch (IOException e) {
                throw new RuntimeException(e);
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
                storage.save(tasks);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                ui.showInvalidIndex();
            } catch (IOException e) {
                throw new RuntimeException(e);
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
        case "find":
            ui.separatorLines();
            try {
                String keyword = splittedCommand[1];
                tasks.findTasks(keyword);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                throw new IncompleteInputException("find what eh! ");
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
