package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.Duke;

final class DeadlineTask extends Task {
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
