package duke.message;

import duke.templates.MessageTemplates;

/**
 * Represents the MenuMessage.
 */
public class MenuMessage extends Message {
    /**
     * Returns String representation of MenuMessage.
     */
    @Override
    public String send() {
        return createMessage(MessageTemplates.MESSAGE_MENU);
    }
}
