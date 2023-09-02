package bot.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bot.exceptions.InvalidTaskException;


/**
 * Abstract task class for tasks that can be created for the task list in the chatbot.
 */
public abstract class Task {
    /**
     * Name of the task.
     */
    private String name;
    /**
     * Indicates if the task is completed.
     */
    private boolean isDone = false;

    /**
     * Default constructor.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Alternative constructor. Usually used when reading data from a file.
     *
     * @param name   Name of the task.
     * @param isDone Completion status of task.
     */
    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Creates a task based on the string input. Throws an InvalidTaskException
     * if no task can be created from the string.
     *
     * @param str Raw string to create task from.
     * @return Task object containing information from the string.
     * @throws InvalidTaskException If no task can be created.
     */
    public static Task makeTask(String str) throws InvalidTaskException {
        Task newTask;
        if (str.startsWith("todo")) {
            newTask = ToDo.makeToDo(str);
        } else if (str.startsWith("deadline")) {
            newTask = Deadline.makeDeadline(str);
        } else {
            newTask = Event.makeEvent(str);
        }
        return newTask;
    }

    /**
     * Reads a string of standardised data and constructs a Task object based on the information.
     *
     * @param str Data string.
     * @return Bot.Task object.
     * @throws InvalidTaskException If the task object cannot be created from the string.
     */
    public static Task convertFromString(String str) throws InvalidTaskException {
        if (str.startsWith("t")) {
            return ToDo.convertFromString(str);
        }
        if (str.startsWith("d")) {
            return Deadline.convertFromString(str);
        }
        return Event.convertFromString(str);
    }

    /**
     * Checks if the raw string contains a task command.
     *
     * @param str Raw string to check.
     * @return True if a task command is found, false otherwise.
     */
    public static boolean isTaskCommand(String str) {
        return str.startsWith("todo ") || str.startsWith("event ") || str.startsWith("deadline ");
    }

    /**
     * Gets completion status of the task.
     *
     * @return True if the task is complete, false otherwise.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    protected boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the name of the task.
     *
     * @return Name of the task.
     */
    protected String getName() {
        return this.name;
    }

    /**
     * Gets the string representation of the task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone() ? "X" : " ") + "] " + this.getName();
    }

    /**
     * Changes the task's completion status to complete.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Changes the task's completion status to incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Creates data string of task.
     *
     * @return Data string.
     */
    public abstract String convertToDataString();

    /**
     * ToDo subclass. Contains a name.
     */
    public static class ToDo extends Task {
        /**
         * Default constructor.
         *
         * @param name Name of task.
         */
        public ToDo(String name) {
            super(name);
        }

        /**
         * Alternative constructor. Usually used when reading data from a file.
         *
         * @param name   Name of ToDo.
         * @param isDone Completion status of Todo.
         */
        protected ToDo(String name, boolean isDone) {
            super(name, isDone);
        }

        /**
         * Reads a string of standardised data and creates a ToDo object.
         *
         * @param str Data string to read.
         * @return ToDo object.
         * @throws InvalidTaskException If an object cannot be created.
         */
        public static ToDo convertFromString(String str) throws InvalidTaskException {
            if (!str.matches("t/[01]/.+")) {
                throw new InvalidTaskException("Could not read Todo.");
            }
            String[] arr = str.split("/");
            return new ToDo(arr[2], arr[1].equals("1"));
        }

        /**
         * Checks for sameness of ToDo. ToDos are the same if they have the same name.
         *
         * @param o Object to compare to.
         * @return True if objects are the same, else false.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o instanceof ToDo) {
                return this.getName().equals(((ToDo) o).getName());
            }
            return false;
        }

        /**
         * Creates a ToDo object.
         *
         * @param str Raw string to create the ToDo object from.
         * @return ToDo object.
         * @throws InvalidTaskException If a ToDo object cannot be created from the string.
         */
        public static ToDo makeToDo(String str) throws InvalidTaskException {
            String name = str.substring(4).trim();
            if (name.equals("")) {
                throw new InvalidTaskException("Sorry, the todo description can't be empty.");
            }
            return new ToDo(name);
        }

