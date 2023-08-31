package ui;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {
    public static final Map<String, String> mapper = new HashMap<>();

    static {
        mapper.put("InvalidCommandException",
                "Can't believe you're asking that! Grrr, what do you want now?");
        mapper.put("EmptyDescException",
                "Seriously? You want me to do something with an empty description?");
        mapper.put("InvalidDescFormatException",
                "Are you trying to confuse me with this nonsense? Try again hooman!");
        mapper.put("TaskStatusException",
                "Grrr! Are you testing my patience? The task is already in that status!");
        mapper.put("TaskListIndexOutOfBoundsException",
                "Arf! Invalid task number? Seriously, can't you count? 💢");
    }
}
