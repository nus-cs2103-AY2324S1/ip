package chatter;

import chatter.command.Command;

import java.io.IOException;

/**
 * Represents chatter.Chatter the chatbot.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class Chatter {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * A constructor for the chatter.Chatter class
     */
    public Chatter(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.readFile());
//            System.out.println(tasks.getTask(0));
        } catch (IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }

    }

    /**
     * Check the user's commands and calls the appropriate methods according
     * to the commands.
     */
    private void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            String fullCommand = ui.readCommand();
            try {
                if (fullCommand.isBlank()) {
                    continue;
                }

                this.ui.showDivider();
                Parser p = new Parser(fullCommand);
                Command c = p.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatterException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                if (fullCommand.startsWith("deadline")) {
                    System.out.println("Please enter a valid description or deadline.");
                } else {
                    System.out.println("Please enter a valid description and start / end time.");
                }

            } finally {
                this.ui.showDivider();
            }
        }
    }

    public static void main(String[] args) {
        new Chatter("./data/chatter.txt").run();
    }
}
