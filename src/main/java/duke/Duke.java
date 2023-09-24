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
import duke.HelperClass.Task;
import duke.HelperClass.Storage;
import duke.HelperClass.Ui;
import duke.HelperClass.Parser;
import duke.HelperClass.TaskList;

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
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ui.speak(ui.greet());

        boolean wantToExit = false;
        boolean userListHaveChanges;

        Scanner getUserInput = new Scanner(System.in);

        while (!(wantToExit)) {

            userListHaveChanges = false;

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
                    userListHaveChanges = true;
                } catch (NumberFormatException e) {
                    ui.speak("need to provide an integer index of task.");
                }


                break;

            case "unmark":
                //unmark 1
                try {
                    int i = Integer.parseInt(parser.getTaskName()) - 1;
                    ui.speak(tasks.unmarkTask(i));
                    userListHaveChanges = true;
                } catch (NumberFormatException e) {
                    ui.speak("need to provide an integer index of task.");
                }

                break;

            case "todo":
                //todo read book
                ui.speak(tasks.addTask(new Task(parser.getTaskName(),
                        1, "Null", "Null", false)));
                userListHaveChanges = true;
                break;

            case "deadline":
                //deadline read book /by 2022-01-01
                ui.speak(tasks.addTask(new Task(parser.getTaskName(),
                        2, "Null", parser.getFirstEnteredTime(), false)));
                userListHaveChanges = true;
                break;

            case "event":
                //event read book /from 2022-01-01 /to 2022-01-02
                ui.speak(tasks.addTask(new Task(parser.getTaskName(),
                        3, parser.getFirstEnteredTime(), parser.getSecondEnteredTime(), false)));
                userListHaveChanges = true;
                break;

            case "delete":
                //delete 1
                try {
                    int i = Integer.parseInt(parser.getTaskName()) - 1;
                    ui.speak(tasks.deleteTask(i));
                    userListHaveChanges = true;
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


            if (userListHaveChanges) {
                storage.saveList(tasks.getUserList(), tasks.getUserListPointer());
            }





        }

        getUserInput.close();





    }



    public String getResponse(String input) {

        boolean userListHaveChanges = false;
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
                userListHaveChanges = true;
            } catch (NumberFormatException e) {
                message = "need to provide an integer index of task.";
            }


            break;

        case "unmark":
            //unmark 1
            try {
                int i = Integer.parseInt(parser.getTaskName()) - 1;
                message = tasks.unmarkTask(i);
                userListHaveChanges = true;
            } catch (NumberFormatException e) {
                message = "need to provide an integer index of task.";
            }

            break;

        case "todo":
            //todo read book
            message = tasks.addTask(new Task(parser.getTaskName(),
                    1, "Null", "Null", false));
            userListHaveChanges = true;
            break;

        case "deadline":
            //deadline read book /by 2022-01-01
            message = tasks.addTask(new Task(parser.getTaskName(),
                    2, "Null", parser.getFirstEnteredTime(), false));
            userListHaveChanges = true;
            break;

        case "event":
            //event read book /from 2022-01-01 /to 2022-01-02
            message = tasks.addTask(new Task(parser.getTaskName(),
                    3, parser.getFirstEnteredTime(), parser.getSecondEnteredTime(), false));
            userListHaveChanges = true;
            break;

        case "delete":
            //delete 1
            try {
                int i = Integer.parseInt(parser.getTaskName()) - 1;
                message = tasks.deleteTask(i);
                userListHaveChanges = true;
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


        default:
            message = "OOPS!!! I'm sorry, but I don't know what that means :-(";


        }


        if (userListHaveChanges) {
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
