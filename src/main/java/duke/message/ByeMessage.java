package duke.message;

import duke.templates.MessageTemplates;

public class ByeMessage extends Message {
    @Override
    public void send() {
        System.out.println(createMessage(MessageTemplates.MESSAGE_BYE));
    }
}
