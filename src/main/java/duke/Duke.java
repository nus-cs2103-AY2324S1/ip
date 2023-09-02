package duke;

import java.io.FileNotFoundException;

public class Duke {
    //fields
    private Storage storage;
    private Ui ui;
    private TaskList list;
    private Parser parser;

    //constuctor
    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filePath);

//        try {
            list = new TaskList(storage.load());
//        } catch (Exceptions.DukeException e) {
//            ui.showLoadingError();
//            tasks = new duke.TaskList();
//        }
    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        parser = new Parser();
        while(!isExit){
            String input = ui.getUserCommand();
            parser.parse(input, list);
            isExit = ui.isExit(input);
        }

        storage.save(list);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Duke("duke.txt").run();

    }
}


