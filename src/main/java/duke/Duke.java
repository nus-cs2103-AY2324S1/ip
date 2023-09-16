package duke;

import javafx.application.Platform;

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
                    assert command != null;
                    if (parser.parse(command, ui, tasks, storage).equals("Goodbye. Hope to be of service again soon!"))
                    {
                        break;
                    };

                } catch (DukeException e) {
                    ui.printline();
                    System.out.println(e.getMessage());
                    ui.printline();
                }
            }
        }
    public String getResponse(String input) {
        try {

            String response = parser.parse(input, ui, tasks, storage).execute(input, ui, tasks, storage);
            assert response != null;

            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke("src/main/java/duke/data/duke.txt");
        duke.run();
    }
}




