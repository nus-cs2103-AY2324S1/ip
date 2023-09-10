package seedu;

import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Command {
    private String command;
    private Ui ui;
    private boolean exit = false;
    private Storage storage;
    private TaskList tasks;
    private int index;
    public Command(String command, Ui ui, Storage storage, TaskList tasks) {
        this.command = command;
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    public Command(String command, Ui ui, Storage storage, TaskList tasks, int index) {
        this.command = command;
        this.ui = ui;
        this.storage = storage;
        this.index = index;
        this.tasks = tasks;
    }

    public ArrayList<String> execute() throws Exception {
        ArrayList<String> s = new ArrayList<>();
        try {
            if (command.equals("bye")) {
                s.add(this.ui.showByeMessage());
                this.storage.save(this.tasks);
            } else if (command.equals("list")) {
                s.add(this.ui.showTask(this.tasks));
            } else if (command.equals("delete")) {
                Task removed = this.tasks.remove(this.index);
                s.add(this.ui.removeTask(removed, this.tasks.getLen()));
            } else if (command.equals("mark")) {
                this.tasks.mark(this.index);
                s.add(this.ui.showMarked());
            } else if (command.startsWith("find")) {
                String keyword = command.substring(4).trim();
                ArrayList<Task> foundWords = this.tasks.find(keyword);
                s.add(this.ui.showFoundWords(foundWords));
            } else if (command.startsWith("remove")) {
                String keyword = command.substring(6).trim();
                ArrayList<Task> foundTasks = this.tasks.find(keyword);
                ArrayList<Task> removedTasks = this.tasks.specificRemove(foundTasks);
                s.add(this.ui.showMassDeleteSuccess(removedTasks));
            } else {
                Task curr = new Task(command.substring(command.indexOf(" ")),
                        command.substring(0, command.indexOf(" ")));
                s.add(this.ui.addTask(curr));
                this.tasks.add(curr);
            }
        } catch (Exception e) {
            throw new Exception("Some error occurred " + e.getMessage());
        }
        return s;

    }

    public boolean exit() {
        return this.exit;
    }
}
