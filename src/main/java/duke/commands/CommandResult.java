package duke.commands;

import java.util.List;

/**
 * Represents the result from executing a command.
 */
public class CommandResult {
    private final boolean shouldSave;
    private final boolean isTaskListDirty;
    private final List<String> response;

    CommandResult(String... response) {
        this.isTaskListDirty = false;
        this.response = List.of(response);
    }

    CommandResult(List<String> response) {
        this.isTaskListDirty = false;
        this.response = response;
    }

    CommandResult(boolean isTaskListDirty, String... response) {
        this.isTaskListDirty = isTaskListDirty;
        this.response = List.of(response);
    }

    CommandResult(boolean isTaskListDirty, List<String> response) {
        this.isTaskListDirty = isTaskListDirty;
        this.response = response;
    }

    public boolean isTaskListDirty() {
        return isTaskListDirty;
    }

    public List<String> getResponse() {
        return response;
    }
}
