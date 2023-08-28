package duke;

import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private UI ui;
    private TaskList tasks;

    private Parser parser;



    public Duke(String filePath) {
        ui = new UI("Alfred");
        storage = new Storage(filePath);
        tasks = new TaskList();
        Storage.preloadFromFile(tasks);
        parser = new Parser();

    }

    public void run() {
            ui.welcomeMessage();
            boolean isExit = false;
            while (!isExit) {
                try {
                    String command = ui.readCommand();
                    ui.printline();
                    parser.parse(command, ui, tasks, storage);

                } catch (DukeException e) {
                    ui.printline();
                    System.out.println(e.message);
                    ui.printline();
                }
            }
        }



    public static void main(String[] args) {
        Duke duke = new Duke("src/main/java/duke/data/duke.txt");
        duke.run();

    }
}




