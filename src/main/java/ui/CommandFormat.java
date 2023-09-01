package ui;

import java.util.HashMap;
import java.util.Map;

import commands.CommandType;

public class CommandFormat {
    public static final Map<CommandType, String> mapper = new HashMap<>();

    static {
        mapper.put(CommandType.MARK,
                "mark [task no.]");
        mapper.put(CommandType.UNMARK,
                "unmark [task no.]");
        mapper.put(CommandType.TODO,
                "todo [task]");
        mapper.put(CommandType.DEADLINE,
                "deadline [task] /by [yyyy-mm-dd]");
        mapper.put(CommandType.EVENT,
                "event [task] /from [yyyy-mm-dd] /to [yyyy-mm-dd]");
        mapper.put(CommandType.BYE,
                "bye");
        mapper.put(CommandType.LIST,
                "list");
        mapper.put(CommandType.DELETE,
                "delete [task no.]");
        mapper.put(CommandType.DATE,
                "date [yyyy-mm-dd]");
    }
}
