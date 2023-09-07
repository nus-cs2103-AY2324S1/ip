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
import HelperClass.Task;
import HelperClass.Storage;
import HelperClass.Ui;
import HelperClass.Parser;
import HelperClass.TaskList;

import java.util.Scanner;

public class Duke {



    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;


    public Duke(String fileName, String dirName) {
        ui = new Ui("Rio");
        storage = new Storage(fileName, dirName);
        try {
            tasks = new TaskList(storage.LoadList(), storage.getListPointer());
        } catch (Exception e) {
            ui.Speak(e.toString());
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

        ui.Speak(ui.Greet());

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
                ui.Speak(ui.Exit());

                break;

            case "list":
                //list
                ui.Speak(tasks.displayList());
                break;

            case "mark":
                //mark 1

                try {
                    int i = Integer.parseInt(parser.getTaskName()) - 1;
                    ui.Speak(tasks.markTask(i));
                    userListHaveChanges = true;
                } catch (NumberFormatException e) {
                    ui.Speak("need to provide an integer index of task.");
                }


                break;

            case "unmark":
                //unmark 1
                try {
                    int i = Integer.parseInt(parser.getTaskName()) - 1;
                    ui.Speak(tasks.unmarkTask(i));
                    userListHaveChanges = true;
                } catch (NumberFormatException e) {
                    ui.Speak("need to provide an integer index of task.");
                }

                break;

            case "todo":
                //todo read book
                ui.Speak(tasks.addTask(new Task(parser.getTaskName(),
                        1, "Null", "Null", false)));
                userListHaveChanges = true;
                break;

            case "deadline":
                //deadline read book /by 2022-01-01
                ui.Speak(tasks.addTask(new Task(parser.getTaskName(),
                        2, "Null", parser.getFirstEnteredTime(), false)));
                userListHaveChanges = true;
                break;

            case "event":
                //event read book /from 2022-01-01 /to 2022-01-02
                ui.Speak(tasks.addTask(new Task(parser.getTaskName(),
                        3, parser.getFirstEnteredTime(), parser.getSecondEnteredTime(), false)));
                userListHaveChanges = true;
                break;

            case "delete":
                //delete 1
                try {
                    int i = Integer.parseInt(parser.getTaskName()) - 1;
                    ui.Speak(tasks.deleteTask(i));
                    userListHaveChanges = true;
                } catch (NumberFormatException e) {
                    ui.Speak("need to provide an integer index of task.");
                }

                break;

            case "find":
                ui.Speak(tasks.findTask(parser.getTaskName()));
                break;


            default:
                ui.Speak("OOPS!!! I'm sorry, but I don't know what that means :-(");


            }


            if (userListHaveChanges) {
                storage.SaveList(tasks.getUserList(), tasks.getUserListPointer());
            }





        }

        getUserInput.close();





    }


    public static void main(String[] args) {

        new Duke("list.txt", "data").run();


    }




}
