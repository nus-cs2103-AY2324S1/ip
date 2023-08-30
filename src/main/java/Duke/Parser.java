package Duke;

import Duke.Commands.*;

public class Parser {
    enum CommandTypes {
        BYE,
        DELETE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT
    }

    public static Command parse(String input, TaskList taskList, Storage storage,  Ui ui) {
        try {
            String[] inputs = input.split(" ");
            CommandTypes command = CommandTypes.valueOf(inputs[0].toUpperCase());
            switch (command) {
                case BYE:
                    return new ByeCommand(taskList, storage, ui);
                case DELETE:
                    return new DeleteCommand(taskList, storage, ui, Integer.parseInt(inputs[inputs.length - 1]));
                case LIST:
                    return new ListCommand(taskList, storage, ui);
                case MARK:
                    return new MarkCommand(taskList, storage, ui, Integer.parseInt(inputs[inputs.length - 1]));
                case UNMARK:
                    return new UnMarkCommand(taskList, storage, ui, Integer.parseInt(inputs[inputs.length - 1]));
                case TODO:
                    return new TodoCommand(input, taskList, storage, ui);
                case DEADLINE:
                    return new DeadlineCommand(input, taskList, storage, ui);
                case EVENT:
                    return new EventCommand(input, taskList, storage, ui);
                default:
                    return new ErrorCommand("I do not understand. Either I am wrong, or you are wrong, so you are wrong", ui);
            }
        } catch (Exception e) {
            return new ErrorCommand("I do not understand. Either I am wrong, or you are wrong, so you are wrong", ui);
        }
    }
}
