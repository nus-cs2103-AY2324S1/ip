package duke;
/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

*/
import duke.helper.Task;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.helper.Parser;
import duke.helper.TaskList;

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

    public void run() {

        ui.speak(greet());

        boolean wantToExit = false;
        boolean haveChangesInUserList;

        Scanner getUserInput = new Scanner(System.in);

        while (!(wantToExit)) {

            haveChangesInUserList = false;

            String userInput = getUserInput.nextLine();
            parser.processUserCommand(userInput);

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
                ui.speak(tasks.addTask(new Task(parser.getTaskName(),
                        2, "Null", parser.getFirstEnteredTime(), false)));
                haveChangesInUserList = true;
                break;

            case "event":
                //event read book /from 2022-01-01 /to 2022-01-02
                ui.speak(tasks.addTask(new Task(parser.getTaskName(),
                        3, parser.getFirstEnteredTime(), parser.getSecondEnteredTime(), false)));
                haveChangesInUserList = true;
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


            default:
                ui.speak("OOPS!!! I'm sorry, but I don't know what that means :-(");


            }


            if (haveChangesInUserList) {
                storage.saveList(tasks.getUserList(), tasks.getUserListPointer());
            }





        }

        getUserInput.close();





    }



    public String getResponse(String input) {

        boolean haveChangesInUserList = false;
        String message = "";
        parser.processUserCommand(input);

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
            message = tasks.addTask(new Task(parser.getTaskName(),
                    2, "Null", parser.getFirstEnteredTime(), false));
            haveChangesInUserList = true;
            break;

        case "event":
            //event read book /from 2022-01-01 /to 2022-01-02
            message = tasks.addTask(new Task(parser.getTaskName(),
                    3, parser.getFirstEnteredTime(), parser.getSecondEnteredTime(), false));
            haveChangesInUserList = true;
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
                message = "Your list is not sorted!\n\n";

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
