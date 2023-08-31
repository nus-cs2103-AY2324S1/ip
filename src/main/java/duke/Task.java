package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract class Task {
    protected boolean isDone = false;
    protected String description;
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public static Task createTask(String task) throws Duke.WrongCommandException, Duke.WrongFormatException {
        TaskType taskType = getTaskType(task);
        if (taskType == null) {
            throw new Duke.WrongCommandException("Whopsie daisies! I don't understand that command!");
        }

        switch (taskType) {
        case TODO:
            return new TodoTask(task);
        case DEADLINE:
            return new DeadlineTask(task);
        case EVENT:
            return new EventTask(task);
        default:
            return null;
        }
    }

    public static Task loadTask(String fileTask) throws Duke.WrongFormatException, Duke.InvalidFileException {
        String[] taskDetails = fileTask.split(" \\| ");
        try {
            TaskType taskType = TaskType.valueOf(taskDetails[0]);
            boolean isDone = taskDetails[1].equals("1");
            String description = taskDetails[2];

            switch (taskType) {
            case TODO:
                return new TodoTask(isDone, description);
            case DEADLINE:
                return new DeadlineTask(isDone, description, taskDetails[3]);
            case EVENT:
                return new EventTask(isDone, description, taskDetails[3], taskDetails[4]);
            default:
                return null;
            }
        } catch (NullPointerException | IllegalArgumentException | DateTimeException e) {
            throw new Duke.InvalidFileException("File is corrupted!");
        }
    }

    private static TaskType getTaskType(String input) {
        if (input.startsWith("todo")) {
            return TaskType.TODO;
        }

        if (input.startsWith("deadline")) {
            return TaskType.DEADLINE;
        }

        if (input.startsWith("event")) {
            return TaskType.EVENT;
        }

        return null;
    }

    private static String squareBracketWrapper(String input) {
        return "[" + input + "]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    protected abstract String getTaskTypeString();

    protected abstract String getDescription(String input);

    protected abstract String saveToFileString();

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    private static final class TodoTask extends Task {
        public TodoTask(String task) throws Duke.WrongFormatException {
            String description = getDescription(task);
            if (description == null) {
                throw new Duke.WrongFormatException("Whopsie daisies! I don't understand that format!");
            }
            this.description = description;
        }

        public TodoTask(boolean isDone, String description) {
            this.isDone = isDone;
            this.description = description;
        }

        @Override
        protected String getTaskTypeString() {
            return squareBracketWrapper("T");
        }

        @Override
        protected String saveToFileString() {
            return "TODO | " + (isDone ? "1" : "0") + " | " + description;
        }

        @Override
        protected String getDescription(String input) {
            if (input.split(" ", 2).length == 1) {
                return null;
            }
            return input.split(" ", 2)[1];
        }

        @Override
        public String toString() {
            return getTaskTypeString() + squareBracketWrapper(isDone ? "X" : " ") + " " + description;
        }
    }

    private static final class DeadlineTask extends Task {
        private LocalDate dateEnd;

        public DeadlineTask(String task) throws Duke.WrongFormatException {
            String description = getDescription(task);
            if (description == null) {
                throw new Duke.WrongFormatException("Whopsie daisies! I don't understand that format!");
            }
            this.description = description;
        }

        public DeadlineTask(boolean isDone, String description, String dateEnd) {
            this.isDone = isDone;
            this.description = description;
            this.dateEnd = LocalDate.parse(dateEnd);
        }

        @Override
        protected String getTaskTypeString() {
            return squareBracketWrapper("D");
        }

        @Override
        protected String saveToFileString() {
            return "DEADLINE | " + (isDone ? "1" : "0") + " | " + description + " | " + dateEnd;
        }

        @Override
        protected String getDescription(String input) {
            if (input.split(" ", 2).length == 1) {
                return null;
            }

            String[] split = input.split(" ", 2)[1].split(" /by ");

            if (split.length == 1) {
                return null;
            }

            try {
                this.dateEnd = LocalDate.parse(split[1]);
            } catch (DateTimeException e) {
                return null;
            }

            return split[0];
        }

        @Override
        public String toString() {
            return getTaskTypeString() + squareBracketWrapper(isDone ? "X" : " ") + " " + description
                    + " (by: " + dateEnd.format(formatter) + ")";
        }
    }

    private static final class EventTask extends Task {
        private LocalDate dateStart;
        private LocalDate dateEnd;

        public EventTask(String task) throws Duke.WrongFormatException {
            String description = getDescription(task);
            if (description == null) {
                throw new Duke.WrongFormatException("Whopsie daisies! I don't understand that format!");
            }
            this.description = description;
        }

        public EventTask(boolean isDone, String description, String dateStart, String dateEnd) {
            this.isDone = isDone;
            this.description = description;
            this.dateStart = LocalDate.parse(dateStart);
            this.dateEnd = LocalDate.parse(dateEnd);
        }

        @Override
        protected String getTaskTypeString() {
            return squareBracketWrapper("E");
        }

        @Override
        protected String saveToFileString() {
            return "EVENT | " + (isDone ? "1" : "0") + " | " + description + " | " + dateStart + " | " + dateEnd;
        }

        @Override
        protected String getDescription(String input) {
            if (input.split(" ", 2).length == 1) {
                return null;
            }

            String[] split = input.split(" ", 2)[1].split(" /from ");
            if (split.length == 1) {
                return null;
            }

            String[] split2 = split[1].split(" /to ");
            if (split2.length == 1) {
                return null;
            }

            try {
                this.dateStart = LocalDate.parse(split2[0]);
                this.dateEnd = LocalDate.parse(split2[1]);
            } catch (DateTimeException e) {
                return null;
            }

            return split[0];
        }

        @Override
        public String toString() {
            return getTaskTypeString() + squareBracketWrapper(isDone ? "X" : " ") + " " + description
                    + " (from: " + dateStart.format(formatter) + " to: " + dateEnd.format(formatter) + ")";
        }
    }
}
