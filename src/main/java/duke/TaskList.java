package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> fullList;
    Ui ui;
    private final static String line = "------------------------------------";

    public TaskList() {
        this.fullList = new ArrayList<>();
        this.ui = new Ui();
    }

    public TaskList(ArrayList<Task> fullList) {
        this.fullList = fullList;
        this.ui = new Ui();
    }

    public void addToList(Task task) {
        this.fullList.add(task);
        ui.showAddMessage(task, this.fullList.size());
    };

    public void deleteFromList(int i) {
        if (i >= 0 && i < fullList.size()) {
            Task item = fullList.get(i);
            this.fullList.remove(item);
            ui.showDeleteMessage(item, this.fullList.size());
        } else {
            ui.showNoItemMessage();
        }
    }

    public void markItem(int index) {
        if (index >= 0 && index < fullList.size()) {
            Task curr = fullList.get(index);
            curr.markDone();
            ui.showMarkMessage(curr);
        } else {
            ui.showNoItemMessage();
        }
    }

    public void unMarkItem(int index) {
        if (index >= 0 && index < fullList.size()) {
            Task curr = fullList.get(index);
            curr.markNotDone();
            ui.showUnmarkMessage(curr);
        } else {
            ui.showNoItemMessage();
        }
    }

    public ArrayList<Task> getTask() {
        return this.fullList;
    }

    public int getSize() {
        return this.fullList.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fullList.size(); i++) {
            int index = i + 1;
            stringBuilder.append(index).append(". ")
                    .append(fullList.get(i).toString()).append("\n");
        }
        return line + "\n" + stringBuilder.toString() + "\n" + line;
    }
}
