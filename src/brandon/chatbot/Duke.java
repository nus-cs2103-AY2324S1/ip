import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import commands.Command;
import commands.CommandResult;
import parser.Parser;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

import ui.TextUi;

import common.DukeException;
import common.DukeUnknownCommandException;
public class Duke {
    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static Path outputPath = Paths.get(CURRENT_DIRECTORY, "output.txt");
    private TaskList tasks;
    private TextUi ui;

    /**
     *
     * @throws IOException
     */
    public void saveFile() throws IOException {
        try {
            if (!Files.exists(outputPath)) {
                Files.createFile(outputPath);
            }
            String data = "";
            for (Task task : tasks.getList()) {
                data += task.getStatus() + "\n";
            }
            Files.write(outputPath, data.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static boolean continueOrNot(String[] input) {
        if (input[0].equals("bye")) {
            return false;
        }
        return true;
    }
    public static String[] splitBy(String input, String regex) {
        String [] parts = input.split(regex, 2);
        if (parts.length <= 1) {
            parts = new String[]{parts[0], ""};
        }
        return parts;
    }


    public void greeting() throws Exception {
        this.ui = new TextUi();
        tasks = new TaskList();
        ui.showWelcomeMessage();
        parseAndRun();
        System.out.println("    bye...");
        System.exit(1);
    }

    public void parseAndRun() {
        String userCommand = ui.getUserCommand();
        String [] parsedCommand = splitBy(userCommand, " ");
        while (continueOrNot(parsedCommand)) {
            try {
                Command command = new Parser().parseCommand(userCommand);
                CommandResult result = executeCommand(command);
                ui.showResultToUser(result);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            userCommand = ui.getUserCommand();
            parsedCommand = splitBy(userCommand, " ");
        }
    }
    private CommandResult executeCommand(Command command) {
        try {
            command.setData(tasks);
            CommandResult result = command.execute();
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        new Duke().greeting();
    }
}

