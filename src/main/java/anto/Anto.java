package anto;

public class Anto {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Anto(String filePath) {
        ui = new Ui();
        storage = new Storage(ui, filePath);
        try {
            tasks = new TaskList(storage.loadSave());
            ui.setTaskList(tasks);
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    public void run() {
        parser = new Parser(ui, tasks);
        this.parser.readInputs();
    }

    public static void main(String[] args) {
        new Anto("data/anto.txt").run();
    }
}
