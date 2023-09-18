package duke.exception;

import duke.util.Ui;

public class AliasException extends DukeException {

    /**
     * Constructs a <code>AliasException</code>.
     */
    public AliasException() {
        super(Ui.connectLines("OOPS!!! The alias command cannot be empty!\n",
                "Enter in the form: \"alias [alias_from] [alias_to]\"\n",
                "Example: \"alias t todo\" To add an alias for \"todo\"\n",
                "Example: \"alias t\" To remove an alias for \"todo\"\n"));
    }
}
