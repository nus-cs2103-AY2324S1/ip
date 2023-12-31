package duke.commands;

import duke.exceptions.ErrorMessages;
import duke.exceptions.UnknownCommandException;
import duke.io.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to show a help message to the user.
 * The help message provides information about the available commands, date formats, and date-time formats.
 */
public class HelpCommand extends Command {

    /** The header message displayed at the beginning of the help output. */
    private static final String HEADER = "Here are the available commands and their respective function:";

    /** Prompt informing the user about the case-insensitivity of command keywords. */
    private static final String PROMPT = "All the command keywords are case insensitive!";

    private static final String[] DATETIME_FORMATS = {
        "MMM dd yyyy HHmm", "MMM dd yyyy HH:mm",
        "yyyy-MM-dd'T'HH:mm", "dd/MM/yyyy HHmm",
        "dd/MM/yyyy HH:mm", "yyyy/MM/dd HHmm",
        "yyyy/MM/dd HH:mm", "yyyy/MM/dd'T'HHmm",
        "yyyy-MM-dd HHmm", "yyyy-MM-dd HH:mm",
        "dd MMM yyyy HHmm", "dd MMM yyyy HH:mm",
        "MMM dd, yyyy HHmm", "MMM dd, yyyy HH:mm",
        "dd-MM-yyyy HH:mm", "dd.MM.yyyy HH:mm",
        "dd/MM/yy HH:mm", "yyyy-MM-dd'T'HH:mmZ",
        "MMM dd yyyy hh:mm a", "yyyy-MM-dd HH:mm:ss"
    };

    private static final String[] DATE_FORMATS = {
        "MMM dd yyyy", "yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd",
        "dd MMM yyyy", "MMM dd, yyyy", "dd-mm-yyyy",
        "dd/MM/yy", "yy-MM-dd", "dd.MM.yyyy"
    };


    /** Message displaying all valid date-time formats. */
    private static final String VALID_DATE_TIME_FORMAT =
            "Here are all the valid date time formats:\n\n"
                    + String.join(",\n", DATETIME_FORMATS);

    /** Message displaying all valid date formats. */
    private static final String VALID_DATE_FORMAT =
            "Here are all the valid date formats:\n\n"
                    + String.join(",\n", DATE_FORMATS);

    /** The specific type of help this command will display. */
    private final HelpType type;

    /**
     * Enumerated types to determine the type of help message to display:
     * either general command information, date format info, or date-time format info.
     */
    private enum HelpType {
        NORMAL, DATE, TIME
    }

    /**
     * Constructs a HelpCommand with the specified information.
     *
     * @param information Determines the specific type of help information to show.
     * @throws UnknownCommandException If the provided information is not recognized.
     */
    public HelpCommand(String information) throws UnknownCommandException {
        super();
        try {
            type = HelpType.valueOf(information.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException(ErrorMessages.INVALID_HELP_COMMAND_ERROR);
        }
    }

    /**
     * Executes the help command, generating and displaying the relevant help message.
     *
     * @param tasks   The list of tasks currently in the application.
     * @param ui      The UI instance, for sending output to the user.
     * @param storage The storage handler for saving/loading tasks.
     * @throws Exception If there's an error in generating or displaying the help message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (type == HelpType.NORMAL) {
            StringBuilder message = new StringBuilder();
            message.append(HEADER).append("\n\n");

            for (Type commandType : Type.values()) {
                message.append("- ").append(commandType.getCommand()).append(" : ")
                        .append(commandType.getDescription()).append("\n\n");
            }

            message.append(PROMPT);
            ui.addToResponse(message.toString());
        } else if (type == HelpType.DATE) {
            ui.addToResponse(VALID_DATE_FORMAT);
        } else if (type == HelpType.TIME) {
            ui.addToResponse(VALID_DATE_TIME_FORMAT);
        }
    }
}
