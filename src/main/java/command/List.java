package command;

import data.storage.Store;

public class List implements Command{
    /**
     * Lists all tasks in the task list.
     * @param input String input from user, which should be "list".
     */
    @Override
    public void execute(String input) {
        Store s = Store.getInstance();
        System.out.print("Here are the tasks in your list:" + "\n" + s);
    }
}
