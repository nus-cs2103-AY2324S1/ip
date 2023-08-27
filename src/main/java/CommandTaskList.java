/**
 * Representation of a command
 * to list all tasks in list.
 * 
 * @author Alvis Ng (supermii2)
 */
public class CommandTaskList extends Command{
    @Override
    /**
     * Removes task from task list.
     * @param input Unused
     * @throws IllegalArgumentException Thrown when invalid index is given.
     */
    public void accept(Parser input) {
        if (Rock.taskList.size() == 0) {
            Rock.respond("Task list is empty!");
        } else {
            int counter = 1;
            String response = "Task List: ";
            for (Task task:Rock.taskList) {
                response += "\n" + Integer.toString(counter) + ". " + task.toString();
                counter++;
            }
            Rock.respond(response);
        }
    }
}