        /**
         * String representation of the todo.
         *
         * @return String representation.
         */
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

        /**
         * Get data string representation.
         *
         * @return Data string.
         */
        public String convertToDataString() {
            return "t/" + (super.isDone() ? "1" : "0") + "/" + super.getName();
        }
    }

    /**
     * Deadline subclass. Contains a name and a time the deadline is due.
     */
    public static class Deadline extends Task {
        /**
         * The time the deadline is due.
         */
        private LocalDate by;

        /**
         * Default constructor.
         *
         * @param name Name of the deadline.
         * @param by   The time the deadline is due.
         */
        public Deadline(String name, LocalDate by) {
            super(name);
            this.by = by;
        }

        /**
         * Alternative constructor. Usually used when reading data from a file.
         *
         * @param name   Name of deadline.
         * @param isDone Completion status of deadline.
         * @param by     The time the deadline is due.
         */
        protected Deadline(String name, boolean isDone, LocalDate by) {
            super(name, isDone);
            this.by = by;
        }

        /**
         * Reads a string of standardised data and constructs a Deadline object based on the information.
         *
         * @param str Data string.
         * @return Deadline object.
         * @throws InvalidTaskException If Deadline object cannot be created.
         */
        public static Deadline convertFromString(String str) throws InvalidTaskException {
            if (!str.matches("d/[01]/.+/.+")) {
                throw new InvalidTaskException("Could not read Deadline.");
            }
            String[] arr = str.split("/");
            return new Deadline(arr[2], arr[1].equals("1"),
                    LocalDate.parse(arr[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        /**
         * Creates a Deadline object.
         *
         * @param str Raw string to create the Deadline object from.
         * @return Deadline object.
         * @throws InvalidTaskException If a Deadline object cannot be created from the string.
         */
        public static Deadline makeDeadline(String str) throws InvalidTaskException {
            String[] comps = str.split("/");
            if (comps.length != 2) {
                throw new InvalidTaskException("Please make sure the deadline is written in the correct format:\n"
                        + "deadline ... /by ...");
            } else if (comps[0].trim().equals("deadline")) {
                throw new InvalidTaskException("Sorry, the deadline description can't be empty.");
            } else if (comps[1].trim().equals("by")) {
                throw new InvalidTaskException("Sorry, the deadline can't be empty.");
            } else if (!comps[1].startsWith("by")) {
                throw new InvalidTaskException("Please make sure the deadline is written in the correct format:\n"
                        + "deadline ... /by ...");
            }
            LocalDate by;
            try {
                by = LocalDate.parse(comps[1].substring(3).trim());
            } catch (DateTimeParseException e) {
                throw new InvalidTaskException("One or more dates are invalid.");
            }
            if (by.isBefore(LocalDate.now())) {
                throw new InvalidTaskException("Deadline can't be before now!");
            }
            return new Deadline(comps[0].substring(9).trim(), by);
        }

        /**
         * String representation of the deadline.
         *
         * @return String representation.
         */
        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: "
                    + this.getBy().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ")";
        }

        /**
         * Get the deadline time.
         *
         * @return Deadline time.
         */
        protected LocalDate getBy() {
            return this.by;
        }

        /**
         * Returns data string representation.
         *
         * @return Data string.
         */
        public String convertToDataString() {
            return "d/" + (super.isDone() ? "1" : "0") + "/" + super.getName()
                    + "/" + this.getBy();
        }

        /**
         * Checks for sameness. Deadlines are the same if they have the same name and deadline.
         *
         * @param o Object to compare to.
         * @return True if objects are the same, else false.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o instanceof Deadline) {
                return this.getName().equals(((Deadline) o).getName())
                        && this.getBy().equals(((Deadline) o).getBy());
            }
            return false;
        }
    }

    /**
     * Event subclass. Contains a name, event start time and end time.
     */
    public static class Event extends Task {
        /**
         * Event start time.
         */
        private LocalDate from;
        /**
         * Event end time.
         */
        private LocalDate to;

        /**
         * Default constructor.
         *
         * @param name Event name.
         * @param from Event start time.
         * @param to   Event end time.
         */
        public Event(String name, LocalDate from, LocalDate to) {
            super(name);
            this.from = from;
            this.to = to;
        }

        /**
         * Alternative constructor. Usually used when reading data from a file.
         *
         * @param name   Name of task.
         * @param isDone Completion status of task.
         * @param from   Start time of task.
         * @param to     End time of task.
         */
        protected Event(String name, boolean isDone, LocalDate from, LocalDate to) {
            super(name, isDone);
            this.from = from;
            this.to = to;
        }

        /**
         * Reads a string of standardised data and constructs an Event object based on the information.
         *
         * @param str Data string.
         * @return Event object.
         * @throws InvalidTaskException If an Event object cannot be created.
         */
        public static Event convertFromString(String str) throws InvalidTaskException {
            if (!str.matches("e/[01]/.+/.+/.+")) {
                throw new InvalidTaskException("Could not read Event.");
            }
            String[] arr = str.split("/");
            return new Event(arr[2], arr[1].equals("1"), LocalDate.parse(arr[3]), LocalDate.parse(arr[4]));
        }

        /**
         * Creates an Event object.
         *
         * @param str Raw string to create the object from.
         * @return Event object.
         * @throws InvalidTaskException If the input string cannot create the Event object.
         */
        public static Event makeEvent(String str) throws InvalidTaskException {
            String[] comps = str.split("/");
            if (comps.length != 3) {
                throw new InvalidTaskException("Please make sure the event is written in the correct format:\n"
                        + "event ... /from ... /to ...");
            } else if (comps[0].trim().equals("event")) {
                throw new InvalidTaskException("Sorry, the event description can't be empty.");
            } else if (comps[1].trim().equals("from")) {
                throw new InvalidTaskException("Sorry, event start time can't be empty.");
            } else if (comps[2].trim().equals("to")) {
                throw new InvalidTaskException("Sorry, event end time can't be empty.");
            } else if (!comps[1].startsWith("from ") || !comps[2].startsWith("to ")) {
                throw new InvalidTaskException("Please make sure the event is written in the correct format:\n"
                        + "event ... /from ... /to ...");
            }
            LocalDate from;
            LocalDate to;
            try {
                from = LocalDate.parse(comps[1].substring(5).trim());
                to = LocalDate.parse(comps[2].substring(3).trim());
            } catch (DateTimeParseException e) {
                throw new InvalidTaskException("One or more dates are invalid.");
            }
            if (from.isAfter(to)) {
                throw new InvalidTaskException("Event end time can't be before event start time!");
            }
            return new Event(comps[0].substring(6).trim(), from, to);
        }

        /**
         * Get event start time.
         *
         * @return Event start time.
         */
        protected LocalDate getFrom() {
            return this.from;
        }

        /**
         * Get event end time.
         *
         * @return Event end time.
         */
        protected LocalDate getTo() {
            return this.to;
        }

        /**
         * String representation of the event.
         *
         * @return String representation.
         */
        @Override
        public String toString() {
            return "[E]" + super.toString()
                    + " (from: "
                    + this.getFrom().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to: " + this.getTo().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ")";
        }

        /**
         * Returns data string representation.
         *
         * @return Data string.
         */
        public String convertToDataString() {
            return "e/" + (super.isDone() ? "1" : "0") + "/" + super.getName()
                    + "/" + this.getFrom() + "/" + this.getTo();
        }

        /**
         * Checks for sameness. Events are the same if they have the same name, start time
         * and end time.
         *
         * @param o Object to compare to.
         * @return True if objects are the same, else false.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o instanceof Event) {
                return this.getName().equals(((Event) o).getName())
                        && this.getFrom().equals(((Event) o).getFrom())
                        && this.getTo().equals(((Event) o).getTo());
            }
            return false;
        }
    }
}
