package duke.duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.util.Parser;
import duke.util.Storage;

/**
 * The main class that executes the Duke bot.
 */
public class Duke {

    public final Storage storage;
    public final Ui ui;
    private TaskList taskList;

    /**
     * Constructs the Duke bot instance.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/tasks.txt");
        taskList = new TaskList(100);
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Gets the response of the duke bot generated based on user input.
     * @param input User's input
     * @return Duke's response.
     */
    public String processInputAndGetResponse(String input) {
        try {
            Parser parser = new Parser(input);
            String command = parser.getCommand();
            String inputDetails = parser.getInputDetails();
            switch (command) {
            case "list":
                return (taskList.showList());
            case "mark": {
                if (parser.checkInputDetailsAbsent()) {
                    throw new DukeException("The task number to mark cannot be empty.");
                }
                int taskNumber = Integer.parseInt(inputDetails);
                Task task = taskList.getTask(taskNumber);
                return (task.setDone());
            }
            case "unmark": {
                if (parser.checkInputDetailsAbsent()) {
                    throw new DukeException("The task number to unmark cannot be empty.");
                }
                int taskNumber = Integer.parseInt(inputDetails);
                Task task = taskList.getTask(taskNumber);
                return (task.unsetDone());
            }
            case "todo": {
                if (parser.checkInputDetailsAbsent()) {
                    throw new DukeException("☹ OOPS!!! The description of a todo task cannot be empty.");
                }
                Task task = new Todo(inputDetails);
                storage.saveTask(inputDetails);
                return (taskList.addTask(task));
            }
            case "deadline": {
                if (parser.checkInputDetailsAbsent()) {
                    throw new DukeException("☹ OOPS!!! The info of a deadline task cannot be empty.");
                }
                String[] deadlineInfo = parser.parseInputDetailsDeadline();
                String description = deadlineInfo[0];
                String deadline = deadlineInfo[1];
                Task task = new Deadline(description, deadline);
                storage.saveTask(description, deadline);
                return (taskList.addTask(task));
            }
            case "event": {
                if (parser.checkInputDetailsAbsent()) {
                    throw new DukeException("☹ OOPS!!! The info of a event task cannot be empty.");
                }
                String[] eventInfo = parser.parseInputDetailsEvent();
                String description = eventInfo[0];
                String from = eventInfo[1];
                String to = eventInfo[2];
                Task task = new Event(description, from, to);
                storage.saveTask(description, from, to);
                return (taskList.addTask(task));
            }
            case "delete": {
                if (parser.checkInputDetailsAbsent()) {
                    throw new DukeException("The task number to delete cannot be empty.");
                }
                int taskNumber = Integer.parseInt(inputDetails);
                storage.deleteTaskFromFile(taskNumber);
                return (taskList.removeTask(taskNumber));
            }
            case "find": {
                if (parser.checkInputDetailsAbsent()) {
                    throw new DukeException("The keyword to find cannot be empty");
                }
                return (taskList.findTasks(inputDetails));
            }
            case "reschedule": {
                if (parser.checkInputDetailsAbsent()) {
                    throw new DukeException("☹ OOPS!!! The info of task to reschedule cannot be empty.");
                }
                String[] rescheduleInfo = parser.parseRescheduleInfo();
                int taskNumber = Integer.parseInt(rescheduleInfo[0]);
                String rescheduleDetails = rescheduleInfo[1];
                Task task = taskList.getTask(taskNumber);
                storage.deleteTaskFromFile(taskNumber);
                return (task.reschedule(rescheduleDetails, storage));
            }
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            return (ui.showMessage(e.getMessage()));
        }
    }

}
