package duke;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidSyntaxException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.Todos;
import duke.task.TaskList;
import duke.command.Command;
import duke.Parser;
import duke.Ui;

import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.Scanner;
public class Duke {
    private static Ui ui = new Ui();

    public static void main(String[] args) {
        boolean listen = true;
        /** Captures user input*/
        Scanner jonBird = new Scanner(System.in);
        /** Stores user input*/
        TaskList inputList =  new TaskList();
        /** User input*/
        String input = "";

        Parser parser = new Parser();

        Storage storage = new Storage();
        inputList = storage.loadData();

        ui.runProgram();
        while (listen) {
            input = jonBird.nextLine().trim();
            String[] inp = input.split("\\s+");
            try {
                Command currentCommand = parser.parse(inp, inputList);
                currentCommand.execute(inputList, storage);
                listen = currentCommand.isContinue();
            } catch (DukeException e) {
                System.out.println("JonBird:\n\t" + e.toString());
            } catch (DateTimeException e) {
                System.out.println("JonBird:\n\t" + "Please ensure that your date is in \"yyyy-MM-dd HH:mm\"" +
                        " format. Put 00:00 if time does not matter.");
            }

        }
        jonBird.close();
    }

}
