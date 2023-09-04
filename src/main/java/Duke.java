import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) throws DukeException {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.tasks = storage.loadIntoList(new TaskList());
    }

    public void run() throws DukeException {
        this.ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                int spaceIndex = userInput.indexOf(" ");
                if (spaceIndex == -1) {
                    switch (userInput) {
                    case "list":
                        ui.printList(tasks);
                        break;
                    case "bye":
                        isExit = true;
                        break;
                    case "todo":
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    case "event":
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    case "deadline":
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else {
                    switch (userInput.substring(0, spaceIndex)) {
                    case "todo":
                        String todoDesc = userInput.substring(spaceIndex + 1);
                        if (todoDesc.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task newTodo = new Todo(todoDesc);
                        tasks.add(newTodo);
                        try {
                            storage.appendToFile(newTodo);
                        } catch (IOException e) {
                            throw new DukeException("☹ OOPS!!! There is something wrong with the description.");
                        }
                        ui.printTaskAdded(newTodo, tasks.size());
                        break;
                    case "event":
                        int fromIndex = userInput.indexOf("/from");
                        int toIndex = userInput.indexOf("/to");
                        String eventDesc = userInput.substring(spaceIndex + 1, fromIndex - 1);
                        if (eventDesc.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        String from = userInput.substring(fromIndex + 6, toIndex - 1);
                        String to = userInput.substring(toIndex + 4);
                        Task newEvent = new Event(eventDesc, from, to);
                        tasks.add(newEvent);
                        try {
                            storage.appendToFile(newEvent);
                        } catch (IOException e) {
                            throw new DukeException("☹ OOPS!!! There is something wrong with the description.");
                        }
                        ui.printTaskAdded(newEvent, tasks.size());
                        break;
                    case "deadline":
                        int byIndex = userInput.indexOf("/by");
                        String deadlineDesc = userInput.substring(spaceIndex + 1, byIndex - 1);
                        if (deadlineDesc.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String by = userInput.substring(byIndex + 4);
                        Task newDeadline = new Deadline(deadlineDesc, by);
                        tasks.add(newDeadline);
                        try {
                            storage.appendToFile(newDeadline);
                        } catch (IOException e) {
                            throw new DukeException("☹ OOPS!!! There is something wrong with the description.");
                        }
                        ui.printTaskAdded(newDeadline, tasks.size());
                        break;
                    case "mark":
                        int i = Integer.parseInt(userInput.split(" ", 2)[1]);
                        Task taskToMark = tasks.get(i - 1);
                        taskToMark.markAsDone();
                        storage.writeListToFile(tasks);
                        ui.printTaskMarked(taskToMark);
                        break;
                    case "unmark":
                        int j = Integer.parseInt(userInput.split(" ", 2)[1]);
                        Task taskToUnmark = tasks.get(j - 1);
                        taskToUnmark.markAsNotDone();
                        storage.writeListToFile(tasks);
                        ui.printTaskUnmarked(taskToUnmark);
                        break;
                    case "delete":
                        int k = Integer.parseInt(userInput.split(" ", 2)[1]);
                        if (k > tasks.size() || k < 0) {
                            throw new DukeException("Integer out of list range");
                        }
                        Task deletedTask = tasks.get(k - 1);
                        tasks.remove(k - 1);
                        storage.writeListToFile(tasks);
                        ui.printTaskDeleted(deletedTask, tasks.size());

                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                ui.printException(e.getMessage());
            }
        }

        this.ui.sendOff();
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }
}