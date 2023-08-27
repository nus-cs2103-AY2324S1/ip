package duke;


/**
 * Functional interface for an action that takes in a TaskList and Storage object and returns nothing.
 */
public interface Action {

  public boolean execute(TaskList taskList, Storage storage) throws WrongIndexException, EmptyBodyException, InvalidDateException;
}
