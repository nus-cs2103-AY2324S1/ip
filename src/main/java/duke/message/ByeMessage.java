package duke.message;

import duke.templates.MessageTemplates;

/**
 * Represents the ByeMessage.
 */
public class ByeMessage extends Message {
    /**
     * Prints ByeMessage.
     */
    @Override
    public void send() {
        System.out.println(createMessage(MessageTemplates.MESSAGE_BYE));
    }
}
