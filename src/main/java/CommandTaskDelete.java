/**
 * Representation of a command
 * to delete a task from the task list.
 * 
 * @author Alvis Ng (supermii2)
 */
public class CommandTaskDelete extends Command {
    @Override
    /**
     * Removes task from task list
     * @param input Contains data with index of removed task.
     * @throws IllegalArgumentException Thrown when invalid index is given.
     */
    public void accept(Parser input) {
        String inputString = input.getDefaultString();
        TaskList taskList = Rock.taskList;
        try {
            int taskIdx = Integer.parseInt(inputString);
            if (taskIdx < 1 || taskIdx > taskList.size()) {
                throw new IllegalArgumentException("Invalid index given!");
            } else {
                Task removedTask = Rock.taskList.removeTask(taskIdx - 1);
                Save.saveSaveFile();
                Rock.respond("Task successfully removed!\n" + removedTask);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid index given!");
        }        
    }
}
