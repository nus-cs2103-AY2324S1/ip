package duke;

import duke.task.TaskStorage;

/**
 * The Duke.UI class is the driver that runs the input handler and output handler.
 */
class UI {
    private boolean isExit = false;
    private final InputHandler inputHandler;

    public UI() {
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
