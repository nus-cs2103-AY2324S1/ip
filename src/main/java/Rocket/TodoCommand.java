package Rocket;

import Rocket.AddCommand;
import Rocket.Todo;

public class TodoCommand extends AddCommand {
    private String description;

    public TodoCommand (String description, boolean isDone) {
        super(new Todo(description, isDone));
    }
}
