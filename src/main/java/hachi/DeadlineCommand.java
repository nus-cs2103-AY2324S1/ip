package hachi;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import hachi.exceptions.DateFormatWrongException;
import hachi.exceptions.EmptyDeadlineException;
import hachi.exceptions.EmptyTaskException;
import hachi.exceptions.HachiException;
import hachi.exceptions.NoDeadlineException;

/**
 * Represents the "deadline" command.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    private TaskList taskList;

    private Storage storage;

    /**
     * Constructor method for the DeadlineCommand class.
     * @param arguments The arguments passed in by the user.
     * @param taskList The task list passed in by the user.
     * @param storage The storage used by the app.
     */
    public DeadlineCommand(String[] arguments, TaskList taskList, Storage storage) {
        super(arguments);
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public String execute() throws HachiException {
        checkArgumentLength(getArguments().length);
        String[] arguments = getArguments();
        int byIndex = Parser.getWordIndex("/by", arguments);
        if (byIndex == -1) {
            throw new NoDeadlineException();
        }
        if (byIndex == arguments.length - 1) {
            throw new EmptyDeadlineException("deadline");
        }
        String deadlineTask = String.join(" ",
                Arrays.copyOfRange(arguments, 0, byIndex));
        String deadlineDate = String.join(" ",
                Arrays.copyOfRange(arguments, byIndex + 1, arguments.length));
        try {
            Deadline dl = new Deadline(deadlineTask, LocalDate.parse(deadlineDate));
            taskList.add(dl);
            storage.updateTaskFile(taskList);
            return "Got it. I've added this task:\n   " + dl
                    + String.format("\nNow you have %d tasks in the list.", taskList.size());
        } catch (DateTimeParseException e) {
            throw new DateFormatWrongException(deadlineDate);
        }
    }

    @Override
    protected void checkArgumentLength(int argumentLength) throws HachiException {
        if (argumentLength < 1) {
            throw new EmptyTaskException(DeadlineCommand.COMMAND_WORD);
        }
    }
}
