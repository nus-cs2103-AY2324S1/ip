package duke;

import duke.exceptions.DukeInvalidDescriptionException;
import duke.exceptions.DukeInvalidIndexException;
import duke.exceptions.DukeInvalidTimeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;

import java.io.IOException;

/**
 * A <code>Handler</code> object keeps track of the <code>TaskList</code>, <code>Ui</code>,
 * and <code>Storage</code> objects, using the data from each to handle the new commands as they
 * come from the user.
 */

public class Handler {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The class constructor.
     *
     * @param taskList A <code>TaskList</code> object. Keeps track of the tasks.
     * @param ui A <code>Ui</code> object. Handles responses to the user.
     * @param storage A <code>Storage</code> object. Handles reading and writing.
     */
    public Handler(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Handles a mark command, marking the relevant <code>Task</code> as done.
     *
     * @param command The command from the user
     * @return Acknowledgement of the marking.
     */
    public String handleMark(String command) throws DukeInvalidDescriptionException, DukeInvalidIndexException {
        String[] parsed = Parser.splitSpace(command);
        if (parsed.length < 2) {
            throw new DukeInvalidDescriptionException();
        }
        boolean valid = true;
        try {
            int i = Integer.parseInt(parsed[1]);
        } catch (NumberFormatException nfe) {
            valid = false;
        }
        if (!valid) {
            throw new DukeInvalidDescriptionException();
        }
        int ind = Integer.parseInt(parsed[1]);
        if (ind > taskList.getLength() || ind < 1) {
            throw new DukeInvalidIndexException();
        }
        taskList.mark(ind-1);
        try {
            storage.save(taskList.writeTaskList());
        } catch (IOException e) {
            e.getMessage();
            System.out.println(ui.failure());
        }
        return ui.markText(taskList.getTask(ind-1));
    }

    /**
     * Handles an unmark command, marking the relevant <code>Task</code> as unfinished.
     *
     * @param command The command from the user
     * @return Acknowledgement of the unmarking.
     */
    public String handleUnmark(String command) throws DukeInvalidDescriptionException,
            DukeInvalidIndexException {
        String[] parsed = Parser.splitSpace(command);
        if (parsed.length < 2) {
            throw new DukeInvalidDescriptionException();
        }
        boolean valid = true;
        try {
            int i = Integer.parseInt(parsed[1]);
        } catch (NumberFormatException nfe) {
            valid = false;
        }
        if (!valid) {
            throw new DukeInvalidDescriptionException();
        }
        int ind = Integer.parseInt(parsed[1]);
        if (ind > taskList.getLength()  || ind < 1) {
            throw new DukeInvalidIndexException();
        }
        taskList.unmark(ind-1);
        try {
            storage.save(taskList.writeTaskList());
        } catch (IOException e) {
            e.getMessage();
            System.out.println(ui.failure());
        }
        return ui.unmarkText(taskList.getTask(ind-1));
    }

    /**
     * Handles a todo command, creating a corresponding <code>ToDo</code> object.
     *
     * @param command The command from the user
     * @return Acknowledgement of the task creation.
     */
    public String handleTodo(String command) throws DukeInvalidDescriptionException {
        String[] parsed = Parser.splitSpace(command);
        if (parsed.length < 2 || parsed[1].equals("")) {
            throw new DukeInvalidDescriptionException();
        }
        ToDo todo = new ToDo(parsed[1]);
        this.taskList.addTask(todo);
        String storeTodo = todo.storedString() + "\n";
        try {
            storage.saveAppend(storeTodo);
        } catch (IOException e) {
            e.getMessage();
            System.out.println(ui.failure());
        }
        return ui.taskText(todo, taskList.getLength());
    }

    /**
     * Handles an event command, creating a corresponding <code>Event</code> object.
     *
     * @param command The command from the user
     * @return Acknowledgement of the task creation.
     */
    public String handleEvent(String command) throws DukeInvalidDescriptionException,
            DukeInvalidTimeException {
        String[] parsed = Parser.splitEvent(command);
        if (parsed.length < 1 || parsed[0].equals("")) {
            throw new DukeInvalidDescriptionException();
        } else if (parsed.length < 3  || parsed[1].equals("") || parsed[2].equals("")) {
            throw new DukeInvalidTimeException();
        }
        Event event = new Event(parsed[0], parsed[1], parsed[2]);
        this.taskList.addTask(event);
        String storeEvent = event.storedString() + "\n";
        try {
            storage.saveAppend(storeEvent);
        } catch (IOException e) {
            e.getMessage();
            System.out.println(ui.failure());
        }
        return ui.taskText(event, taskList.getLength());
    }

    /**
     * Handles a deadline command, creating a corresponding <code>Deadline</code> object.
     *
     * @param command The command from the user
     * @return Acknowledgement of the task creation.
     */
    public String handleDeadline(String command) throws DukeInvalidDescriptionException, DukeInvalidTimeException {
        String[] parsed = Parser.splitDeadline(command);
        if (parsed.length < 1 || parsed[0].equals("")) {
            throw new DukeInvalidDescriptionException();
        } else if (parsed.length < 2 || parsed[1].equals("")) {
            throw new DukeInvalidTimeException();
        }
        Deadline deadline = new Deadline(parsed[0], parsed[1]);
        this.taskList.addTask(deadline);
        String storeDeadline = deadline.storedString() + "\n";
        try {
            storage.saveAppend(storeDeadline);
        } catch (IOException e) {
            e.getMessage();
            System.out.println(ui.failure());
        }
        return ui.taskText(deadline, taskList.getLength());
    }

    /**
     * Handles a delete command, deleting the corresponding <code>Task</code>.
     *
     * @param command The command from the user
     * @return Acknowledgement of the task deletion.
     */
    public String handleDelete(String command) throws DukeInvalidDescriptionException, DukeInvalidIndexException {
        String[] parsed = Parser.splitSpace(command);
        if (parsed.length < 2) {
            throw new DukeInvalidDescriptionException();
        }
        boolean valid = true;
        try {
            int i = Integer.parseInt(parsed[1]);
        } catch (NumberFormatException nfe) {
            valid = false;
        }
        if (!valid) {
            throw new DukeInvalidDescriptionException();
        }
        int ind = Integer.parseInt(parsed[1]);
        if (ind > taskList.getLength()) {
            throw new DukeInvalidIndexException();
        }
        Task t = taskList.getTask(ind-1);
        taskList.remove(ind-1);
        try {
            storage.save(taskList.writeTaskList());
        } catch (IOException e) {
            e.getMessage();
            System.out.println(ui.failure());
        }
        return ui.removeText(t, taskList.getLength());
    }
}
