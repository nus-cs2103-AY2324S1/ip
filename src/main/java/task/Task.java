package task;

import duke.Duke;
import java.time.LocalDateTime;


public class Task {

    private String taskName;
    private Boolean isDone;
    private LocalDateTime dateAdded;

    /**
     * Calls the constructor to create a Task.
     *
     * @param taskName The name of the task.
     * @return Returns a Task object.
     */
    public static Task of(String taskName) {
        return new ToDos(taskName);
    }

    /**
     * Calls the constructor to create a Task.
     *
     * @param taskName The name of the task.
     * @param dayDate The date and time of the deadline of the task.
     * @return Returns a Task object.
     */
    public static Task of(String taskName, LocalDateTime dayDate) {
        return new Deadlines(taskName, dayDate);
    }

    /**
     * Calls the constructor to create a Task.
     *
     * @param taskName The name of the task.
     * @param startDayDateTime The date and time of the start of the event.
     * @param endDayDateTime The date and time of the end of the event.
     * @return Returns a Task object.
     */
    public static Task of(String taskName, LocalDateTime startDayDateTime, LocalDateTime endDayDateTime) {
        return new Event(taskName, startDayDateTime, endDayDateTime);
    }

    private Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.dateAdded = LocalDateTime.now();
    }

    /**
     * The string that represents the object.
     *
     * @return Returns a string that represents the object.
     */
    @Override
    public String toString() {
        return ("[" + (this.isDone ? "X] " : " ] ") + this.taskName);
    }

    /**
     * Mark the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Check if the task is done.
     *
     * @return Returns true if the task is done, false if otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return Returns the name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Writes the initial command the user put that resulted in the task.
     *
     * @return Returns the initial command the user put for this task.
     */
    public String write() {
        return "task " + this.taskName + "\n";
    }


    private static class ToDos extends Task {
        private ToDos(String taskName) {
            super(taskName);
        }

        /**
         * @inheritDoc
         */
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

        /**
         * @inheritDoc
         */
        @Override
        public String write() {
            return "todo " + super.taskName + "\n";
        }
    }

    private static class Deadlines extends Task {
        private LocalDateTime dayDate;
        private Deadlines(String taskName, LocalDateTime dayDate) {
            super(taskName);
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
            return "deadline " + super.taskName + "/by " + this.dayDate.format(Duke.FORMAT) + "\n";
        }
    }

    private static class Event extends Task {
        private LocalDateTime startDayDateTime;
        private LocalDateTime endDayDateTime;
        private Event(String taskName, LocalDateTime startDayDateTime, LocalDateTime endDayDateTime) {
            super(taskName);

            this.endDayDateTime = endDayDateTime;
            this.startDayDateTime = startDayDateTime;
        }

        /**
         * @inheritDoc
         */
        @Override
        public String toString() {
            return "[E]" + super.toString() + "(from: " + startDayDateTime.format(Duke.FORMAT)
                    + " to: " + endDayDateTime.format(Duke.FORMAT) +")";
        }

        /**
         * @inheritDoc
         */
        @Override
        public String write() {
            return "event " + super.taskName + "/from " + this.startDayDateTime.format(Duke.FORMAT)
                    + " /to " + this.endDayDateTime.format(Duke.FORMAT) + "\n";
        }
    }
}

