package duke;

/**
 * Represents the different commands that the user can input.
 */
public class Exit {
    private Thread thread;

    /**
     * Creates an exit command.
     */
    public Exit() {
        thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
    }

    /**
     * Starts the exit command.
     */
    public void start() {
        thread.start();
    }
}
