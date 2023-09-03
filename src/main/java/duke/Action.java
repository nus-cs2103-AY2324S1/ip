package duke;


import javafx.scene.layout.Pane;

/**
 * Functional interface for an action that takes in a TaskList and Storage object and returns nothing.
 */
public interface Action {

  public void execute(TaskList taskList, Storage storage, Pane vbox) throws WrongIndexException, EmptyBodyException, InvalidDateException;
}
