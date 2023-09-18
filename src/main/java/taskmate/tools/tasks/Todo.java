package taskmate.tools.tasks;

import java.util.HashMap;

import taskmate.exceptions.InvalidAttributeException;

/**
 * The Todo class is a child class of the Task class that represents a 'Todo' type task specified by the user.
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public void update(HashMap<String, String> changes) throws InvalidAttributeException {
        for (HashMap.Entry<String, String> attributeValuePair : changes.entrySet()) {
            String attribute = attributeValuePair.getKey();
            String newValue = attributeValuePair.getValue();
            if (attribute.equals("/name")) {
                setName(newValue);
            } else {
                throw new InvalidAttributeException();
            }
        }
    }

    private void setName(String newName) {
        this.name = newName;
    }

    @Override
    String getTaskType() {
        return "Todo";
    }

    @Override
    public String toString() {
        return "[T][" + (this.getIsDone() ? 'X' : ' ') + "] " + this.name;
    }

    @Override
    public String formatTaskForSaving() {
        return this.toString();
    }
}
