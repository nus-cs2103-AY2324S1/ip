package duke.command;

import duke.data.storage.Store;

    
public class ListCommand implements Command{
    @Override
    /**
     * Lists all tasks in the task list.
     * @param input String input from user, which should be "list".
     */
    public String execute(String input) {
        Store s = Store.getInstance();
        return "Here are the tasks in your list:\n" + s;
    }
}
