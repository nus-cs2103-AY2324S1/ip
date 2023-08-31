package duke;

import duke.task.DukeList;

public class Duke {
    private DukeList dukelist;
    private Ui ui;
    private Storage storage;
    private static final String FILE_PATH = "data/duke.txt";

    public Duke(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.dukelist = new DukeList(this.storage.getData());
        } catch (DukeException e) {
           this.dukelist = new DukeList();
            System.out.println("error");
        }
    }
    public static void main(String[] args) throws DukeException {
        new Duke(FILE_PATH).froggie();
    }

    public void froggie() {
        this.ui.hello();
        Parser parser = new Parser();
        parser.parse(this.storage, dukelist, ui);
    }

}
