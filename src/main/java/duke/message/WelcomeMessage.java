package duke.message;

import duke.templates.MessageTemplates;

/**
 * Represents the WelcomeMessage.
 */
public class WelcomeMessage extends Message {
    /**
     * Returns String representation of WelcomeMessage.
     */
    @Override
    public String send() {
        return createMessage(
                MessageTemplates.MESSAGE_LINE,
                MessageTemplates.MESSAGE_HI,
                MessageTemplates.MESSAGE_LINE
        );
    }
}
