package duke.message;

import duke.templates.MessageTemplates;

public class WelcomeMessage extends Message {
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
