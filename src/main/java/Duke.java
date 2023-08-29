import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Duke class serves as the chatbot
 */
public class Duke {

    private DukeList ItemList;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        //Initialising array to store list items
        ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.ItemList = new DukeList(this.storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.ItemList = new DukeList();
        }

    }

    public static void main(String[] args) {
           new Duke("data/duke.txt").run();
    }

    /**
     * This method initialises the beginning of the bots interaction with the user. It analyses the users input
     * and performs the relevant action correspondingly
     */
    public void run() {
        //Initialising Scanner
        ui.greet();
        Parser parser = new Parser();
        parser.parse(storage, ItemList, ui);
    }


}


