package duke.assets.commands;

import duke.data.TaskList;
import duke.assets.tasks.Todo;

public class CreateTodoCommand extends CommandAbstract {
    public CreateTodoCommand(String input) {
        super(input);
    }

    @Override
    protected boolean isValid() {
        try {
            String[] delimitedBySpace = this.input.split(" ");
            String information = delimitedBySpace[1];
            return true;
        } catch (IndexOutOfBoundsException indexExcept) {
            System.out.println("ChadGPT: Please include information about the task you would like to add.");
        }
        return false;
    }

    @Override
    protected void completeOperation(TaskList tasklist) {
        tasklist.addTask(new Todo(this.input.substring(5)));
    }
}
