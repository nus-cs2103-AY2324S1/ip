package sisyphus;

import sisyphus.task.TaskList;

public class Sisyphus {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Sisyphus() {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.loadData();
        parser = new Parser();
    }

    /**
     * Driver function to run all components.
     */
    public void run() {
        ui.greet();
        boolean isChatting = true;
        while (isChatting) {
            try {
                String fullCommand = ui.readLine();
                parser.runCommand(fullCommand, tasks, storage, ui);
                isChatting = parser.getActiveStatus();
            } catch (SisyphusException e){
                System.out.println(e.getMessage());
            }
        }
        ui.exit();
    }
    public static void main(String[] args) {
        Sisyphus sisyphus = new Sisyphus();
        sisyphus.run();
    }

}
