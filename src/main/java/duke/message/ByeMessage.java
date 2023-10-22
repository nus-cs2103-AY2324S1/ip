package duke.message;

import duke.templates.MessageTemplates;

/**
 * Represents the ByeMessage.
 */
public class ByeMessage extends Message {
    /**
     * Returns String representation of ByeMessage.
     */
    @Override
    public String send() {
        return createMessage(MessageTemplates.MESSAGE_BYE);
    }
}
