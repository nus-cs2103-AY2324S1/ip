package tasks;

import ui.Ui;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<String> tasks;

    protected TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addItem(String item) {
        Ui.wrapPrintWithHorizontalRules(String.format("added: %s\n", item));
        this.tasks.add(item);
    }

    public void list() {
        System.out.print(Ui.HORIZONTAL_RULE);
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, this.tasks.get(i));
        }
        System.out.print(Ui.HORIZONTAL_RULE + "\n");
    }

    public static TaskList newTaskList() {
        return new TaskList();
    }
}
