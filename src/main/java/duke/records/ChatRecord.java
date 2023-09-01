package duke.records;

import java.util.ArrayList;

import duke.parser.TaskParser;
import duke.storage.SaveData;
import duke.task.Task;

/**
 * Records all tasks.
 * @author Toh Li Yuan (A0255811H)
 */
public class ChatRecord {
    private ArrayList<Task> chatRecords;
    public ChatRecord() {
        chatRecords = new ArrayList<>();
    }

    /**
     * Load the data from local storage to the current records instance.
     *
     * @return the status of the import.
     */
    public String loadData() {
        ArrayList<Task> temp = SaveData.loadData();
        if (temp.size() >= 0) {
            chatRecords = temp;
            return "Save loaded successfully!";
        } else {
            return "No valid save found! Starting a new instance...";
        }
    }

    public void addTask(Task task) {
        chatRecords.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param n the task number of the task in the records.
     * @return the removed task.
     */
    public Task deleteTask(int n) {
        return chatRecords.remove(n - 1);
    }

    /**
     * Lists all tasks in the record.
     *
     * @return the string representation of the task.
     */
    public String listMessage() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < chatRecords.size(); i++) {
            ret.append(String.format("\t%d. %s\n", i + 1, chatRecords.get(i).toString()));
        }
        return ret.toString().stripTrailing();
    }

    /**
     * Returns the string representation for the save file.
     *
     * @return the string representation for the save file.
     */
    public String toSave() {
        Task[] temp = new Task[chatRecords.size()];
        temp = chatRecords.toArray(temp);
        return TaskParser.formatSave(temp);
    }

    public int getCount() {
        return chatRecords.size();
    }

    public Task setMark(int n) {
        Task task = chatRecords.get(n - 1).mark();
        return task;
    }

    public Task setUnmark(int n) {
        Task task = chatRecords.get(n - 1).unmark();
        return task;
    }
}
