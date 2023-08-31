package duke;

import java.io.IOException;
import java.io.File;

/**
 * Class for the ChatBot
 */
class Duke {
    public static String TEXTFILE = "duke.txt";
    public static String DATAFILE = "data.txt";
    public static String DELIM = " ";
    public static String GREETING = "-------------------------------\n"
            + "Hello! I'm Skog.\n"
            + "What can I do for you?\n"
            + "-------------------------------\n";
    public static String EXIT = "-------------------------------\n"
            + "Bye. Hope to see you again soon!\n"
            + "-------------------------------\n";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class.
     * @param filePath Specifies the name of the text file
     */
    public Duke(String filePath) {
        this.storage = new Storage(TEXTFILE, DATAFILE);
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Method to initiate the chatbot.
     */
    public void run() {
        try {
            File myFile = new File(TEXTFILE);
            if (myFile.createNewFile()) {
                System.out.println("-------------------------------\n"
                        + "Welcome! New text file created.");
            } else {
                // go straight into task control
            }
        } catch (IOException e) {
            System.out.println("An error has occurred!");
        }

        System.out.println(GREETING);

        while (ui.sc.hasNext()) {
            try {
                String[] arr = ui.sc.nextLine().split(DELIM);
                String type = arr[0];

                if (type.equals("bye")) {
                    ui.end();
                    storage.saveTextFile(tasks);
                    storage.saveDataFile(tasks);
                    System.out.println(EXIT);
                    break;
                } else if (type.equals("list")) {
                    tasks.listOut();
                } else if (type.equals("mark")) {
                    tasks.mark(arr);
                } else if (type.equals("unmark")) {
                    tasks.unmark(arr);
                } else if (type.equals("delete")) {
                    tasks.delete(arr);
                }
                else {
                    // check for task type first
                    if (type.equals("todo")) {
                        if (arr.length == 1) {
                            throw new EmptyDescription();
                        } else {
                            String desc = tasks.getDescription(arr);
                            tasks.addTodo(desc);
                        }
                    } else if (type.equals("deadline")) {
                        String desc = tasks.getDescription(arr);
                        String date = tasks.getDeadline(arr);
                        tasks.addDeadline(desc, date);
                    }
                    else if (type.equals("event")) {
                        String desc = tasks.getDescription(arr);
                        String timeline = tasks.getEventTimeline(arr);
                        tasks.addEvent(desc, timeline);
                    }
                    else {
                        throw new WrongInput();
                    }
                }
            } catch (EmptyDescription | WrongInput e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(TEXTFILE).run();
    }
}
