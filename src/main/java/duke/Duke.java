package duke;

import java.util.Scanner;

//CHECKSTYLE.OFF: MissingJavadocMethodCheck
//CHECKSTYLE.OFF: MissingJavadocType
public class Duke {
    private Storage storage;
    private Launcher.TaskList tasks;
    private Ui ui;
    private Gui gui;
    private String filePath;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.filePath = filePath;
        try {
            tasks = new Launcher.TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new Launcher.TaskList();
            this.filePath = filePath;
        }
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    /**
     * Runs the chatbot by initiating the scanner and parser
     */
    public void run() {
        ui.showWelcomeMessage();

        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String input = in.nextLine();
                int number = tasks.num();
                Parser.parseInput(input, tasks, number, filePath, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }

    public void getDukeResponse(String userInput) {
        try {
            int number = tasks.num();
            Parser.parseInput(userInput, tasks, number, filePath, gui, storage);
        } catch (DukeException e) {
            gui.printError(e.getMessage());
        }
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
