package sidtacphi;

import sidtacphi.parser.Parser;
import sidtacphi.storage.Storage;
import sidtacphi.task.TaskList;

/**
 * Sidtacphi is the main class for the Sidtacphi bot.
 */
public class Sidtacphi {
    private static TaskList taskList = new TaskList();

    /**
     * Constructs the Sidtacphi object.
     */
    public Sidtacphi() {
        taskList = Storage.readJson("./stored/tasks.json");
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        return Parser.parseInput(taskList, input);
    }

    /**
     * Saves taskList as json.
     */
    public void saveTaskList() {
        Storage.saveAsJson(taskList, "./stored/tasks.json");
    }
}
