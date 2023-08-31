package duke;

import duke.storage.*;
import duke.ui.*;
import duke.task.*;


/**
 * The main class for the Duke chatbot application.
 */
public class Duke {
    private Ui ui;
    private TaskArray taskArrayList;
    private Storage storage;
    public final static String horiLine = "____________________________________________________________";
    public final static String logo = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";


    /**
     * Constructs a Duke object with the given file path for storage.
     *
     * @param filePath The file path to use for storing tasks.
     */
    public Duke(String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try{
            this.taskArrayList = storage.load();
        } catch (Exception e){
//            ui.showLoadingError();
            this.taskArrayList = new TaskArray();
        }
    }

    /**
     * Runs the Duke application.
     */
    public void run(){
        ui.greetFunction("Jack");
        ui.helpFunction();

        taskArrayList = ui.runTask(taskArrayList);

        storage.upload(taskArrayList);


    }

    /**
     * The main entry point of the Duke application.
     *
     * @param args Command line arguments (not used in this context).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }


}
