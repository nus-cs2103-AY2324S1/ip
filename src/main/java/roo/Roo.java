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

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Roo() {
    }

    /**
     * Constructs a Roo object with the specified file path.
     * @param filePath The path to the file where tasks are stored.
     */
    public Roo(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage);
        this.ui = new Ui(tasks);
    }

    public String greet() {
        return this.ui.greet();
    }

    /**
     * Starts the Roo application. Initializes the task list, greets the user, and handles user commands.
     */
    public String run(String input) {
        this.storage.initialise(tasks);
        Commands c;
        while (true) {
            c = Parse.parse(input);
            try {
                switch (c) {
                case LIST:
                    return this.ui.list();

                case CLEAR:
                    return this.ui.clear();

                case DATE:
                    if (input.length() < 7) {
                        return "Which day u want oh?? Give in dd-MM-yyyy ahhh\n";
                    }
                    String date = input.substring(6);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    return this.ui.listDateEvents(LocalDate.parse(date, formatter));

                case FIND:
                    if (input.length() < 6) {
                        return "What do you want to find ahh??\n";
                    }
                    String keyword = input.substring(6);
                    return this.ui.find(keyword);

                case UNMARK:
                    if (input.length() < 8) {
                        return "Please unmark your task using this format: "
                                + "\"unmark [serial number]\"\n";
                    }
                    int t = Integer.parseInt(input.substring(7));
                    if (t > this.tasks.size()) {
                        return "We dunhave so many task lah =_=\n";
                    } else if (!this.tasks.isDone(t - 1)) {
                        return "Weihh... It's unmark ehhh\n";
                    }
                    return this.ui.markUndone(t - 1);

                case MARK:
                    if (input.length() < 6) {
                        return "Please mark your task using this format: "
                                + "\"mark [serial number]\"\n";
                    }
                    int u = Integer.parseInt(input.substring(5));
                    if (u > this.tasks.size()) {
                        return "We dunhave so many task lah =_=\n";
                    } else if (this.tasks.isDone(u - 1)) {
                        return "Weihh... It's already mark ehhh\n";
                    }
                    return this.ui.markDone(u - 1);

                case DELETE:
                    if (input.length() < 8) {
                        return "Please delete your task using this format: "
                                + "\"delete [serial number]\"\n";
                    }
                    int v = Integer.parseInt(input.substring(7));
                    if (v > this.tasks.size()) {
                        return "We dunhave so many task lah =_=\n";
                    }
                    return this.ui.delete(v - 1);

                case TODO:
                    String tsk = input.substring(4);
                    return this.ui.add(new Todo(tsk));

                case DEADLINE:
                    if (!input.contains("/by")) {
                        return "Please enter your task with this format: "
                                + "\"deadline task_description /by dd-MM-yyyy HH:mm (deadline)\"\n";
                    }
                    String tk = input.substring(8, input.indexOf("/") - 1);
                    return this.ui.add(new Deadline(tk, input.substring(input.indexOf("/by") + 4)));

                case EVENT:
                    if (!input.contains("/from") || !input.contains("/to")) {
                        return "Please enter your task with this format: "
                                + "\"event task_description /from dd-MM-yyyy HH:mm (start) "
                                + " /to dd-MM-yyyy HH:mm (end)\"\n";
                    }
                    String ts = input.substring(5, input.indexOf("/from") - 1);
                    String start = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                    String end = input.substring(input.indexOf("/to") + 4);
                    return this.ui.add(new Event(ts, start, end));

                case END:
                    return this.ui.close();

                default:
                    return "I dunno what u mean!!!\n";

                }

            } catch (RooException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Roo: \n" + run(input);
    }

    public static void main(String[] args) {
        Roo roo = new Roo("roo.txt");
        roo.run("list");
    }

}

