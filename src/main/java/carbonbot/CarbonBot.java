package carbonbot;

import java.io.IOException;

import carbonbot.command.Command;

/**
 * CarbonBot is a simple chatbot that helps to keep track of various things such as tasks.
 */

public class CarbonBot {
    private final String saveFilePath;
    private final Ui ui;
    private final Storage storage;
    private boolean shouldExit = false;
    private TaskList tasks;


    public CarbonBot() {
        this("./data/tasks.txt");
    }

    /**
     * Constructs a CarbonBot object that will read and write its data to the specified file path.
     *
     * @param filePath Path to save file
     */
    public CarbonBot(String filePath) {
        this.saveFilePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException | DukeException ex) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Executes CarbonBot to start running
     */
    public void run() {
        this.ui.showGreetings();
        boolean isExit = false;
        while (!isExit) {
            String input = this.ui.getNextInput();

            // Ignore if the input was empty
            if (input.isBlank()) {
                continue;
            }

            try {
                ui.printDivider();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                continue;

            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.printDivider();
            }
        }
    }

    /**
     * Returns whether the bot is ready to be terminated.
     * @return Boolean indicating if the bot received an ExitCommand
     */
    public boolean shouldExit() {
        return this.shouldExit;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                this.shouldExit = true;
            }
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        } finally {
            //ui.printDivider();
        }

        return ui.flushBuffer();
    }
}
