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
     * @return output string from UI
     * @throws SisyphusException
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
            return ui.printTasks(taskList);
        }
        case ("mark"): {
            return runMarkCommand(params, taskList, storage, ui);
        }
        case ("unmark"): {
            return runUnmarkCommand(params, taskList, storage, ui);
        }
        case ("delete"): {
            return runDeleteCommand(params, taskList, storage, ui);
        }
        case ("todo"): {
            return runTodoCommand(params, taskList, storage, ui);
        }
        case ("deadline"): {
            return runDeadlineCommand(params, taskList, storage, ui);
        }
        case ("event"): {
            return runEventCommand(params, taskList, storage, ui);
        }
        case ("find"): {
            return runFindCommand(params, taskList, ui);
        }
        default: {
            throw new SisyphusException("Enter a valid command. Available comments are "
                    + "bye, find, list, event, deadline, todo, mark, unmark, delete.");
        }
        }
        return output;

    }

    /**
     * Returns an output string after marking a task from the taskList and storage based on the params.
     *
     * @param params
     * @param taskList
     * @param storage
     * @param ui
     * @return an output string after marking a task from the taskList and storage based on the params
     * @throws SisyphusException
     */
    public String runMarkCommand(String params, TaskList taskList, Storage storage, Ui ui) throws SisyphusException {
        int index;
        try {
            index = Integer.parseInt(params.split(" ")[0]) - 1;
            taskList.markTask(index);
            storage.writeFile(taskList);
        } catch (Exception e) {
            throw new SisyphusException("You must include a valid task number. "
                    + "Use list to see what is valid.");
        }
        return ui.printMarkTask(taskList, index);
    }

    /**
     * Returns an output string after unmarking a task from the taskList and storage based on the params.
     *
     * @param params
     * @param taskList
     * @param storage
     * @param ui
     * @return an output string after unmarking a task from the taskList and storage based on the params.
     * @throws SisyphusException
     */
    public String runUnmarkCommand(String params, TaskList taskList, Storage storage, Ui ui) throws SisyphusException {
        int index;
        try {
            index = Integer.parseInt(params.split(" ")[0]) - 1;
            taskList.unmarkTask(index);
            storage.writeFile(taskList);
        } catch (Exception e) {
            throw new SisyphusException("You must include a valid task number. "
                    + "Use list to see what is valid.");
        }
        return ui.printUnmarkTask(taskList, index);
    }

    /**
     * Returns an output string after deleting a task from the taskList and storage.
     *
     * @param params
     * @param taskList
     * @param storage
     * @param ui
     * @return an output string after deleting a task from the taskList and storage.
     * @throws SisyphusException
     */
    public String runDeleteCommand(String params, TaskList taskList, Storage storage, Ui ui) throws SisyphusException {
        int index;
        String output;
        try {
            index = Integer.parseInt(params.split(" ")[0]) - 1;
            output = ui.printDeleteTask(taskList, index);
            taskList.deleteTask(index);
            storage.writeFile(taskList);
        } catch (Exception e) {
            throw new SisyphusException("You must include a valid task number. "
                    + "Use list to see what is valid.");
        }
        return output;
    }

    /**
     * Returns an output string after adding a ToDo to the taskList and storage based on the params.
     *
     * @param params
     * @param taskList
     * @param storage
     * @param ui
     * @return an output string after adding a ToDo to the taskList and storage based on the params
     * @throws SisyphusException
     */
    public String runTodoCommand(String params, TaskList taskList, Storage storage, Ui ui) throws SisyphusException {
        if (params == "" || params == null) {
            throw new SisyphusException("Include a description for the sisyphus.task.ToDo. \nHere is an example: "
                    + "todo Roll Boulder");
        }
        ToDo todoTask = new ToDo(params);
        assert todoTask != null : "Ensure that deadline is created";
        taskList.addTask(todoTask);
        storage.writeFile(taskList);
        return ui.printAddTodo(taskList);
    }

    /**
     * Returns an output string after adding an event into the taskList and storage.
     *
     * @param params
     * @param taskList
     * @param storage
     * @param ui
     * @return an output string after adding an event into the taskList and storage
     * @throws SisyphusException
     */
    public String runEventCommand(String params, TaskList taskList, Storage storage, Ui ui) throws SisyphusException {
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
        assert eventTask != null : "Ensure that event is created";
        taskList.addTask(eventTask);
        storage.writeFile(taskList);
        return ui.printAddEvent(taskList);
    }

    /**
     * Returns an output string after adding a deadline into the taskList and storage based on the params.
     *
     * @param params
     * @param taskList
     * @param storage
     * @param ui
     * @return an output string after adding a deadline into the taskList and storage based on the params
     * @throws SisyphusException
     */
    public String runDeadlineCommand(String params, TaskList taskList, Storage storage, Ui ui) throws SisyphusException {
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
        assert deadlineTask != null : "Ensure that deadline is created";
        taskList.addTask(deadlineTask);
        storage.writeFile(taskList);
        return ui.printAddDeadline(taskList);
    }

    /**
     * Returns an output string of a list of matching tasks based on the params.
     *
     * @param params
     * @param taskList
     * @param ui
     * @return an output string of a list of matching tasks based on the params
     */
    public String runFindCommand(String params, TaskList taskList, Ui ui) {
        TaskList matchingTaskList = taskList.findMatchingTasks(params);
        return ui.printMatchingTasks(matchingTaskList, params);
    }
}
