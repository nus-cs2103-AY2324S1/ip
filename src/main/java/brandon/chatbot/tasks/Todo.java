package brandon.chatbot.tasks;

import brandon.chatbot.common.DukeException;

public class Todo extends Task {

    public Todo(String title) throws DukeException {
        super(title);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

}

