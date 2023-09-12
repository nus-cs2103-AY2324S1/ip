package duke.parser;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.AddDeadlineTask;
import duke.command.AddEventTask;
import duke.command.AddToDoTask;
import duke.command.Command;
import duke.command.DeleteTask;
import duke.command.Farewell;
import duke.command.FindTask;
import duke.command.Mark;
import duke.command.PrintList;
import duke.command.Unmark;


/** Abstraction to understand user input */
public class Parser {

    private Ui ui = new Ui();
    private TaskList list;
    private String filepath;

    /**
     * Creates a parser to parse commands inputed by user.
     *
     * @param list List to be updated by each command.
     */
    public Parser(TaskList list, String filepath) {
        this.filepath = filepath;
        this.list = list;
    }

    /**
     * Parses the input of the user.
     *
     * @param msg Inputed string by user.
     * @return Whether the bot should wait for next command or stop.
     */
    public Command parse(String msg) {
        String[] input = msg.split(" ", 2);
        String command = input[0];
        String specifications = input.length > 1 ? input[1] : "";
        switch (command) {
        case "bye":
            Storage.updateStorage(this.filepath, this.list);
            return new Farewell();
        case "list":
            return new PrintList(this.list);
        case "mark":
            return new Mark(this.list, specifications);
        case "unmark":
            return new Unmark(this.list, specifications);
        case "todo":
            return new AddToDoTask(this.list, specifications);
        case "deadline":
            return new AddDeadlineTask(this.list, specifications);
        case "event":
            return new AddEventTask(this.list, specifications);
        case "delete":
            return new DeleteTask(this.list, specifications);
        case "find":
            return new FindTask(this.list, specifications);
        default:
            throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
