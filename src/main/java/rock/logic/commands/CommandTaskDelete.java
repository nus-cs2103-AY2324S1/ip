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
            int taskIdx = Integer.parseInt(inputString);
            Task removedTask = client.getTaskList().removeTask(taskIdx - 1);
            this.client.saveFile();
            return ("Task successfully removed!\n" + removedTask);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid index given!");
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Index does not exist!");
        }
    }
}
