package dude.command;

import dude.Storage;
import dude.Ui;
import dude.exception.DudeException;
import dude.task.Task;
import dude.task.TaskList;

import java.util.ArrayList;

/**
 * Command to find task with description containing given substring.
 */
public class FindTaskCommand extends DudeCommand {
  private static final String SEARCH_RESULTS_PREFIX = "Here are the matching tasks in your list:\n";

  private static final String NO_SEARCH_RESULTS_MSG = "Couldn't find any tasks matching \"%s\"";

  /**
   * Substring to search.
   */
  private final String searchString;

  /**
   * Constructor for find task command.
   *
   * @param searchString Substring to search.
   */
  public FindTaskCommand(String searchString) {
    this.searchString = searchString;
  }

  /**
   * Finds tasks with description matching substring.
   */
  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) throws DudeException {
    ArrayList<Task> results = taskList.searchDescriptions(searchString);
    if (results.isEmpty()) {
      ui.printMessage(String.format(NO_SEARCH_RESULTS_MSG, searchString));
    } else {
      ui.printMessage(SEARCH_RESULTS_PREFIX + TaskList.displayList(results));
    }
  }

  /**
   * {@inheritDoc}
   *
   * @return False.
   */
  @Override
  public boolean isExit() {
    return false;
  }
}
