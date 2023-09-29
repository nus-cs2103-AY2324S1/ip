package rock.logic.commands;

import rock.client.Rock;
import rock.logic.io.Parser;
import rock.tasks.Task;

/**
 * Representation of a command
 * to delete a task from the task list.
 * @author Alvis Ng (supermii2)
 */
public class CommandTaskDelete extends Command {
    /**
     * Constructor to create the
     * delete command
     * @param client Chatbot object
     */
    public CommandTaskDelete(Rock client) {
        super(client);
    }
    /**
     * Removes task from task list
     * @param input Contains data with index of removed task.
     * @throws IllegalArgumentException Thrown when invalid index is given.
     */
    @Override
    public String apply(Parser input) {
        String inputString = input.getDefaultString();
        try {
            // Displayed task list is 1-indexed but internal stored as 0-indexed
            int taskIdx = Integer.parseInt(inputString) - 1;
            Task removedTask = client.getTaskList().removeTask(taskIdx);
            this.client.saveFile();
            return String.format("Gotcha! This task has been removed:\n%s", removedTask.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR: Invalid number given!");
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("ERROR: No task exists at that number!");
        }
    }
}
