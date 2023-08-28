package seedu.james;

import java.util.ArrayList;

public class James {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public James(String savePath) {
        Ui ui = new Ui();
        this.ui = ui;
        Storage storage = new Storage(savePath);
        this.storage = storage;
        try {
            ArrayList<Task> tasks = storage.load();
            this.tasks = new TaskList(tasks);
        } catch (LoadingException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }
    public static void main(String[] args) {
        new James("data/James.txt").run();
    }

    public void run() {
        ui.start(tasks);
        try {
            this.storage.save(tasks);
        } catch (SavingException e) {
            System.out.println("Error saving file");
        }
    }



}
