package duke;

import java.util.Scanner;

/**
 * Represents the Duke chatbot.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList toDo;

    /**
     * Constructs a new <code>Duke</code> object.
     * @param filepath the filepath to specify the filepath to document containing the task list.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        toDo = new TaskList();
    }

    /**
     * Runs the Duke chatbot.
     * @throws DukeException if user enters invalid input.
     */
    public void run() throws DukeException {
        Scanner sc = new Scanner(System.in);
        String userCommand;
        storage.load();
        ui.helloMsg();
        userCommand = sc.nextLine();

        while (!userCommand.equals("bye")) {
            Parser p = new Parser(userCommand);
            p.parseAndRespond();
            userCommand = sc.nextLine();
        }

        ui.goodbyeMsg();
    }
    public static void main(String[] args) throws DukeException {
        Duke dukey = new Duke("duke.txt");
        dukey.run();
    }
}
