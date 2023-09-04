package duke.main;

import java.io.IOException;
import java.util.Scanner;

import duke.utilities.TaskList;
import duke.utilities.Ui;
import duke.commands.Command;
import duke.exception.DukeException;
import duke.utilities.Parser;
import duke.utilities.Storage;

/**
 * Main ChatBot class.
 */
public class Duke {
    private Storage dataBase;
    private TaskList taskList;
    private Ui ui;
    private String filePath = "./tasklist.txt";

    /**
     * ChatBot constructor.
     * @throws IOException if there is an error loading the saved file.
     */
    public Duke() throws IOException {
        ui = new Ui();
        this.dataBase = new Storage(filePath);
        this.taskList = dataBase.load();
    }

    /**
     * The factory method to run the chatbot after it has been instantiated. It is responsible for scanning inputs and
     * passing the inputs to the parsers to parse and instantiate the respective commands
     */
    public void run() throws DukeException, IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ui.printHello();

        Scanner sc = new Scanner(System.in);
        String userInput = "";
        do {
            userInput = sc.nextLine();
            Command c = Parser.parse(userInput, this.taskList, this.dataBase, ui);
            c.execute();
        } while (!userInput.toLowerCase().equals("bye"));
    }
}
