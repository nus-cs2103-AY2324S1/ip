package taskmate.tools.tasks;

import java.util.HashMap;

import taskmate.exceptions.InvalidTodoUpdateException;

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
    public HashMap<String, String> update(HashMap<String, String> changes) throws InvalidTodoUpdateException {

        // Check if update command is valid
        checkValidUpdate(changes);

        // Set updates if update command is valid
        return setUpdatesAndReturnSuccessfulUpdates(changes);
    }

    protected void checkValidUpdate(HashMap<String, String> changes) throws InvalidTodoUpdateException {
        for (HashMap.Entry<String, String> attributeValuePair : changes.entrySet()) {
            String attribute = attributeValuePair.getKey();
            boolean isName = attribute.equals("/name");
            if (!isName) {
                throw new InvalidTodoUpdateException();
            }
        }
    }

    protected HashMap<String, String> setUpdatesAndReturnSuccessfulUpdates(HashMap<String, String> changes) {
        HashMap<String, String> successfulUpdates = new HashMap<>();
        for (HashMap.Entry<String, String> attributeValuePair : changes.entrySet()) {
            String newValue = attributeValuePair.getValue();
            setName(newValue);
            successfulUpdates.put("name", newValue);
        }
        return successfulUpdates;
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
