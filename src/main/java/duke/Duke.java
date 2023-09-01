package duke;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UI ui;
    private static final String NAME = "DEREK";
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new UI(NAME);
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.printLoadingErrorMessage();
            this.tasks = new TaskList();
        }

    }

    public void run() {
        ui.printWelcomeMessage();
        String input;

        while (true) {
            input = ui.readCommand();
            try {
                ArrayList<String> parsedInput = Parser.parseUserInput(input);
                String command = parsedInput.get(0);

                if (command.equals("mark")) {
                    int index = Integer.parseInt(parsedInput.get(1)) - 1;
                    Task task = tasks.mark(index);
                    ui.printTaskMarkedMessage(task);
                    tasks.saveState(storage);
                    continue;
                }
                if (command.equals("unmark")) {
                    int index = Integer.parseInt(parsedInput.get(1)) - 1;
                    Task task = tasks.unmark(index);
                    ui.printTaskUnmarkedMessage(task);
                    tasks.saveState(storage);
                    continue;
                }
                if (command.equals("list")) {
                    tasks.printContents();
                    continue;
                }
                if (command.equals("bye")) {
                    ui.printGoodbyeMessage();
                    ui.closeUi();
                    break;
                }
                if (command.equals("todo")) {
                    ToDo newTodo = new ToDo(parsedInput.get(1));
                    Task task = tasks.add(newTodo);
                    ui.printTaskAddedMessage(task, tasks.getTaskCount());
                    tasks.saveState(storage);
                    continue;
                }
                if (command.equals("deadline")) {
                    Deadline newDeadline = new Deadline(parsedInput.get(1),
                            LocalDateTime.parse(parsedInput.get(2), dateTimeInputFormatter));
                    Task task = tasks.add(newDeadline);
                    ui.printTaskAddedMessage(task, tasks.getTaskCount());
                    tasks.saveState(storage);
                    continue;
                }
                if (command.equals("event")) {
                    Event newEvent = new Event(parsedInput.get(1),
                            LocalDateTime.parse(parsedInput.get(2), dateTimeInputFormatter),
                            LocalDateTime.parse(parsedInput.get(3), dateTimeInputFormatter));
                    Task task = tasks.add(newEvent);
                    ui.printTaskAddedMessage(task, tasks.getTaskCount());
                    tasks.saveState(storage);
                    continue;
                }
                if (command.equals("delete")) {
                    int index = Integer.parseInt(parsedInput.get(1)) - 1;
                    Task task = tasks.remove(index);
                    ui.printTaskDeletedMessage(task, tasks.getTaskCount());
                    tasks.saveState(storage);
                    continue;
                }
                if (command.equals("on")) {
                    LocalDate date = LocalDate.parse(parsedInput.get(1));
                    ArrayList<Task> tasksOnDate = tasks.getTasksOn(date);
                    ui.printTasksOn(tasksOnDate);
                    continue;
                }
                if (command.equals("find")) {
                    ArrayList<Task> tasksContainingKeyword = tasks.getTasksContainingKeyword(parsedInput.get(1));
                    ui.printTasksMatching(tasksContainingKeyword);
                    continue;
                }
                throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (InvalidCommandException | StorageException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/state.txt").run();
    }
}
