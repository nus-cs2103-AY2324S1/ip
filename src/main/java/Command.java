//public enum Command {
//    BYE,
//    LIST,
//    MARK,
//    UNMARK,
//    TODO,
//    DEADLINE,
//    EVENT,
//    DELETE,
//}

abstract class Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public boolean isExit() {
        return false;
    }
}