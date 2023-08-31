package duke.task;

import duke.Duke;

final class TodoTask extends Task {
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
