package duke;

import duke.commands.Deadline;
import duke.commands.Delete;
import duke.commands.Event;
import duke.commands.Mark;
import duke.commands.Sort;
import duke.commands.Todo;
import duke.commands.Unmark;
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
            message = bye();
            break;

        case "list":
            //list
            message = tasks.displayList();
            break;

        case "mark":
            //mark 1
            Mark mark = new Mark(storage, parser, tasks);
            message = mark.execute();
            break;

        case "unmark":
            //unmark 1
            Unmark unmark = new Unmark(storage, parser, tasks);
            message = unmark.execute();
            break;

        case "todo":
            //todo read book
            Todo todo = new Todo(storage, parser, tasks);
            message = todo.execute();
            break;

        case "deadline":
            //deadline read book /by 2022-01-01
            Deadline deadline = new Deadline(storage, parser, tasks);
            message = deadline.execute();
            break;

        case "event":
            //event read book /from 2022-01-01 /to 2022-01-02
            Event event = new Event(storage, parser, tasks);
            message = event.execute();
            break;

        case "delete":
            //delete 1
            Delete delete = new Delete(storage, parser, tasks);
            message = delete.execute();
            break;

        case "find":
            message = tasks.findTask(parser.getTaskName());
            break;

        case "stats":
            message = tasks.showTaskStatistics();
            break;

        case "sort":
            Sort sort = new Sort(storage, parser, tasks);
            message = sort.execute();
            break;

        default:
            message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
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

    /**
     * Returns message for saying goodbye.
     *
     * @return Goodbye message.
     */
    public String bye() {
        return ui.exit();
    }
    public static void main(String[] args) {

        new Duke("list.txt", "data").run();



    }




}
