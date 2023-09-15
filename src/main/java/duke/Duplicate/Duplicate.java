package duke.Duplicate;

import duke.exception.DukeDuplicatesCommandException;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Duplicate {
    enum DuplicatesMode {
        ON,
        OFF,
        TEXT,
    }

    private DuplicatesMode duplicatesMode;

    public Duplicate() {
        duplicatesMode = DuplicatesMode.ON;
    }

    public String getMode() {
        return duplicatesMode.toString();
    }

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
    public boolean isDuplicates(Task task, TaskList taskList) throws DukeException {
        switch (duplicatesMode) {
            case ON:
                return isAbsoluteDuplicates(task,  taskList);
            case TEXT:
                return isTextDuplicates(task, taskList);
            case OFF:
                return false;
            default:
                throw new DukeDuplicatesCommandException("");
        }
    }

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
