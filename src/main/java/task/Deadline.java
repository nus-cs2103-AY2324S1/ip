package task;

import java.time.LocalDateTime;

import duke.Duke;

/**
 * This class represents the task type Deadline.
 */
class Deadline extends Task {
    private LocalDateTime dayDate;

    /**
     * Creates a Deadline object.
     *
     * @param taskDescription The name of the task.
     * @param dayDate The date and time of the deadline of the task.
     * @return Returns a Task object.
     */
    Deadline(String taskDescription, LocalDateTime dayDate) {
        super(taskDescription);
        this.dayDate = dayDate;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dayDate.format(Duke.FORMAT) + ")";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String write() {
        return "deadline " + super.getTaskDescription() + "/by " + this.dayDate.format(Duke.FORMAT) + "\n";
    }
}
