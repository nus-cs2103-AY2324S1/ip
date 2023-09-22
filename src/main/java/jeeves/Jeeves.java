package jeeves;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javafx.application.Application;
import jeeves.exception.DeletedIdException;
import jeeves.exception.MissingByException;
import jeeves.exception.MissingDescriptionException;
import jeeves.exception.MissingFromException;
import jeeves.exception.MissingIdException;
import jeeves.exception.MissingToException;
import jeeves.exception.NotIntegerIdException;
import jeeves.exception.OutOfBoundIdException;
import jeeves.note.Note;
import jeeves.note.NoteList;
import jeeves.parser.Parser;
import jeeves.storage.Storage;
import jeeves.task.Deadline;
import jeeves.task.Event;
import jeeves.task.Task;
import jeeves.task.TaskList;
import jeeves.task.Todo;
import jeeves.ui.Ui;
/**
 * Contains the main method and primary logic for Jeeves.
 */
public class Jeeves {

    private static final String RELATIVE_PATH_DATA_DIRECTORY = "data";
    private static final String RELATIVE_PATH_DATA_FILE = "data/JeevesData.txt";
    private static final String RELATIVE_PATH_NOTE_FILE = "data/JeevesNote.txt";
    private static final Storage storage = new Storage(RELATIVE_PATH_DATA_DIRECTORY, RELATIVE_PATH_DATA_FILE,
                                                RELATIVE_PATH_NOTE_FILE);
    private static final TaskList tasks = new TaskList(storage.readTasklistFromFile());
    private static final NoteList notes = new NoteList(storage.readNoteListFromFile());
    private static final Parser parser = new Parser();

    /**
     * Launches the GUI and executes the main logic of Jeeves.
     *
     * @param args Optional command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Ui.class);
    }

    /**
     * Contains the primary logic for Jeeves.
     * Constantly loops and waits for a new line of user input.
     * Processes the input depending on what was entered.
     */
    public static String processInput(String inputLine) {
        // Reads the user input and parses the relevant tokens for use
        ArrayList<String> tokens;
        try {
            tokens = parser.parseUserInput(inputLine);
        } catch (MissingByException | MissingDescriptionException | MissingFromException | MissingToException
                 | OutOfBoundIdException | NotIntegerIdException | MissingIdException e) {
            return e.getMessage();
        }
        assert !tokens.isEmpty();

        String currentCommand = tokens.get(0);
        // Performs a different action depending on the input received
        // Unless a specific pre-defined command is received, the program will
        // print a default error message.
        switch (currentCommand) {
        case "list":
            return processListCommand();
        case "mark":
            return processMarkCommand(tokens);
        case "unmark":
            return processUnmarkCommand(tokens);
        case "delete":
            return processDeleteCommand(tokens);
        case "todo":
            return processTodoCommand(tokens);
        case "deadline":
            return processDeadlineCommand(tokens);
        case "event":
            return processEventCommand(tokens);
        case "find":
            return tasks.searchFor(tokens.get(1));
        case "note":
            return processNoteCommand(tokens);
        case "list notes":
            return processListNotesCommand();
        case "bye":
            processByeCommand();
            break;
        default:
                // Fallthrough
        }

        // By default, informs the user that the command is not recognized.
        return "Apologies Master, I am unable to understand that command.\n"
                        + "I will improve myself to better serve you in the future.\n";
    }

