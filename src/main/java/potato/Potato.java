package potato;

import java.io.IOException;

import potato.command.*;

public class Potato {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Potato() {
        storage = new Storage("./Potato.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTask());
            System.out.println("here's the saved list");
        } catch (IOException e) {
            System.out.println("No saved list");
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input);
            response += c.execute(tasks, storage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}
