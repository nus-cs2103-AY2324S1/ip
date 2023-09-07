package main.java;

import main.java.command.Command;
import main.java.exception.EmptyChoiceException;
import main.java.exception.EmptyTodoException;
import main.java.exception.UnknownCommandException;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;
import java.util.Scanner;

/**
 * The Botty class represents the main class responsible for interacting with the user and managing tasks.
 * It initializes the user interface, task list, parser, and storage components.
 * The class reads user input, processes commands, and handles task operations.
 */
public class Botty {

    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    /**
     * Constructs a new `Botty` instance, initializing user interface, task list, parser, and storage components.
     * The constructor also loads the task list from storage.
     */
    public Botty() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.storage = new Storage("./storage.txt");
        this.storage.loadTaskList(this.taskList);
    }
    public static void main(String[] args) {
        new Botty().run();
    }

    /**
     * Runs the main application loop, interacting with the user, processing commands, and managing tasks.
     * The loop continues until the user enters the "bye" command.
     */

    public String getResponse(String input) {
        try {
            Command command = this.parser.parseInstruction(input);
            this.storage.saveTaskList(this.taskList);
            return command.execute(this.taskList, this.ui);
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        } catch (EmptyTodoException e) {
            System.out.println(e.getMessage());
        } catch (EmptyChoiceException e) {
            System.out.println(e.getMessage());
        }
        return "Sorry, I don't recognise the command, please try another command";
    }
    public void run() {
        String name = "Botty";
        String tmp = "";
        Scanner scanner = new Scanner(System.in);
        this.ui.greet(name);
        while (true) {
            tmp = scanner.nextLine();
            if (tmp.equals("bye")) {
                break;
            } else {
                try {
                    Command command = this.parser.parseInstruction(tmp);
                    command.execute(this.taskList, this.ui);
                    this.storage.saveTaskList(this.taskList);
                } catch (UnknownCommandException e) {
                    System.out.println(e.getMessage());
                } catch (EmptyTodoException e) {
                    System.out.println(e.getMessage());
                } catch (EmptyChoiceException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        this.ui.bye();
    }
}
