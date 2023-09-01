package duke.records;

import duke.parser.TaskParser;
import duke.storage.SaveData;
import duke.task.*;

import java.util.ArrayList;
import java.util.Optional;

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

    public Optional<String> findMessage(String findWord) {
        StringBuilder ret = new StringBuilder();
        for (Task task : chatRecords) {
            if (task.getName().contains(findWord)) {
                ret.append(String.format("\t%s\n", task));
            }
        }
        if (ret.length() <= 0) {
            return Optional.empty();
        }
        return Optional.of(ret.toString().stripTrailing());
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
