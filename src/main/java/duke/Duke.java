package duke;

import duke.task.*;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            this.ui.printError(e);
            this.tasks = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        this.ui.printEntryMessage();

        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (input.equals("list")) {
                this.ui.printList(this.tasks);
            } else if (input.startsWith("mark")) {
                try {
                    Task task = tasks.mark(Parser.parseUserMark(input, tasks.size()));
                    this.ui.printMark(task);
                } catch (TaskException e) {
                    this.ui.printError(e);
                }
            } else if (input.startsWith("unmark")) {
                try {
                    Task task = tasks.unmark(Parser.parseUserUnmark(input, tasks.size()));
                    this.ui.printUnmark(task);
                } catch (TaskException e) {
                    this.ui.printError(e);
                }
            } else if (input.startsWith("delete")) {
                try {
                    Task task = tasks.remove(Parser.parseUserDelete(input, tasks.size()));
                    this.ui.printDelete(task, tasks.size());
                } catch (TaskException e) {
                    this.ui.printError(e);
                }
            } else if (input.startsWith("deadline")) {
                try {
                    Deadline deadline = Parser.parseUserDeadline(input);
                    this.tasks.add(deadline);
                    this.ui.printAdd(deadline, tasks.size());
                } catch (DeadlineException e) {
                    this.ui.printError(e);
                }
            } else if (input.startsWith("event")) {
                try {
                    Event event = Parser.parseUserEvent(input);
                    this.tasks.add(event);
                    this.ui.printAdd(event, tasks.size());
                } catch (EventException e) {
                    this.ui.printError(e);
                }
            } else if (input.startsWith("todo")) {
                try {
                    ToDo todo = Parser.parseUserToDo(input);
                    this.tasks.add(todo);
                    this.ui.printAdd(todo, tasks.size());
                } catch (ToDoException e) {
                    this.ui.printError(e);
                }
            } else if (input.equals("bye")) {
                this.storage.save(this.tasks);
                this.ui.printExitMessage();
                break;
            } else {
                this.ui.printCommandNotFound();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();

    }

}
