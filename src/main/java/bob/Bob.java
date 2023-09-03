package bob;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import bob.command.Command;

/**
 * Main class for Bob
 */
public class Bob {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Bob constructor, initialise storage, tasks and ui
     */
    public Bob() {
        storage = new Storage("./bob.txt");
        ui = new Ui();

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            try {
                String nextLine = sc.nextLine();
                if (nextLine.isEmpty()) {
                    throw new NoSuchElementException();
                }

                Command c = Parser.parse(nextLine);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (NoSuchElementException e) {
                ui.stringFormat(new String[]{"Write something!"});
            }
        }
    }

    public String greet() {
        return ui.greet();
    }

    public String getResponse(String input) {
        if (input.equals("")) {
            return ui.stringFormat(new String[]{"Write something!"});
        }
        Command c = Parser.parse(input);
        return c.execute(tasks, ui, storage);
    }


}

