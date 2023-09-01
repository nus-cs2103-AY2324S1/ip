package duke.tools;

public enum KeywordEnum {
    LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID;

    public static KeywordEnum assign(String task) {
        if (task.startsWith("list")) return KeywordEnum.LIST;
        else if (task.startsWith("bye")) return KeywordEnum.BYE;
        else if (task.startsWith("mark")) return KeywordEnum.MARK;
        else if (task.startsWith("unmark")) return KeywordEnum.UNMARK;
        else if (task.startsWith("todo")) return KeywordEnum.TODO;
        else if (task.startsWith("deadline")) return KeywordEnum.DEADLINE;
        else if (task.startsWith("event")) return KeywordEnum.EVENT;
        else if (task.startsWith("delete")) return KeywordEnum.DELETE;
        else return KeywordEnum.INVALID;
    }
}
