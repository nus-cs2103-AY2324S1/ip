package chatter;

import java.io.IOException;

import chatter.command.Command;

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
     *
     * @param filepath Filepath to get the local data file from.
     */
    public Chatter(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.readFile());
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
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();

            if (fullCommand.isBlank()) {
                continue;
            }

            try {
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (ChatterException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                if (fullCommand.startsWith("deadline")) {
                    System.out.println("Please enter a valid description or deadline.");
                } else {
                    System.out.println("Please enter a valid description and start / end time.");
                }
            }
        }
    }

    /**
     * Returns response from Chatter upon user response.
     *
     * @param input User response in the form of a string.
     * @return String response from Chatter.
     */
    public String getResponse(String input) {
        String response;
        try {
            response = Parser.parse(input).execute(tasks, ui, storage);
        } catch (ChatterException e) {
            response = e.getMessage();
        } catch (Exception e) {
            if (input.startsWith("deadline")) {
                response = "Please enter a valid description or deadline.";
            } else {
                response = "Please enter a valid format for your commands.";
            }
        }
        return response;
    }

    public static void main(String[] args) {
        new Chatter("./data/chatter.txt").run();
    }
}
