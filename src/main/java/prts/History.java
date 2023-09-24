package prts;

import java.util.ArrayList;

/**
 * Represents a history of the past TaskLists made at any point by the user, in the current session.
 * Is added to by commands that modify the TaskList (Add, Delete, Mark, Unmark, Undo) and is used
 * by Undo to retrieve past states.
 */
public class History {

    /**
     * ArrayList that maintains the past states.
     */
    private final ArrayList<TaskList> history;

    public History() {
        history = new ArrayList<>();
    }

    /**
     * Adds a TaskList to the list of previous states.
     * @param taskList The TaskList to be stored.
     */
    public void addToHistory(TaskList taskList) {
        if (taskList.getSize() == 0) {
            history.add(new TaskList());
        } else {
            history.add(new TaskList(taskList));
        }
    }

    /**
     * Undoes one or more commands the user has input in the current session.
     * @param undoCount The number of commands to undo. If the input is null, undoes all past inputs.
     * @param taskList The main TaskList of the program.
     * @return The acknowledgement message to be displayed to the user.
     */
    public String undo(Integer undoCount, TaskList taskList) throws OutOfRangeException {
        if (undoCount == null) {
            taskList.mirror(history.get(0));
            history.subList(1, history.size()).clear();
            return "All actions undone.";
        }

        int undoIndex = history.size() - undoCount - 1;
        if (undoCount <= 0 || undoIndex < 0) {
            throw new OutOfRangeException();
        }
        taskList.mirror(history.get(undoIndex));
        if (history.size() > undoIndex) {
            history.subList(undoIndex + 1, history.size()).clear();
        }
        return undoCount + " actions undone.";
    }

    /**
     * Returns a string representation of the history.
     * @return A string representation of the history.
     */
    public String toString() {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (TaskList taskList : history) {
            stringBuilder.append("index ");
            stringBuilder.append(i);
            stringBuilder.append(": ");
            stringBuilder.append(taskList);
            stringBuilder.append("\n");
            i++;
        }
        return stringBuilder.toString();
    }

}
