package duke;

import java.util.Scanner;

import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * A chatbot that helps to record tasks and store tasks.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    private Scanner sc;

    /**
     * Constructs Duke object.
     */
    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new TaskList();
        this.storage = new Storage(tasks);
        this.parser = new Parser(tasks, storage);
        this.ui = new Ui();
    }

    /**
     * Greets by chatbot.
     */
    private void greet() {
        System.out.println("------------------------------------------");
        System.out.println("  Hello! I'm Jokey :) \n  What can I do for you?");
        System.out.println("------------------------------------------");
    }

    /**
     * Runs Duke chatbot.
     */
    public void run() {
        storage.load();
        greet();
        parser.interact();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();

    }
}
