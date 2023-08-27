public interface Action {

  public boolean execute(TaskList taskList, Storage storage) throws WrongIndexException, EmptyBodyException, InvalidDateException;
}
