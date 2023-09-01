package juke;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
public class Juke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private final Parser parser;

    private boolean isEnded;

    public void closeBot() {
        ui.printBye();
        isEnded = true;
    }

    public void printList() {
        ui.printList(tasks.tasks);
    }

    public void unmark(int index) throws JukeError {
        Task currTask = tasks.markAsUndone(index);
        ui.unmark(currTask);
        storage.updateAll(tasks.tasks);
    }

    public void mark(int index) throws JukeError {
        Task currTask = tasks.markAsDone(index);
        ui.mark(currTask);
        storage.updateAll(tasks.tasks);
    }

    public void delete(int index) throws JukeError {
        Task currTask = tasks.delete(index);
        ui.delete(currTask, tasks.getSize());
        storage.updateAll(tasks.tasks);
    }

    public void find(String searchTerm) {
        ArrayList<Task> results = tasks.find(searchTerm);
        ui.find(results);
    }

    public void createTodo(String desc) throws JukeError {
        Task newTask = new Todo(desc);
        tasks.add(newTask);
        storage.write(newTask);
        ui.createTask(newTask, tasks.getSize());
    }

    public void createDeadline(String desc, LocalDate by) throws JukeError {
        Task newTask = new Deadline(desc, by);
        tasks.add(newTask);
        storage.write(newTask);
        ui.createTask(newTask, tasks.getSize());
    }

    public void createEvent(String desc, LocalDate start, LocalDate end) throws JukeError {
        Task newTask = new Event(desc, start, end);
        tasks.add(newTask);
        storage.write(newTask);
        ui.createTask(newTask, tasks.getSize());
    }

    public Juke(String filePath) {
        ui = new Ui();
        parser = new Parser(this);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JukeError e) {
            ui.printError(e);
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.printStart();
        while (!isEnded) {
            try {
                parser.parse(ui.readInput());
            } catch (JukeError error) {
                ui.printError(error);
            }
        }
    }
    public static void main(String[] args) {
        new Juke("./savefile.txt").run();
    }
}
