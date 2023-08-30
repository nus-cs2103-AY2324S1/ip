package duke;

/**
 * Duke bot class with a storage, TaskList and Ui
 *
 * @author wj331
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of Duke class
     * @param filePath the path to the file we want to write to/load from
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (InvalidInputException e) {
            tasks = new TaskList();
        }
    }

    /**
     * run method to trigger while loop to get responses from user
     */
    public void run() {
        Ui ui = new Ui();
        System.out.println(ui.greet());

        while (true) {
            try {
                //activates scanner
                String input = ui.getCommand();

                //handle the input from user
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (InvalidInputException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(ui.byeGreetings());
    }

    /**
     * Main method of our Duke Chat bot
     * @param args empty String array
     */
    public static void main(String[] args) {
        new Duke(Storage.relativePath + "\\TaskList.txt").run();
    }
}
