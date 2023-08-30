package duke.exceptions;

public class DukeNoTaskFoundException extends DukeException {
    private int taskNum;

    public DukeNoTaskFoundException(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String toString() {
        return String.format("%s task number %d does not exist",
                super.toString(),
                this.taskNum
        );
    }
}