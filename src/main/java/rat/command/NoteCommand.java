package rat.command;

import static rat.io.RatPrinter.printWithLines;

import rat.notes.RatNoteManager;

/**
 * This class encapsulates a command that handles the user's notes.
 * This command can add and delete notes.
 *
 * @author Keagan
 */
public class NoteCommand extends RatCommand {

    /**
     * The user input passed into the NoteCommand.
     */
    private final String input;

    /**
     * The type of NoteCommand.
     * Can be either ADD_NOTE, DELETE_NOTE or INVALID.
     */
    private final CommandType noteCommandType;

    /**
     * Constructor for a NoteCommmand Object.
     * Parses the user input and initialises the NoteCommand object.
     * @param ratNoteManager The RatNoteManager object used to store and process the user's notes.
     * @param input The user input passed into the NoteCommand.
     */
    public NoteCommand(RatNoteManager ratNoteManager, String input) {
        super(ratNoteManager);
        this.input = input;
        int commandTypeIndex = 1;
        String[] inputs = input.split(" ");
        if (inputs.length < 2) {
            this.noteCommandType = CommandType.INVALID;
        } else {
            switch (inputs[commandTypeIndex]) {
            case "/add":
                this.noteCommandType = CommandType.ADD_NOTE;
                break;
            case "/delete":
                this.noteCommandType = CommandType.DELETE_NOTE;
                break;
            default:
                this.noteCommandType = CommandType.INVALID;
                break;
            }
        }
    }

    /**
     * Adds a note to the note list.
     * Parses the user input to get the note to be added.
     * @return The response to the user echoing a successful note creation, or the reason for an unsuccessful one.
     */
    public String addNote() {
        try {
            int noteAddParamsStart = 10;
            String noteParams = this.input.substring(noteAddParamsStart);
            return this.ratNoteManager.addNote(noteParams);
        } catch (StringIndexOutOfBoundsException e) {
            return "Note cannot be empty";
        }
    }

    /**
     * Deletes a note from the note list.
     * Parses the user input to get the index of the note to be deleted.
     * @return The response to the user echoing a successful note deletion, or the reason for an unsuccessful one.
     */
    public String deleteNote() {
        try {
            int noteIndexToDelete = 2;
            return this.ratNoteManager.deleteNote(Integer.parseInt(this.input.split(" ")[noteIndexToDelete]));
        } catch (NumberFormatException e) {
            return "Note index must be an integer";
        } catch (IndexOutOfBoundsException e) {
            return "Note index must be within the range of the note list";
        }
    }

    @Override
    public String getResponse() {
        switch (this.noteCommandType) {
        case ADD_NOTE:
            return this.addNote();
        case DELETE_NOTE:
            return this.deleteNote();
        case INVALID:
            return "Note command must be of the form: note /add <note> or note /delete <index>";
        default:
            return "";
        }
    }

    @Override
    public void execute() {
        switch (this.noteCommandType) {
        case ADD_NOTE:
            this.addNote();
            break;
        case DELETE_NOTE:
            this.deleteNote();
            break;
        default:
            printWithLines("Note command must be of the form: note /add <note> or note /delete <index>");
        }
    }
}
