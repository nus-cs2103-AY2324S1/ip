package duchess;

/**
 * The main class used to execute Duchess actions.
 */
public class DuchessGui {

    private Duchess duchess;
    private String responseString = "";

    /**
     * Returns a new DuchessGUI instance.
     */
    public DuchessGui() {
        this.duchess = new Duchess();
        this.duchess.setCallbackHandler((s) -> {
            this.responseString = s;
        });
    }

    public void saveTasks() {
        this.duchess.saveTasks();
    }

    public void loadTasks() {
        this.duchess.loadTasks();
    }

    /**
     * Returns, as a String, the response of DuchessGUI when a user input command is given.
     *
     * @param userInput - the user's input.
     * @return            the DuchessGUI's reponse.
     */
    public String getResponse(String userInput) {
        this.duchess.parseUserInput(userInput);

        return this.responseString;
    }
}
