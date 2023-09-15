

/**
 * Sidtacphi is the main class for the Sidtacphi bot.
 */
public class Sidtacphi {
    private static TaskList taskList = new TaskList();
    
    /**
     * Main method for the Sidtacphi class.
     * 
     * @param args
     */
    public static void main(String[] args) {
        taskList = Storage.readJson(taskList);
        startBot();
        Storage.saveAsJson(taskList);
        stopBot();
    }

    /**
     * Starts the Sidtacphi bot.
     */
    private static void startBot() {
        Ui.printIntro();
        Parser.readInputs(taskList);
    }

    /**
     * Stops the Sidtacphi bot.
     */
    private static void stopBot() {
        Ui.printGoodbye();
    }
}
