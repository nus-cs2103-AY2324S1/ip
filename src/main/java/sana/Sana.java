package sana;

import java.util.Scanner;

public class Sana {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Sana(String filePath) {
        ui = new Ui(new Scanner(System.in));
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SanaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Sana("/Users/ariellacallista/Desktop/SanaTasks.txt").run();
    }
}


