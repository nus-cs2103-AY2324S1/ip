package duke;

import java.time.format.DateTimeParseException;
import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.notes.Note;
import duke.notes.NoteList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Commands;
import duke.utils.Parser;
import duke.utils.Ui;

/**
 * Chatbot that takes in commands.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private NoteList notes;
    private Ui ui;

    /**
     * Constructor for creating a Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.readTaskFile());
        this.notes = new NoteList(storage.readNoteFile());
    }

    private String taskAdded(String input, Commands command) throws DukeException {
        if (command.equals(Commands.NOTE)) {
            Note n = NoteList.createNote(input);
            assert n != null : "Note was not created";
            notes.addNote(n);
            return ui.showNoteAdded(n.getNote());
        } else {
            Task t = TaskList.createTask(input, command, 0);
            assert t != null : "Task was not created";
            tasks.addTask(t);
            return ui.showTaskAdded(t.getTask());
        }
    }

    private String getList() {
        String output;
        if (tasks.isEmpty() && notes.isEmpty()) {
            return ui.showNoTasks() + "\n" + ui.showNoNotes();
        } else if (tasks.isEmpty()) {
            return ui.showNoTasks() + "\n" + ui.showNotes(notes.getNotesDes(1));
        } else {
            return ui.showTasks(tasks.getTasksDes(1), 0)
                    + "\n" + ui.showNotes(notes.getNotesDes(1));
        }
    }

    private String taskActions(String input, Commands command) throws DukeException {
        switch(command) {
        case UNMARK:
        case MARK:
            return tasks.changeTaskCompletion(input, command);
        case FIND:
            List<String> matchingTasks = tasks.findTask(input);
            return ui.showTasks(matchingTasks, 1);
        case DELETET:
            return tasks.deleteTask(input);
        case DELETEN:
            return notes.deleteNote(input);
        default:
            throw new InvalidInputException("Invalid input");
        }
    }

    /**
     * Returns the response to the user's input
     *
     * @param input The user's input
     * @return Toothless' response
     */
    public String getResponse(String input) {
        String output;
        Commands command = Parser.determineCommand(input);

        try {
            switch (command) {
            case TODO:
            case DEADLINE:
            case EVENT:
            case NOTE:
                output = taskAdded(input, command);
                break;
            case LIST:
                output = getList();
                break;
            case UNMARK:
            case MARK:
            case FIND:
            case DELETET:
            case DELETEN:
                output = taskActions(input, command);
                break;
            case BYE:
                String savedStatus = storage.saveToDisk(tasks.getTasksDes(0), notes.getNotesDes(0));
                output = savedStatus + "\n" + ui.farewell();
                break;
            case UNKNOWN:
            default:
                throw new InvalidInputException("Invalid input");
            }
        } catch (DukeException e) {
            output = ui.showDukeError(e);
        } catch (DateTimeParseException e) {
            output = ui.showDateError();
        } catch (Exception e) {
            output = ui.showGeneralError();
        }
        assert !output.isEmpty() : "No output received";
        return output;
    }
}
