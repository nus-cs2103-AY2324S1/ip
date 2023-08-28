package duke;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.command.Command;
import duke.task.TaskList;

import java.time.format.DateTimeParseException;

public class Duke {

    private static TaskList list;
    public static Parser parser;
    private static Storage storage;



    public static void main(String[] args) {

        storage = new Storage();
        list = new TaskList();
        storage.load(list);

        parser = new Parser();

        Ui.ui.startPrompt();
        run();
    }

    public static void run() {
        try {
            Command cmd = parser.readInput();
            cmd.execute(list);
        } catch (DukeException e) {
            Ui.ui.errorPrompt(e);
            run();
        } catch (DateTimeParseException e) {
            Ui.ui.wrongDateFormatPrompt();
            run();
        }
    }

    public static int listSize() {
        return list.list().size();
    }

}
