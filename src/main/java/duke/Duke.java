package duke;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;

import duke.exception.*;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A chatbot that helps to record tasks and store tasks.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    Scanner sc;
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
