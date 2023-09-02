package Duke;

/**
 * Main class from which Duke program is run.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;

    /**
     * Main method for Duke.
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        new Duke("file.txt").run();
    }

    /**
     * Public constructor for Duke
     * @param filename for the txt file to be referenced.
     */
    public Duke (String filename){
        ui=new Ui();
        storage=new Storage(filename);
        tasks= new TaskList(storage.load());
        parser=new Parser();
    }

    /**
     * Contains the logic essential to the running of Duke.
     */
    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String cmd = ui.readCommand();
            parser.parse(cmd, tasks, storage, ui);
            isExit = parser.isExit();
        }
        ui.showExit();
    }

}
