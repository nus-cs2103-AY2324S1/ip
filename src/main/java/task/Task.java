package task;

import duke.Duke;
import java.time.LocalDateTime;


public class Task {

    private String taskName;
    private Boolean done;
    private LocalDateTime dateAdded;

    public static Task of(String taskName) {
        return new ToDos(taskName);
    }

    public static Task of(String taskName, LocalDateTime dayDate) {
        return new Deadlines(taskName, dayDate);
    }

    public static Task of(String taskName, LocalDateTime startDayDateTime, LocalDateTime endDayDateTime) {
        return new Event(taskName, startDayDateTime, endDayDateTime);
    }

    /**
     * Construct the task object.
     * @param taskName The description or name of the task.
     */
    private Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
        this.dateAdded = LocalDateTime.now();
    }

    /**
     * The string that represents the object.
     * @return Returns a string that represents the object.
     */
    @Override
    public String toString() {
        return ("[" + (this.done ? "X] " : " ] ") + this.taskName);
    }

    /**
     * Check if the task is done.
     * @return Returns true if the task is done, false if otherwise.
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Mark the task as done.
     */
    protected void mark() {
        this.done = true;
    }

    /**
     * Mark the task as undone.
     */
    protected void unMark() {
        this.done = false;
    }

    /**
     * Writes the initial command the user put that resulted in the task.
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
         * The string that represents the object.
         * @return Returns a string that represents the object.
         */
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

        /**
         * Writes the initial command the user put that resulted in the task.
         * @return Returns the initial command the user put for this task.
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
         * The string that represents the object.
         * @return Returns a string that represents the object.
         */
        @Override
        public String toString() {
            return "[D]" + super.toString() + "(by: " + this.dayDate.format(Duke.FORMAT) + ")";
        }

        /**
         * Writes the initial command the user put that resulted in the task.
         * @return Returns the initial command the user put for this task.
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
         * The string that represents the object.
         * @return Returns a string that represents the object.
         */
        @Override
        public String toString() {
            return "[E]" + super.toString() + "(from: " + startDayDateTime.format(Duke.FORMAT) + " to: " + endDayDateTime.format(Duke.FORMAT) +")";
        }

        /**
         * Writes the initial command the user put that resulted in the task.
         * @return Returns the initial command the user put for this task.
         */
        @Override
        public String write() {
            return "event " + super.taskName + "/from " + this.startDayDateTime.format(Duke.FORMAT) + " /to " + this.endDayDateTime.format(Duke.FORMAT) + "\n";
        }
    }
}

