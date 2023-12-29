package juke.commands;

import juke.commons.enums.SortOrderEnum;
import juke.commons.enums.SortTypeEnum;
import juke.commons.exceptions.arguments.JukeIllegalArgumentException;
import juke.responses.Dialog;
import juke.responses.Response;
import juke.tasks.TaskList;

/**
 * Action that sorts the {@code TaskList}.
 */
public class JukeSortListCommand extends JukeCommand {
    /** {@code SortOrderEnum} to sort the {@code TaskList} by (order). */
    private final SortOrderEnum sortOrder;

    /** {@code SortTypeEnum} to sort the {@code TaskList} on (fields). */
    private final SortTypeEnum sortType;

    /** {@code TaskList} to manage all tasks. */
    private final TaskList taskList;

    /**
     * Constructs a sort list command.
     *
     * @param sortOrder {@code SortOrderEnum} enum that describes the order of sorting
     * @param sortType {@code SortTypeEnum} enum that describes the type of sorting
     * @param taskList {@code TaskList} object to sort
     */
    public JukeSortListCommand(SortOrderEnum sortOrder, SortTypeEnum sortType, TaskList taskList) {
        this.sortOrder = sortOrder;
        this.sortType = sortType;
        this.taskList = taskList;
    }

    /**
     * Invokes an action when the command is executed. Note that sorting the task list is
     * inherently destructive, and the initial order of the task list will be destroyed
     * after the sort.
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @return {@code Response} object composed with response from Juke or the user
     * @throws JukeIllegalArgumentException if there are any errors encountered when sorting
     */
    @Override
    public Response execute(Response response) {
        this.taskList.sort(this.sortOrder, this.sortType);
        return response.with(Dialog.ofJuke("Your task list is now sorted!\n\n" + this.taskList));
    }
}
