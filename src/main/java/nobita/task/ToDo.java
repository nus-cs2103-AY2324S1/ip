package nobita.task;

import nobita.exception.NobitaException;

import java.util.StringJoiner;

/**
 * Class that encapsulates ToDo which extends from Task.
 *
 * @author Zheng Chenglong
 */
public class ToDo extends Task {

    /**
     * Constructs ToDo using name of task.
     *
     * @param taskName The name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Return a String representation of ToDo.
     *
     * @return A String representing of ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Return a String representation of ToDo that is formatted for file reading and writing.
     *
     * @return A String representation of ToDo that is formatted for file reading and writing.
     */
    @Override
    public String toFileFormat() {
        StringJoiner joiner = new StringJoiner(";");
        joiner.add("T").add(super.toFileFormat());
        return joiner.toString();
    }
}
