package duke.commands;

import java.util.List;

public class CommandResult {
    public final boolean shouldSave;
    public List<String> response;

    CommandResult(String... response) {
        this.shouldSave = false;
        this.response = List.of(response);
    }

    CommandResult(List<String> response) {
        this.shouldSave = false;
        this.response = response;
    }

    CommandResult(boolean shouldSave, String... response) {
        this.shouldSave = shouldSave;
        this.response = List.of(response);
    }

    CommandResult(boolean shouldSave, List<String> response) {
        this.shouldSave = shouldSave;
        this.response = response;
    }
}
