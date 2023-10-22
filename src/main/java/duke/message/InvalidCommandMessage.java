package duke.message;

import duke.templates.MessageTemplates;

/**
 * Represents the InvalidCommandMessage.
 */
public class InvalidCommandMessage extends Message {
    /**
     * Returns String representation of InvalidCommandMessage.
     */
    @Override
    public String send() {
        return createMessage(MessageTemplates.MESSAGE_INVALID_COMMAND);
    }
}
