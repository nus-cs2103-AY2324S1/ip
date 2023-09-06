import java.util.Scanner;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class Duke for the chatbot
 *
 * @author marioalvaro
 */
public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private String response;


    /**
     * A Constructor to create Duke Chat bot.
     */
    public Duke() {
        storage = new Storage();
        this.ui = new Ui();

        try {
            storage.checkFile();
            this.taskList = new TaskList(storage.readFile());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     *
     * @param input input response in string.
     * @return Response message from Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            response = c.execute(taskList, ui, storage);
            if (c instanceof ByeCommand) {

            }
        } catch (Exception e) {
            ui.printError(e.getMessage());
            return e.getMessage();
        }

        return response;
    }

    /**
     * method to run the code.
     */
    public void run() {
        ui.printGreet();

        while (true) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();

            if (s == null) {
                s = in.nextLine();
            }

            try {
                Command c = Parser.parse(s);
                response = c.execute(taskList, ui, storage);
                if (c instanceof ByeCommand) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        storage.writeFile(taskList);
    }



    public static void main(String[] args) {
        new Duke().run();
    }
}
