package roo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import roo.task.Deadline;
import roo.task.Event;
import roo.task.Todo;

/**
 * The main class for the Roo application, which is a tasks management program.
 */
public class Roo {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Roo object with the specified file path.
     * @param filePath The path to the file where tasks are stored.
     */
    Roo(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage);
        this.ui = new Ui(tasks);
    }

    /**
     * Starts the Roo application. Initializes the task list, greets the user, and handles user commands.
     */
    public void run() {
        this.storage.initialise(tasks);
        this.ui.greet();
        String input;
        Commands c;
        while (true) {
            input = this.ui.read();
            c = Parse.parse(input);
            try {
                switch (c) {
                case LIST:
                    this.ui.list();
                    break;

                case CLEAR:
                    this.ui.clear();
                    break;

                case DATE:
                    if (input.length() < 7) {
                        throw new RooException("Which day u want oh?? Give in dd-MM-yyyy ahhh\n");
                    }
                    String date = input.substring(6);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    this.ui.listDateEvents(LocalDate.parse(date, formatter));
                    break;

                case FIND:
                    if (input.length() < 6) {
                        throw new RooException("What do you want to find ahh??\n");
                    }
                    String keyword = input.substring(6);
                    this.ui.find(keyword);
                    break;

                case UNMARK:
                    if (input.length() < 8) {
                        throw new RooException("Please unmark your task using this format: "
                                + "\"unmark [serial number]\"\n");
                    }
                    int t = Integer.parseInt(input.substring(7));
                    if (t > this.tasks.size()) {
                        throw new RooException("We dunhave so many task lah =_=\n");
                    } else if (!this.tasks.isDone(t - 1)) {
                        throw new RooException("Weihh... It's unmark ehhh\n");
                    }
                    this.ui.markUndone(t - 1);
                    break;

                case MARK:
                    if (input.length() < 6) {
                        throw new RooException("Please mark your task using this format: "
                                + "\"mark [serial number]\"\n");
                    }
                    int u = Integer.parseInt(input.substring(5));
                    if (u > this.tasks.size()) {
                        throw new RooException("We dunhave so many task lah =_=\nq");
                    } else if (this.tasks.isDone(u - 1)) {
                        throw new RooException("Weihh... It's already mark ehhh\n");
                    }
                    this.ui.markDone(u - 1);
                    break;

                case DELETE:
                    if (input.length() < 8) {
                        throw new RooException("Please delete your task using this format: "
                                + "\"delete [serial number]\"\n");
                    }
                    int v = Integer.parseInt(input.substring(7));
                    if (v > this.tasks.size()) {
                        throw new RooException("We dunhave so many task lah =_=\n");
                    }
                    this.ui.delete(v - 1);
                    break;

                case TODO:
                    String tsk = input.substring(4);
                    this.ui.add(new Todo(tsk));
                    break;

                case DEADLINE:
                    if (!input.contains("/by")) {
                        throw new RooException("Please enter your task with this format: "
                                + "\"deadline task_description /by dd-MM-yyyy HH:mm (deadline)\"\n");
                    }
                    String tk = input.substring(8, input.indexOf("/") - 1);
                    this.ui.add(new Deadline(tk, input.substring(input.indexOf("/by") + 4)));
                    break;

                case EVENT:
                    if (!input.contains("/from") || !input.contains("/to")) {
                        throw new RooException("Please enter your task with this format: "
                                + "\"event task_description /from dd-MM-yyyy HH:mm (start) "
                                + " /to dd-MM-yyyy HH:mm (end)\"\n");
                    }
                    String ts = input.substring(5, input.indexOf("/from") - 1);
                    String start = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                    String end = input.substring(input.indexOf("/to") + 4);
                    this.ui.add(new Event(ts, start, end));
                    break;

                case END:
                    this.ui.close();
                    return;

                default:
                    throw new RooException("I dunno what u mean!!!\n");

                }

            } catch (RooException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Roo roo = new Roo("roo.txt");
        roo.run();
    }
}