    private static String processListCommand() {
        // Displays a different message if no task is being tracked
        StringBuilder sb = new StringBuilder();
        if (Task.getTaskCount() == 0) {
            return "I am not currently tracking anything for you Master\n";
        } else {
            sb.append("This is what I am tracking for you Master\n");
        }

        // Displays the current list of tasks tracked and their status
        for (int i = 1; i <= Task.getTaskCount(); i++) {
            if (tasks.getTask(i) != null) {
                sb.append(tasks.getTask(i).toString());
                sb.append("\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    private static String processMarkCommand(ArrayList<String> tokens) {
        // Get the id and try to process the marking
        int id = Integer.parseInt(tokens.get(1));

        try {
            if (tasks.getTask(id) == null) {
                // If the id to be marked belongs to a deleted task (null), throws the DeletedIdException
                throw new DeletedIdException();
            }

            tasks.markTask(id);
            return "Understood, I have marked the following task as done:\n"
                    + "    "
                    + tasks.getTask(id).toString()
                    + "\n";
        } catch (DeletedIdException e) {
            return e.getMessage();
        }
    }

    private static String processUnmarkCommand(ArrayList<String> tokens) {
        int id = Integer.parseInt(tokens.get(1));

        try {
            if (tasks.getTask(id) == null) {
                // If the id to be marked belongs to a deleted task (null), throws the DeletedIdException
                throw new DeletedIdException();
            }

            tasks.unmarkTask(id);
            return "Understood, I have marked the following task as not done:\n"
                    + "    "
                    + tasks.getTask(id).toString()
                    + "\n";
        } catch (DeletedIdException e) {
            return e.getMessage();
        }
    }

    private static String processDeleteCommand(ArrayList<String> tokens) {
        int id = Integer.parseInt(tokens.get(1));

        try {
            if (tasks.getTask(id) == null) {
                // If the id to be marked belongs to a deleted task (null), throws the DeletedIdException
                throw new DeletedIdException();
            }

            String deletedTask = tasks.getTask(id).toString();
            tasks.setTask(id, null);
            return "Understood, I have deleted the following task:\n"
                    + "    "
                    + deletedTask
                    + "\n";
        } catch (DeletedIdException e) {
            return e.getMessage();
        }
    }

    private static String processTodoCommand(ArrayList<String> tokens) {
        // Adds the To.do normally to the task list
        Todo newTodo = new Todo(tokens.get(1));
        tasks.addTaskAtIndex(Task.getTaskCount(), newTodo);
        return "Task added:\n"
                + "    "
                + newTodo
                + "\n";
    }

    private static String processDeadlineCommand(ArrayList<String> tokens) {
        // Checks if the Date input is in the accepted format
        try {
            LocalDate deadline = LocalDate.parse(tokens.get(2));
            // Adds the 'Deadline' Task to the task list
            Deadline newDeadline = new Deadline(tokens.get(1), deadline);
            tasks.addTaskAtIndex(Task.getTaskCount(), newDeadline);
            return "Deadline added:\n"
                    + "    "
                    + newDeadline + "\n";
        } catch (DateTimeParseException e) {
            return "Please enter the date in a yyyy-mm-dd format";
        }
    }

    private static String processEventCommand(ArrayList<String> tokens) {
        // Adds the 'Event' Task to the task list
        Event newEvent = new Event(tokens.get(1), tokens.get(2), tokens.get(3));
        tasks.addTaskAtIndex(Task.getTaskCount(), newEvent);
        return "Event added:\n"
                + "    "
                + newEvent
                + "\n";
    }

    private static String processNoteCommand(ArrayList<String> tokens) {
        // Adds the Note to the note list
        Note newNote = new Note(tokens.get(1));
        notes.add(newNote);
        return "Note added to your list:\n"
                    + "    "
                    + newNote
                    + "\n";
    }

    private static String processListNotesCommand() {
        // Displays a different message if no notes are being tracked
        StringBuilder sb = new StringBuilder();
        if (Note.getNoteCount() == 0) {
            return "I am not tracking any of your notes, Master";
        } else {
            sb.append("This is what I am tracking for you Master:\n");
        }

        sb.append(notes.getNotelistDataAsString());
        return sb.toString();
    }

    private static void processByeCommand() {
        // Before the actual termination of the program, writes the current task list to the external file.
        storage.writeTasklistToFile(tasks.getTaskListDataAsString());
        // Before the actual termination of the program, writes the current note list to the external file.
        storage.writeNoteListToFile(notes.getNotelistDataAsString());

        // Terminates the application
        System.exit(0);
    }
}
