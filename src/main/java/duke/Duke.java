package duke;

import duke.helper.Task;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.helper.Parser;
import duke.helper.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Duke {



    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;


    public Duke(String fileName, String dirName) {
        ui = new Ui("Blus");
        storage = new Storage(fileName, dirName);
        try {
            tasks = new TaskList(storage.loadList(), storage.getListPointer());
        } catch (Exception e) {
            ui.speak(e.toString());
            tasks = new TaskList();
        }
        parser = new Parser();
    }

    private boolean isValidDateFormat(String date) {
        try {
            LocalDate d = LocalDate.parse(date);

        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Handles interactions with the user in command line interface.
     */
    public void run() {

        ui.speak(greet());

        boolean isExiting = false;

        Scanner getUserInput = new Scanner(System.in);

        while (!(isExiting)) {

            String userInput = getUserInput.nextLine();

            String message = getResponse(userInput);
            if (message.equals(ui.exit())) {
                isExiting = true;
            }

            ui.speak(message);

        }

        getUserInput.close();

    }


    /**
     * Handles interactions with the user in GUI.
     *
     * @param input User command.
     * @return Blus's response to the user command.
     */
    public String getResponse(String input) {


        String message = "";
        parser.processUserCommand(input);

        //Invoke corresponding command handling using switch. Therefore, the switch statement is long
        //because there are a number of available user command.
        switch (parser.getCommand()) {

        case "bye":
            //bye
            message = ui.exit();
            break;

        case "list":
            //list
            message = tasks.displayList();
            break;

        case "mark":
            //mark 1
            message = mark();
            break;

        case "unmark":
            //unmark 1
            message = unmark();
            break;

        case "todo":
            //todo read book
            message = todo();
            break;

        case "deadline":
            //deadline read book /by 2022-01-01
            message = deadline();
            break;

        case "event":
            //event read book /from 2022-01-01 /to 2022-01-02
            message = event();
            break;

        case "delete":
            //delete 1
            message = delete();
            break;

        case "find":
            message = tasks.findTask(parser.getTaskName());
            break;

        case "stats":
            message = tasks.showTaskStatics();
            break;

        case "sort":
            message = sort();
            break;

        default:
            message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }

        return message;
    }

    /**
     * Stores the current tasks list to file.
     *
     */
    public void store() {
        storage.saveList(tasks.getUserList(), tasks.getUserListPointer());
    }


    /**
     * Handles mark command.
     *
     * @return Blus's response to the user after processing the command.
     */
    public String mark(){
        String message;
        try {
            int i = Integer.parseInt(parser.getTaskName()) - 1;
            message = tasks.markTask(i);
            store();

        } catch (NumberFormatException e) {
            message = "need to provide an integer index of task.";
        }

        return message;

    }

    /**
     * Handles unmark command.
     *
     * @return Blus's response to the user after processing the command.
     */
    public String unmark(){
        String message;
        try {
            int i = Integer.parseInt(parser.getTaskName()) - 1;
            message = tasks.unmarkTask(i);
            store();
        } catch (NumberFormatException e) {
            message = "need to provide an integer index of task.";
        }

        return message;
    }

    /**
     * Handles todo command.
     *
     * @return Blus's response to the user after processing the command.
     */
    public String todo(){
        String message;
        message = tasks.addTask(new Task(parser.getTaskName(),
                1, "Null", "Null", false));
        store();

        return message;
    }


    /**
     * Handles deadline command.
     *
     * @return Blus's response to the user after processing the command.
     */
    public String deadline(){
        String message;
        if (isValidDateFormat(parser.getFirstEnteredTime())) {
            message = tasks.addTask(new Task(parser.getTaskName(),
                    2, "Null", parser.getFirstEnteredTime(), false));
            store();
        } else {
            message = "Invalid date format. Please enter as /by yyyy-mm-dd.";
        }

        return message;
    }

    /**
     * Handles event command.
     *
     * @return Blus's response to the user after processing the command.
     */
    public String event(){
        String message;
        if (isValidDateFormat(parser.getFirstEnteredTime()) &&
                isValidDateFormat(parser.getSecondEnteredTime())) {
            message = tasks.addTask(new Task(parser.getTaskName(),
                    3, parser.getFirstEnteredTime(), parser.getSecondEnteredTime(), false));
            store();
        } else {
            message = "Invalid date format. Please enter as /from yyyy-mm-dd /to yyyy-mm-dd.";
        }

        return message;
    }

    /**
     * Handles delete command.
     *
     * @return Blus's response to the user after processing the command.
     */
    public String delete(){
        String message;
        try {
            int i = Integer.parseInt(parser.getTaskName()) - 1;
            message = tasks.deleteTask(i);
            store();
        } catch (NumberFormatException e) {
            message = "need to provide an integer index of task.";
        }

        return message;
    }

    /**
     * Handles sort command.
     *
     * @return Blus's response to the user after processing the command.
     */
    public String sort(){
        String message;
        if (!(parser.getTaskName().equals("by start date") || parser.getTaskName().equals("by end date"))) {
            message = "Sorry, I do not know how you want to sort the tasks";
        } else {

            if (parser.getTaskName().equals("by start date")) {
                tasks.sortByStartDate();
            }

            if (parser.getTaskName().equals("by end date")) {
                tasks.sortByEndDate();
            }

            store();
            message = "Your list is now sorted!\n\n";

            message = message + tasks.displayList();
        }

        return message;
    }

    /**
     * Returns greeting message.
     *
     * @return Greeting message.
     */
    public String greet(){
        String logo = "  ____  _           \n" +
                " |  _ \\| |          \n" +
                " | |_) | |_   _ ___ \n" +
                " |  _ <| | | | / __|\n" +
                " | |_) | | |_| \\__ \\\n" +
                " |____/|_|\\__,_|___/\n";

        String message = "Hello from\n" + logo + "\n";
        message = message + ui.greet();

        return message;
    }
    public static void main(String[] args) {

        new Duke("list.txt", "data").run();



    }




}
