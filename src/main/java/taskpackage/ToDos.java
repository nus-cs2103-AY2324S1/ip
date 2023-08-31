package taskpackage;

import dukepackage.DukeException;

public class ToDos extends Task {

    public ToDos(String task, String isDone) throws DukeException {
        super(task, isDone);
    }

    @Override
    public String printTask() {
        return String.format("[T]%s", super.printTask());
    }

    @Override
    public String addToStorage() {
        return String.format("T %s%n", super.addToStorage());
    }
}
