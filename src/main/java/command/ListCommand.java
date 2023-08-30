package command;

import data.storage.Store;

    
public class ListCommand implements Command{
    @Override
    /**
     * Lists all tasks in the task list.
     * @param input String input from user, which should be "list".
     */
    public void execute(String input) {
        Store s = Store.getInstance();
        System.out.print("Here are the tasks in your list:" + "\n" + s);
    }
}
