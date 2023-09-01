package haste.tasks;

import haste.exceptions.EmptyTaskException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDo extends Task {
    public ToDo(String description, boolean isComplete) {
        super(description, isComplete);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
    public static ToDo interpret(String cmd) throws EmptyTaskException {
        Pattern pt = Pattern.compile("todo( (.+))?"); // ( ...)? is optional group
        Matcher mt = pt.matcher(cmd);
        mt.find();
        String desc = mt.group(2);
        if (Task.checkEmpty(desc)) { // check if desc is null
            throw new EmptyTaskException("tasks.ToDo");
        }
        return new ToDo(desc, false);
    }

    @Override
    public String save() {
        return "T|" + super.save();
    }
}

