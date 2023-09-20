package brandon.chatbot.commands;

/**
 * Represents a feedback to be shown to the user after executing a command.
 */
public class Feedback {
    public static final String TITLE_BLANK = "title of the task cannot be blank...ㅠㅅㅠ";
    public static final String DEADLINE_BLANK = "deadline date of a deadline cannot be blank... ㅠㅅㅠ";
    public static final String STARTING_TIME_BLANK = "starting time of an event cannot be blank... ㅠㅅㅠ";
    public static final String ENDING_TIME_BLANK = "ending time of an event cannot be blank... ㅠㅅㅠ";
    public static final String FIND_SUCCESS = "ok... i'm finding the task...\n";
    public static final String FIND_FAIL = "sorry... i couldn't find any task...ㅠㅅㅠ";
    public static final String FIND_EMPTY_ARGUMENTS = "sorry... i don't know what you are finding...ㅠㅅㅠ\n"
            + "could you give me either the title or a tag of the task...?";
    public static final String ADD_SUCCESS = "ok... i'm adding..";
    public static final String LIST_SUCCESS = "ok... i'm listing..";
    public static final String LIST_FAIL = "there is no task to list... ㅠㅅㅠ";

}
