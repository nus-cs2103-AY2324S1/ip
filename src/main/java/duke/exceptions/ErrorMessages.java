package duke.exceptions;

public class ErrorMessages {
    public static final String EMPTY_DEADLINE_ERROR = "OOPS!!! The description of a deadline task cannot be empty.";
    public static final String EMPTY_EVENT_ERROR = "OOPS!!! The description of an event task cannot be empty.";
    public static final String EMPTY_TODO_ERROR = "OOPS!!! The description of a todo task cannot be empty.";

    public static final String INVALID_DESCRIPTION_ERROR = "OOPS!!! The description cannot be empty.";
    public static final String INVALID_INDEX_ERROR = "OOPS!!! The input index is not within the range of [1, %d]. "
            + "Please input a index that is within the given range";
    public static final String INVALID_TASK_INDEX_ERROR = "OOPS!!! The input task index is not a number. "
            + "Please input a valid task index.";


    public static final String INVALID_DEADLINE_FORMAT_ERROR = "OOPS!!! Please input the deadline task in the "
            + "correct format. Example: deadline Quiz 1 /by 01/09/2023 2030";
    public static final String INVALID_EVENT_FORMAT_ERROR = "OOPS!!! Please input the event task in the "
            + "correct format. Example: event Tp /from 01/09/2023 2030 /to 01/09/2023 2230";
    public static final String INVALID_FROM_AND_TO_ERROR = "OOPS!!! Start date can not be after than the End date";


    public static final String INVALID_TYPE_ERROR = "Type tag of event should be [T], [D], or [E]";
    public static final String INVALID_STATUS_ERROR = "IsDone tag of event should be [ ], or [X]";

    public static final String EMPTY_ERROR = "OOPS!!! The instruction cannot be empty";
    public static final String STORAGE_ERROR = "OOPS!!! There's something wrong when reading the storage list";
    public static final String TASK_LIST_EMPTY_ERROR = "OOPS!!! Your task list is currently empty";
    public static final String UNRECOGNIZED_ERROR = "OOPS!!! I'm sorry, but I don't know what that means :-(\n "
            + "Please type in \"help\" to check all available commands.";
}
