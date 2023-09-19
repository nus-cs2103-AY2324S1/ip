package hachi;

import java.time.LocalDate;
import java.util.ArrayList;

import hachi.exceptions.DateFormatWrongException;
import hachi.exceptions.HachiException;
import hachi.exceptions.TooManyArgumentsException;

/**
 * Represents the "search-date" command.
 */
public class SearchdateCommand extends Command {

    public static final String COMMAND_WORD = "search-date";

    private TaskList taskList;

    /**
     * Constructor method for the SearchdateCommand class.
     * @param arguments The arguments passed in by the user.
     * @param taskList The task list passed in by the user.
     */
    public SearchdateCommand(String[] arguments, TaskList taskList) {
        super(arguments);
        this.taskList = taskList;
    }

    @Override
    public String execute() throws HachiException {
        checkArgumentLength(getArguments().length);
        LocalDate searchDate = LocalDate.parse(getArguments()[0]);
        ArrayList<Task> filteredDates = new ArrayList<>();
        taskList.iter(task -> {
            if (task.isDateWithinRange(searchDate)) {
                filteredDates.add(task);
            }
        });
        return new TaskList(filteredDates).toString();
    }

    @Override
    protected void checkArgumentLength(int argumentLength) throws HachiException {
        if (argumentLength > 1) {
            throw new TooManyArgumentsException(SearchdateCommand.COMMAND_WORD, 1, argumentLength);
        }
        if (argumentLength < 1) {
            throw new DateFormatWrongException("");
        }
    }
}
