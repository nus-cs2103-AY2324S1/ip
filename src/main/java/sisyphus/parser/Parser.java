package sisyphus.parser;

import java.time.LocalDate;

import sisyphus.SisyphusException;
import sisyphus.storage.Storage;
import sisyphus.task.Deadline;
import sisyphus.task.Event;
import sisyphus.task.TaskList;
import sisyphus.task.ToDo;
import sisyphus.ui.Ui;

/**
 * Parser class that processes the main logic flow to parse different commands.
 */
public class Parser {
    private boolean isChatting;

    /**
     * Constructor to initialise isChatting as true.
     */
    public Parser() {
        isChatting = true;
    }

    /**
     * Gets whether the parser is still active.
     *
     * @return state of isChatting.
     */
    public boolean getActiveStatus() {
        return this.isChatting;
    }

    /**
     * Run different process based on the given command. Commands include
     * bye, mark, unmark, delete, todo, deadline, event, list.
     *
     * @param fullCommand
     * @param taskList
     * @param storage
     * @param ui
     * @throws SisyphusException
     * @return output string from UI
     */
    public String runCommand(String fullCommand, TaskList taskList, Storage storage, Ui ui) throws SisyphusException {
        String[] inputArray;
        String command;
        String params = "";
        String output = "";

        inputArray = fullCommand.split(" ", 2);
        command = inputArray[0];

        if (inputArray.length > 1) {
            params = inputArray[1];
        }

        switch (command) {
        case ("bye"): {
            isChatting = false;
            break;
        }
        case ("list"): {
            output = ui.printTasks(taskList);
            break;
        }
        case ("mark"): {
            int index;
            try {
                index = Integer.parseInt(params.split(" ")[0]) - 1;
                taskList.markTask(index);
                storage.writeFile(taskList);
            } catch (Exception e) {
                throw new SisyphusException("You must include a valid task number. "
                        + "Use list to see what is valid.");
            }
            output = ui.printMarkTask(taskList, index);
            break;
        }
        case ("unmark"): {
            int index;
            try {
                index = Integer.parseInt(params.split(" ")[0]) - 1;
                taskList.unmarkTask(index);
                storage.writeFile(taskList);
            } catch (Exception e) {
                throw new SisyphusException("You must include a valid task number. "
                        + "Use list to see what is valid.");
            }
            output = ui.printUnmarkTask(taskList, index);
            break;
        }
        case ("delete"): {
            int index;
            try {
                index = Integer.parseInt(params.split(" ")[0]) - 1;
                output = ui.printDeleteTask(taskList, index);
                taskList.deleteTask(index);
                storage.writeFile(taskList);
            } catch (Exception e) {
                throw new SisyphusException("You must include a valid task number. "
                        + "Use list to see what is valid.");
            }
            break;
        }
        case ("todo"): {
            if (params == "" || params == null) {
                throw new SisyphusException("Include a description for the sisyphus.task.ToDo. \nHere is an example: "
                        + "todo Roll Boulder");
            }
            ToDo todoTask = new ToDo(params);
            taskList.addTask(todoTask);
            storage.writeFile(taskList);
            output = ui.printAddTodo(taskList);
            break;
        }
        case ("deadline"): {
            String description;
            String deadline;
            LocalDate deadlineDate;
            try {
                description = params.split(" /by ")[0];
                deadline = params.split(" /by ")[1];
                deadlineDate = LocalDate.parse(deadline);
            } catch (Exception e) {
                throw new SisyphusException("Include both description and deadline for a deadline. \nHere "
                        + "is an example: deadline roll boulder /by 2023-10-15");
            }

            Deadline deadlineTask = new Deadline(description, deadlineDate);
            taskList.addTask(deadlineTask);
            storage.writeFile(taskList);
            output = ui.printAddDeadline(taskList);
            break;
        }
        case ("event"): {
            String description;
            String fromAndToTime;
            String from;
            String to;
            try {
                description = params.split(" /from ")[0];
                fromAndToTime = params.split(" /from ")[1];
                from = fromAndToTime.split(" /to ")[0];
                to = fromAndToTime.split(" /to ")[1];
            } catch (Exception e) {
                throw new SisyphusException("Include the description, from and to time for an event. \nHere is"
                        + " an example: event roll boulder /from past /to eternity");
            }

            Event eventTask = new Event(description, from, to);
            taskList.addTask(eventTask);
            storage.writeFile(taskList);
            output = ui.printAddEvent(taskList);
            break;
        }
        case ("find"): {
            TaskList matchingTaskList = taskList.findMatchingTasks(params);
            output = ui.printMatchingTasks(matchingTaskList, params);
            break;
        }
        default: {
            output = "Enter a valid command. Available comments are "
                    + "bye, find, list, event, deadline, todo, mark, unmark, delete.";
            throw new SisyphusException("Enter a valid command. Available comments are "
                    + "bye, find, list, event, deadline, todo, mark, unmark, delete.");
        }
        }
        return output;

    }
}
