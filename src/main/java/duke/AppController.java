package duke;

import duke.task.TaskStorage;

/**
 * The {@code AppController} class is the main logic controller of the application.
 */
class AppController {
    private boolean isExit = false;
    private final InputHandler inputHandler;

    public AppController() {
        CliOutputUi outputUI = new CliOutputUi();
        this.inputHandler = new InputHandler(outputUI, new TaskStorage());
    }

    public void run() {
        while (!isExit) {
            inputHandler.handleInput();
            isExit = inputHandler.isExit();
        }
    }
}
