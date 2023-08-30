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
        Scanner in = new Scanner(System.in);

        ui.printWelcomeMessage();

        while (true) {
            String line = in.nextLine();
            try {
                ArrayList<String> parsedInput = Parser.parseUserInput(line);
                String command = parsedInput.get(0);

                if (command.equals(Command.MARK.getCommand())) {
                    int index = Integer.parseInt(parsedInput.get(1)) - 1;
                    Task task = tasks.mark(index);
                    ui.printTaskMarkedMessage(task);
                    continue;
                }
                if (command.equals(Command.UNMARK.getCommand())) {
                    int index = Integer.parseInt(parsedInput.get(1)) - 1;
                    Task task = tasks.unmark(index);
                    ui.printTaskUnmarkedMessage(task);
                    continue;
                }
                if (command.equals(Command.LIST.getCommand())) {
                    tasks.printContents();
                    continue;
                }
                if (command.equals(Command.BYE.getCommand())) {
                    tasks.saveState(storage);
                    ui.printGoodbyeMessage();
                    break;
                }
                if (command.equals(Command.TODO.getCommand())) {
                    ToDo newTodo = new ToDo(parsedInput.get(1));
                    Task task = tasks.add(newTodo);
                    ui.printTaskAddedMessage(task, tasks.getTaskCount());
                    continue;
                }
                if (command.equals(Command.DEADLINE.getCommand())) {
                    Deadline newDeadline = new Deadline(parsedInput.get(1),
                            LocalDateTime.parse(parsedInput.get(2), dateTimeInputFormatter));
                    Task task = tasks.add(newDeadline);
                    ui.printTaskAddedMessage(task, tasks.getTaskCount());
                    continue;
                }
                if (command.equals(Command.EVENT.getCommand())) {
                    Event newEvent = new Event(parsedInput.get(1),
                            LocalDateTime.parse(parsedInput.get(2), dateTimeInputFormatter),
                            LocalDateTime.parse(parsedInput.get(3), dateTimeInputFormatter));
                    Task task = tasks.add(newEvent);
                    ui.printTaskAddedMessage(task, tasks.getTaskCount());
                    continue;
                }
                if (command.equals(Command.DELETE.getCommand())) {
                    int index = Integer.parseInt(parsedInput.get(1)) - 1;
                    Task task = tasks.remove(index);
                    ui.printTaskDeletedMessage(task, tasks.getTaskCount());
                    continue;
                }
                if (command.equals(Command.ON.getCommand())) {
                    LocalDate date = LocalDate.parse(parsedInput.get(1));
                    ArrayList<Task> tasksOnDate = tasks.getTasksOn(date);
                    ui.printTasksOn(tasksOnDate);
                    continue;
                }
                if (command.equals(Command.FIND.getCommand())) {
                    ArrayList<Task> tasksContainingKeyword = tasks.getTasksContainingKeyword(parsedInput.get(1));
                    ui.printTasksMatching(tasksContainingKeyword);
                    continue;
                }
                throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
        in.close();
    }

    public static void main(String[] args) {
        new Duke("./data/state.txt").run();
    }
}
