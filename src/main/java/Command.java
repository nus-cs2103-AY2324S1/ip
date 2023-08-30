import java.io.IOException;

public abstract class Command {
//    public static String enumCommand(Duke.Type command, String input) throws DukeException, IOException {
//        Duke.Type type = command;
//
//        switch (type) {
//            case LIST:
//                return TaskList.list();
//            case MARK:
//                return TaskList.mark(input);
//            case UNMARK:
//                return TaskList.unmark(input);
//            case TODO:
//                return todo(input);
//            case DEADLINE:
//                return deadline(input);
//            case EVENT:
//                return event(input);
//            case DELETE:
//                return TaskList.delete(input);
//            default:
//                throw new DukeException("I'm afraid I do not quite understand. Could you kindly repeat it?");
//        }
//    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
