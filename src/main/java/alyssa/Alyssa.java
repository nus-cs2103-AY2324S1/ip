package alyssa;

import java.io.IOException;
import java.util.Scanner;

import alyssa.Exceptions.AlyssaArgumentException;

/**
 * This class represents the main program.
 */
public class Alyssa {
    private static final String LINE = "____________________________________________________________";
    private Storage storage;
    private String saveFilePath;
    private String dirPath;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor method for Alyssa.
     * @param saveFilePath Relative path of the save file.
     * @param dirPath Directory to be created, where the save file resides.
     */
    public Alyssa(String saveFilePath, String dirPath) {
        this.saveFilePath = saveFilePath;
        this.dirPath = dirPath;
        this.ui = new Ui();
        this.parser = new Parser();
    }
    public static void main(String[] args) {
        new Alyssa("./data/alyssa.txt", "./data").execute();
    }
    private void run(Command command, String rest) {
        switch (command) {
        case BYE:
            ui.goodbye();
            break;
        case LIST:
            taskList.listTasks();
            break;
        case MARK:
            try {
                taskList.markTask(rest);
            } catch (NumberFormatException e) {
                this.ui.printWithLines(e.getMessage());
            } catch (AlyssaArgumentException e) {
                this.ui.printWithLines(e.getMessage());
            }
            break;
        case UNMARK:
            try {
                taskList.unmarkTask(rest);
            } catch (NumberFormatException e) {
                this.ui.printWithLines(e.getMessage());
            } catch (AlyssaArgumentException e) {
                this.ui.printWithLines(e.getMessage());
            }
            break;
        case TODO:
            try {
                taskList.addTodo(rest);
            } catch (AlyssaArgumentException e) {
                this.ui.printWithLines(e.getMessage());
            }
            break;
        case DEADLINE:
            try {
                taskList.addDeadline(rest);
            } catch (AlyssaArgumentException e) {
                this.ui.printWithLines(e.getMessage());
            }
            break;
        case EVENT:
            try {
                taskList.addEvent(rest);
            } catch (AlyssaArgumentException e) {
                this.ui.printWithLines(e.getMessage());
            }
            break;
        case DELETE:
            try {
                taskList.deleteTask(rest);
            } catch (AlyssaArgumentException e) {
                this.ui.printWithLines(e.getMessage());
            } catch (NumberFormatException e) {
                this.ui.printWithLines(e.getMessage());
            }
            break;
        default:
            ui.invalidTaskResponse();
        }
    }
    private void execute() {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        Command command;
        this.ui.greet();
        this.storage = new Storage(saveFilePath, dirPath);
        this.taskList = new TaskList(storage.loadTasks());
        while (isRunning) {
            String nextInput = sc.nextLine();
            String[] parsedInput = this.parser.parseCommand(nextInput);
            String commandString = parsedInput[0];
            command = Command.assignCommand(commandString);
            String rest = parsedInput.length > 1 ? parsedInput[1] : "";
            run(command, rest);
            try {
                storage.saveTasks(taskList);
            } catch (IOException e) {
                System.out.println("Oops... We couldn't save your task data to a file :(");
                System.out.println(e.getMessage());
            }
            if (command == Command.BYE) {
                isRunning = false;
            }
        }
    }
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
