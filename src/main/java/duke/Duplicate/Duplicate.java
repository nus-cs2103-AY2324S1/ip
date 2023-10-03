package duke.Duplicate;

import duke.exception.DukeDuplicatesCommandException;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class to handle duplicates
 *
 * @author marioalvaro
 */
public class Duplicate {
    enum DuplicatesMode {
        ON,
        OFF,
        TEXT,
    }

    private DuplicatesMode duplicatesMode;

    /**
     * Constructor for duplicates class.
     */
    public Duplicate() {
        duplicatesMode = DuplicatesMode.ON;
    }

    /**
     * To get the current mode
     * @return String of the mode.
     */
    public String getMode() {
        return duplicatesMode.toString();
    }

    /**
     * Update the duplicate mode.
     * @param command string command to change the mode.
     */
    public void updateCheckDuplicates(String command) {
        if (command.equals("on")) {
            this.duplicatesMode = DuplicatesMode.ON;
        } else if (command.equals("text")) {
            this.duplicatesMode = DuplicatesMode.TEXT;
        } else {
            assert command.equals("off") : "Duplicates Command word is wrong!";
            this.duplicatesMode = DuplicatesMode.OFF;
        }
    }

    /**
     * Check if there are duplicates according to the duplicate mode.
     * @param task The task to check the duplicate.
     * @param taskList Tasklist that may contain duplicates.
     * @return boolean about the existence of duplicates.
     * @throws DukeException of command not correct
     */
    public boolean isDuplicates(Task task, TaskList taskList) throws DukeException {
        switch (duplicatesMode) {
        case ON:
            return isAbsoluteDuplicates(task, taskList);
        case TEXT:
            return isTextDuplicates(task, taskList);
        case OFF:
            return false;
        default:
            throw new DukeDuplicatesCommandException("");
        }
    }

    /**
     * check if there is absolute duplicates of the task
     * @param task the task to check the duplicate
     * @param taskList TaskList that may contain duplicates
     * @return boolean about the existence of the duplicates.
     */
    private boolean isAbsoluteDuplicates(Task task, TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) == null) {
                break;
            }
            if (task.equals(taskList.get(i))) {
                return true;
            }

        }
        return false;
    }

    /**
     * CHeck if the text stored is duplicates.
     * @param task the task to check with
     * @param taskList The taskList to check with
     * @return the existence of text duplicates.
     */
    private boolean isTextDuplicates(Task task, TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) == null) {
                break;
            }
            if (task.equalsText(taskList.get(i))) {
                return true;
            }

        }
        return false;
    }
}
