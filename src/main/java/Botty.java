package main.java;
/**
 * The Duke class is responsible for responding to user's input.
 * It provides functionalities to add, mark, unmark, and delete tasks.
 */

import main.java.command.Command;
import main.java.exception.EmptyChoiceException;
import main.java.exception.EmptyTodoException;
import main.java.exception.UnknownCommandException;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

import java.util.Scanner;

public class Botty {

    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Storage storage;

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
