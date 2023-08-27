package duke.message;

import duke.templates.MessageTemplates;

/**
 * Represents the WelcomeMessage.
 */
public class WelcomeMessage extends Message {
    /**
     * Prints WelcomeMessage.
     */
    @Override
    public void send() {
        System.out.println(
                createMessage(
                        MessageTemplates.MESSAGE_LINE,
                        MessageTemplates.MESSAGE_HI,
                        MessageTemplates.MESSAGE_LINE
                )
        );
    }
}
