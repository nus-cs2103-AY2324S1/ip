package duke;

import java.util.ArrayList;

/**
 * The Personal Assistant that keeps a todo list for the user.
 * Users can input multiple commands to use this personal assistant.
 *
 * Different tasks can be input and stored into the list. This includes
 * Todos, Deadlines and Events. Tasks can be marked and unmarked to
 * indicate completion of such tasks.
 */
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
                    if (parser.parse(command, ui, tasks, storage) == -1) {
                        break;
                    };

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




