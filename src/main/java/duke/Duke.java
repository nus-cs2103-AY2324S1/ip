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
        ui = new Ui("Duke");
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

        boolean wantToExit = false;
        boolean haveChangesInUserList;

        Scanner getUserInput = new Scanner(System.in);


        while (!(wantToExit)) {

            haveChangesInUserList = false;

            String userInput = getUserInput.nextLine();
            parser.processUserCommand(userInput);

            //Invoke corresponding command handling using switch. Therefore, the switch statement is long
            //because there are a number of available user command.
            switch (parser.getCommand()) {

            case "bye":
                //bye

                wantToExit = true;
                ui.speak(ui.exit());

                break;

            case "list":
                //list
                ui.speak(tasks.displayList());
                break;

            case "mark":
                //mark 1

                try {
                    int i = Integer.parseInt(parser.getTaskName()) - 1;
                    ui.speak(tasks.markTask(i));
                    haveChangesInUserList = true;
                } catch (NumberFormatException e) {
                    ui.speak("need to provide an integer index of task.");
                }


                break;

            case "unmark":
                //unmark 1
                try {
                    int i = Integer.parseInt(parser.getTaskName()) - 1;
                    ui.speak(tasks.unmarkTask(i));
                    haveChangesInUserList = true;
                } catch (NumberFormatException e) {
                    ui.speak("need to provide an integer index of task.");
                }

                break;

            case "todo":
                //todo read book
                ui.speak(tasks.addTask(new Task(parser.getTaskName(),
                        1, "Null", "Null", false)));
                haveChangesInUserList = true;
                break;

            case "deadline":
                //deadline read book /by 2022-01-01
                if (isValidDateFormat(parser.getFirstEnteredTime())) {
                    ui.speak(tasks.addTask(new Task(parser.getTaskName(),
                            2, "Null", parser.getFirstEnteredTime(), false)));
                    haveChangesInUserList = true;
                } else {
                    ui.speak("Invalid date format. Please enter as /by yyyy-mm-dd.");
                }
                break;

            case "event":
                //event read book /from 2022-01-01 /to 2022-01-02
                if (isValidDateFormat(parser.getFirstEnteredTime()) &&
                        isValidDateFormat(parser.getSecondEnteredTime())) {
                    ui.speak(tasks.addTask(new Task(parser.getTaskName(),
                            3, parser.getFirstEnteredTime(), parser.getSecondEnteredTime(), false)));
                    haveChangesInUserList = true;
                } else {
                    ui.speak("Invalid date format. Please enter as /from yyyy-mm-dd /to yyyy-mm-dd.");
                }
                break;

            case "delete":
                //delete 1
                try {
                    int i = Integer.parseInt(parser.getTaskName()) - 1;
                    ui.speak(tasks.deleteTask(i));
                    haveChangesInUserList = true;
                } catch (NumberFormatException e) {
                    ui.speak("need to provide an integer index of task.");
                }

                break;

            case "find":
                ui.speak(tasks.findTask(parser.getTaskName()));
                break;

            case "stats":
                ui.speak(tasks.showTaskStatics());
                break;

            case "sort":
                if (!(parser.getTaskName().equals("by start date") || parser.getTaskName().equals("by end date"))) {
                    ui.speak("Sorry, I do not know how you want to sort the tasks");
                } else {
                    haveChangesInUserList = true;
                    if (parser.getTaskName().equals("by start date")) {
                        tasks.sortByStartDate();
                    }

                    if (parser.getTaskName().equals("by end date")) {
                        tasks.sortByEndDate();
                    }
                    String message = "Your list is now sorted!\n\n";

                    message = message + tasks.displayList();
                    ui.speak(message);
                }

                break;


            default:
                ui.speak("OOPS!!! I'm sorry, but I don't know what that means :-(");


            }


            if (haveChangesInUserList) {
                storage.saveList(tasks.getUserList(), tasks.getUserListPointer());
            }





        }

        getUserInput.close();





    }


    /**
     * Handles interactions with the user in GUI.
     *
     * @param input User command.
     * @return Duke's response to the user command.
     */
    public String getResponse(String input) {

        boolean haveChangesInUserList = false;
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

            try {
                int i = Integer.parseInt(parser.getTaskName()) - 1;
                message = tasks.markTask(i);
                haveChangesInUserList = true;
            } catch (NumberFormatException e) {
                message = "need to provide an integer index of task.";
            }


            break;

        case "unmark":
            //unmark 1
            try {
                int i = Integer.parseInt(parser.getTaskName()) - 1;
                message = tasks.unmarkTask(i);
                haveChangesInUserList = true;
            } catch (NumberFormatException e) {
                message = "need to provide an integer index of task.";
            }

            break;

        case "todo":
            //todo read book
            message = tasks.addTask(new Task(parser.getTaskName(),
                    1, "Null", "Null", false));
            haveChangesInUserList = true;
            break;

        case "deadline":
            //deadline read book /by 2022-01-01
            if (isValidDateFormat(parser.getFirstEnteredTime())) {
                message = tasks.addTask(new Task(parser.getTaskName(),
                        2, "Null", parser.getFirstEnteredTime(), false));
                haveChangesInUserList = true;
            } else {
                message = "Invalid date format. Please enter as /by yyyy-mm-dd.";
            }
            break;

        case "event":
            //event read book /from 2022-01-01 /to 2022-01-02
            if (isValidDateFormat(parser.getFirstEnteredTime()) &&
                    isValidDateFormat(parser.getSecondEnteredTime())) {
                message = tasks.addTask(new Task(parser.getTaskName(),
                        3, parser.getFirstEnteredTime(), parser.getSecondEnteredTime(), false));
                haveChangesInUserList = true;
            } else {
                message = "Invalid date format. Please enter as /from yyyy-mm-dd /to yyyy-mm-dd.";
            }
            break;

        case "delete":
            //delete 1
            try {
                int i = Integer.parseInt(parser.getTaskName()) - 1;
                message = tasks.deleteTask(i);
                haveChangesInUserList = true;
            } catch (NumberFormatException e) {
                message = "need to provide an integer index of task.";
            }

            break;

        case "find":
            message = tasks.findTask(parser.getTaskName());
            break;

        case "stats":
            message = tasks.showTaskStatics();
            break;

        case "sort":
            if (!(parser.getTaskName().equals("by start date") || parser.getTaskName().equals("by end date"))) {
                message = "Sorry, I do not know how you want to sort the tasks";
            } else {
                haveChangesInUserList = true;
                if (parser.getTaskName().equals("by start date")) {
                    tasks.sortByStartDate();
                }

                if (parser.getTaskName().equals("by end date")) {
                    tasks.sortByEndDate();
                }
                message = "Your list is now sorted!\n\n";

                message = message + tasks.displayList();
            }

            break;


        default:
            message = "OOPS!!! I'm sorry, but I don't know what that means :-(";


        }


        if (haveChangesInUserList) {
            storage.saveList(tasks.getUserList(), tasks.getUserListPointer());
        }
        return message;
    }

    /**
     * Returns greeting message.
     *
     * @return Greeting message.
     */
    public String greet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String message = "Hello from\n" + logo + "\n";
        message = message + ui.greet();

        return message;
    }
    public static void main(String[] args) {

        new Duke("list.txt", "data").run();



    }




}
