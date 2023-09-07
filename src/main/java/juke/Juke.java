package juke;

import javafx.application.Application;
import javafx.application.Platform;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Juke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private final Parser parser;

    private Application main;

    private boolean isEnded;
    public Juke(String filePath, Application main) {
        ui = new Ui();
        parser = new Parser(this);
        storage = new Storage(filePath);
        this.main = main;
        try {
            tasks = new TaskList(storage.load());
        } catch (JukeError e) {
            System.out.println(ui.printError(e));
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public String getResponse(String input) {
        try {
            return parser.parse(input);
        } catch (JukeError error) {
            return ui.printError(error);
        } catch (Exception error) {
            System.out.println("Something went wrong.");
        }
        return "Something went wrong. Please try again!";
    }

    public String closeBot() throws Exception {
        // Create a ScheduledExecutorService with a single-thread pool
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        // Schedule the main.stop() to run after 2 seconds
        executorService.schedule(() -> {
            Platform.runLater(() -> {
                try {
                    main.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }, 2, TimeUnit.SECONDS);

        // Shutdown the executor service after scheduling the task
        executorService.shutdown();

        // Return ui.printBye() immediately
        return ui.printBye();
    }

    public String printList() {
        return ui.printList(tasks.getTasks());
    }

    public String unmark(int index) throws JukeError {
        Task currTask = tasks.markAsUndone(index);
        storage.updateAll(tasks.getTasks());
        return ui.unmark(currTask);
    }

    public String mark(int index) throws JukeError {
        Task currTask = tasks.markAsDone(index);
        storage.updateAll(tasks.getTasks());
        return ui.mark(currTask);
    }

    public String delete(int index) throws JukeError {
        Task currTask = tasks.delete(index);
        storage.updateAll(tasks.getTasks());
        return ui.delete(currTask, tasks.getSize());
    }

    public String find(String searchTerm) {
        ArrayList<Task> results = tasks.find(searchTerm);
        return ui.find(results);
    }

    public String createTodo(String desc) throws JukeError {
        Task newTask = new Todo(desc);
        tasks.add(newTask);
        storage.write(newTask);
        return ui.createTask(newTask, tasks.getSize());
    }

    public String createDeadline(String desc, LocalDate by) throws JukeError {
        Task newTask = new Deadline(desc, by);
        tasks.add(newTask);
        storage.write(newTask);
        return ui.createTask(newTask, tasks.getSize());
    }

    public String createEvent(String desc, LocalDate start, LocalDate end) throws JukeError {
        Task newTask = new Event(desc, start, end);
        tasks.add(newTask);
        storage.write(newTask);
        return ui.createTask(newTask, tasks.getSize());
    }
}
