package records;

import parser.TaskParser;
import parser.TimeParser;
import storage.SaveData;
import task.*;

import java.util.ArrayList;

public class ChatRecord {
    private ArrayList<Task> chatRecords;
    private int counter;
    public ChatRecord() {
        chatRecords = SaveData.loadData();
        counter = chatRecords.size();
    }

    public void addTask(Task task) {
        chatRecords.add(task);
    }

    public Task addTodo(String name) {
        Task ret = new Todo(name);
        chatRecords.add(ret);
        counter++;
        saveChat();
        return ret;
    }

    public Task addDeadline(String name, String args) {
        Task ret = new Deadline(name, TimeParser.parseTime(args.trim()));
        chatRecords.add(ret);
        counter++;
        saveChat();
        return ret;
    }

    public Task addEvent(String name, String[] args) {
        Task ret = new Event(name, TimeParser.parseTime(args[0].trim()), TimeParser.parseTime(args[1].trim()));
        chatRecords.add(ret);
        counter++;
        saveChat();
        return ret;
    }

    public Task deleteTask(int n) {
        counter--;
        saveChat();
        return chatRecords.remove(n - 1);
    }

    public String listMessage() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < chatRecords.size(); i++) {
            ret.append(String.format("\t%d. %s\n", i + 1, chatRecords.get(i).toString()));
        }
        return ret.toString().stripTrailing();
    }

    private void saveChat() {
        Task[] temp = new Task[chatRecords.size()];
        temp = chatRecords.toArray(temp);
        SaveData.saveData(TaskParser.formatSave(temp));
    }

    public int getCount() {
        return counter;
    }

    public Task setMark(int n) {
        Task task = chatRecords.get(n - 1).mark();
        saveChat();
        return task;
    }

    public Task setUnmark(int n) {
        Task task = chatRecords.get(n - 1).unmark();
        saveChat();
        return task;
    }

    public String getTask(int n) {
        return chatRecords.get(n - 1).toString();
    }
}
