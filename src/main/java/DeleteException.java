public class DeleteException extends DukeException {
    public DeleteException() {
        super("The format for deleting a task is incorrect. Please use: 'delete [index of task]'");
    }
}
