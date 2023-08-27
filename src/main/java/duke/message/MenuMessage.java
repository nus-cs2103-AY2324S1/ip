package duke.message;

import duke.templates.MessageTemplates;

/**
 * Represents the MenuMessage.
 */
public class MenuMessage extends Message {
    /**
     * Prints MenuMessage.
     */
    @Override
    public void send() {
        System.out.println(createMessage(MessageTemplates.MESSAGE_MENU));
    }
}
