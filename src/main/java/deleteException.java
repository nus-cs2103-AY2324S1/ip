public class deleteException extends DukeException {
    public deleteException() {
        super("The format for deleting a task is incorrect. Please use: 'delete [index of task]'");
    }
}
