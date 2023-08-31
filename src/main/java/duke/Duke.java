package duke;

import java.io.IOException;
import java.io.File;

/**
 * Class for the ChatBot
 */
class Duke {
    /**
     * Name of the text file.
     */
    public static String textFile = "duke.txt";
    public static String dataFile = "data.txt";
    public static String delim = " ";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(textFile, dataFile);
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        try {
            File myFile = new File(textFile);
            if (myFile.createNewFile()) {
                System.out.println("-------------------------------\n"
                        + "Welcome! New text file created.");
            } else {
                // go straight into task control
            }
        } catch (IOException e) {
            System.out.println("An error has occurred!");
        }

        System.out.println(greeting);

        while (ui.sc.hasNext()) {
            try {

                String[] arr = ui.sc.nextLine().split(delim);
                String type = arr[0];

                if (type.equals("bye")) {
                    ui.end();
                    storage.writeToFile(tasks);
                    storage.save(tasks);
                    System.out.println(exit);
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

    /**
     * Greeting from the bot when user launches the program.
     */
    public static String greeting = "-------------------------------\n"
            + "Hello! I'm Skog.\n"
            + "What can I do for you?\n"
            + "-------------------------------\n";
    /**
     * Exit message when user exits the bot.
     */
    public static String exit = "-------------------------------\n"
            + "Bye. Hope to see you again soon!\n"
            + "-------------------------------\n";



    public static void main(String[] args) {
        new Duke(textFile).run();
    }
}
