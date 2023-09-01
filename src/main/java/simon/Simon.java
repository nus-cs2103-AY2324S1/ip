package simon;

import simon.command.Parser;
import simon.task.Task;


import java.util.Scanner;

public class Simon {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    protected static final String SPACE = "____________________________________________________________";
    protected static final String NSPACE = "\n____________________________________________________________";
    protected static final String SPACEN = "____________________________________________________________\n";

    public Simon(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
//            ui.showMessage("I've loaded your tasks from the last time you used me!" + NSPACE);
        } catch (SimonException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner scan = new Scanner(System.in);

        // Start Program
        ui.showWelcome();

        while (true) {
            String inData = scan.nextLine();
            Parser.Command command = Parser.parseCommand(inData.split(" ")[0]);

            try {
                switch (command) {
                    case LIST:
                        ui.listTasks(tasks);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        Task newTask = Parser.parseAddTask(inData, command);
                        tasks.addTask(newTask);
                        storage.save(tasks.getAllTasks());
                        ui.showAddedTask(newTask, tasks.getTaskCount());
                        break;
                    case UNMARK:
                        Task unmarkedTask = tasks.markTask(inData, false);
                        storage.save(tasks.getAllTasks());
                        ui.showMarkedTask(false, unmarkedTask);
                        break;
                    case MARK:
                        Task markedTask = tasks.markTask(inData, true);
                        storage.save(tasks.getAllTasks());
                        ui.showMarkedTask(true, markedTask);
                        break;
                    case DELETE:
                        Task deletedTask = tasks.deleteTask(inData);
                        storage.save(tasks.getAllTasks());
                        ui.showDeletedTask(deletedTask, tasks.getTaskCount());
                        break;
                    case FIND:
                        TaskList matchedTasks = tasks.findTasks(inData);
                        ui.showMatchingTasks(matchedTasks);
                        break;
                    case BYE:
                        ui.showGoodbye();
                        return;
                    case UNKNOWN:
                    default:
                        ui.showUnknownCommand();
                }
            } catch (SimonException se) {
                ui.showError(se.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Simon("data/simon.txt").run();
    }
}
