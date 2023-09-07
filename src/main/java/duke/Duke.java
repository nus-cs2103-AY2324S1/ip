package duke;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.tasks.Task;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.Ui;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Duke {
    private String name;
    private ArrayList<Task> tasks;
    private String filepath;
    private Storage storage;


    public Duke(String name, String filepath) {
        this.name = name;
        this.filepath = filepath;
        this.storage = new Storage(this.filepath);
        try {
            this.tasks = storage.readFile();
        } catch (FileNotFoundException e) {
            this.tasks = new ArrayList<>();
        }
    }

    public Duke() {
        this("Beary", "data/tasks.txt");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Ui.printGreeting(this.name);
        new ListCommand().execute(this.storage, this.tasks);

        while (true) {
            String fullCommand = scanner.nextLine();
            Ui.printLine();

            Parser parser = new Parser();
            Command command = parser.parse(fullCommand);
            if (command == null) {
                continue;
            }
            command.execute(this.storage, this.tasks);
            if (command instanceof ExitCommand) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        String name = "Beary";
        String filepath = "data/tasks.txt";
        Duke duke = new Duke(name, filepath);
        duke.run();
    }

    /**
     * Generates Duke's response to the user's input
     *
     * @param input
     * @return A string representing duke's response
     */
    public String getResponse(String input) {
        // To redirect standard output to a variable so that
        // we can return the response as a string
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        String fullCommand = input;
        Parser parser = new Parser();
        Command command = parser.parse(fullCommand);

        if (command != null) {
            command.execute(this.storage, this.tasks);
        }

        if (command instanceof ExitCommand) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> Platform.exit());
            pause.play();
        }

        try {
            this.storage.writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        System.setOut(originalOut);
        String capturedOutput = byteArrayOutputStream.toString();
        capturedOutput = capturedOutput.replaceAll("_________________" +
                "___________________________________________", "");

        return this.name + " says: \n" + capturedOutput;
    }
}

