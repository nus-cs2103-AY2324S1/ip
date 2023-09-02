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
     * @param dataPath is path to bob.txt
     */
    public Bob(String dataPath) {
        storage = new Storage(dataPath);
        ui = new Ui();

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
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

    public static void main(String[] args) {
        new Bob("./bob.txt").run();
    }
}
