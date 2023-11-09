package sisyphus;

import sisyphus.parser.Parser;
import sisyphus.storage.Storage;
import sisyphus.task.TaskList;
import sisyphus.ui.Ui;

/**
 * Class for Sisyphus chatbot.
 */
public class Sisyphus {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;


    /**
     * Constructor for the Sisyphus chatBot.
     */
    public Sisyphus() {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.loadData();
        parser = new Parser();
    }

    /**
     * Run all components as the driver function.
     */
    public void run() {
        ui.greet();
        boolean isChatting = true;
        while (isChatting) {
            try {
                String fullCommand = ui.readLine();
                assert fullCommand != null : "Ensure line was read correctly";
                parser.runCommand(fullCommand, tasks, storage, ui);
                isChatting = parser.getActiveStatus();
            } catch (SisyphusException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.exit();
    }

    /**
     * Runs CLI version of Sisyphus.
     * @param args
     */
    public static void main(String[] args) {
        Sisyphus sisyphus = new Sisyphus();
        sisyphus.run();
    }

    /**
     * @param input
     * @return Output string after running command
     */
    public String getResponse(String input) {
        try {
            return parser.runCommand(input, tasks, storage, ui);
        } catch (SisyphusException e) {
            return e.getMessage();
        }
    }




}
