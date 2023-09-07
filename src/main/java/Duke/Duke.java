package Duke;

import exceptions.ErrorStorageException;
import helpers.Storage;
import helpers.TaskList;
import helpers.Ui;

/**
 * Main Class of controllers.Duke
 */
public class Duke {

    private TaskList taskList;
    private final String DIRECTORY = "data";
    private Storage storage;
    private Ui ui;

    /**
     * Empty public constructor to load controllers.Duke
     */
    public Duke() {
    }

    /**
     * Public constructor to load controllers.Duke
     *
     * @param filePath File path to get text file data from
     * @throws ErrorStorageException Exception for storage loading error
     */
    public Duke(String filePath) throws ErrorStorageException {
        ui = new Ui();
        String projRoot = System.getProperty("user.dir");
        String path = projRoot + "/" + DIRECTORY + filePath;
        this.storage = new Storage(path);
        try {
            taskList = new TaskList(this.storage.read());
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

    }

    public Storage getStorage() {
        return this.storage;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Running method for executing other core processes
     */
//    public void run() {
//        //Create buffered reader for user input
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            ui.showWelcome();
//            boolean isRunning = true;
//            //exits the echo commands when input contains 'bye'
//            while (isRunning) {
//                String input = ui.getCommand(br);
//                String cmd = input.split(" ")[0].toUpperCase();
//                Parser parser = new Parser(cmd, taskList, storage);
//                parser.execute(input);
//                if (cmd.equalsIgnoreCase("bye")) {
//                    isRunning = false;
//                }
//            }
//        } catch (IOException | InvalidCommandException | ErrorStorageException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
