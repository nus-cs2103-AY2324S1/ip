package duke;

import exceptions.DukeException;
import exceptions.FileUnloadableException;
import exceptions.ParseTaskFromStringException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    static ArrayList<Task> taskArray = new ArrayList<>();
    static TaskList taskList =  new TaskList(taskArray);
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        } catch (IOException e){
            System.out.println("Something went wrong while loading saved task file.");
        }
    }

    public void run() {
        // Send welcome message
        Ui.printWelcomeMessage();

        try {
            taskList = new TaskList(Storage.load());
            if (taskList.listTasks() == "") {
                System.out.println("There are no tasks in your list at the moment. Add some!");
            } else {
                System.out.println("Here are the tasks in your list:");
                System.out.println(taskList.listTasks());
            }
        } catch (FileNotFoundException e) {
            System.out.println("There are no tasks in your list at the moment. Add some!");
        } catch (FileUnloadableException e) {
            System.out.println("File cannot be loaded.");
        } catch (IOException e) {
            System.out.println("e.getMessage");
        } catch (ParseTaskFromStringException e) {
            System.out.println(e);
        }

        // Implement function to read user input via keyboard
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while(true) {
            userInput = scanner.nextLine().trim();
            Parser.parseInput(userInput, tasks);
            if (userInput.equals("bye")) {
                break;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
