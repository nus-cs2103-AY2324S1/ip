package seedu;

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

    public boolean execute() throws Exception {
        try {
            if (command.equals("bye")) {
                this.ui.showByeMessage();
                this.storage.save(this.tasks);
                return true;
            } else if (command.equals("list")) {
                this.ui.showTask(tasks);
            } else if (command.equals("delete")) {
                Task removed = this.tasks.remove(this.index);
                this.ui.removeTask(removed, this.tasks.getLen());
            } else if (command.equals("mark")) {
                this.tasks.mark(this.index);
                this.ui.showMarked();
            } else if (command.startsWith("find")) {
                String keyword = command.substring(4).trim();
                ArrayList<Task> foundWords = this.tasks.find(keyword);
                this.ui.showFoundWords(foundWords);
            } else {
                Task curr = new Task(command.substring(command.indexOf(" ")),
                        command.substring(0, command.indexOf(" ")));
                this.ui.addTask(curr);
                this.tasks.add(curr);
            }
        } catch (Exception e) {
            throw new Exception("Some error occurred " + e.getMessage());
        }
        return false;

    }

    public boolean exit() {
        return this.exit;
    }
}
