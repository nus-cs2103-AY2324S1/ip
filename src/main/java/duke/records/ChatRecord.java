package duke.records;

import java.util.ArrayList;

import duke.parser.TaskParser;
import duke.storage.SaveData;
import duke.task.Task;

public class ChatRecord {
    private ArrayList<Task> chatRecords;
    public ChatRecord() {
        chatRecords = new ArrayList<>();
    }

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

    public Task deleteTask(int n) {
        return chatRecords.remove(n - 1);
    }

    public String listMessage() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < chatRecords.size(); i++) {
            ret.append(String.format("\t%d. %s\n", i + 1, chatRecords.get(i).toString()));
        }
        return ret.toString().stripTrailing();
    }

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
