package brandon.chatbot.commands;

/**
 * Represents a feedback to be shown to the user after executing a command.
 */
public class Feedback {
    public static final String TITLE_BLANK = "title of the task cannot be blank...ㅠㅅㅠ\n";
    public static final String DEADLINE_BLANK = "deadline date of a deadline cannot be blank... ㅠㅅㅠ\n";
    public static final String STARTING_TIME_BLANK = "starting time of an event cannot be blank... ㅠㅅㅠ\n";
    public static final String ENDING_TIME_BLANK = "ending time of an event cannot be blank... ㅠㅅㅠ\n";
    public static final String FIND_SUCCESS = "ok... i'm finding the task...\n";
    public static final String FIND_FAIL = "sorry... i couldn't find any task...ㅠㅅㅠ\n";
    public static final String FIND_EMPTY_ARGUMENTS = "sorry... i don't know what you are finding...ㅠㅅㅠ\n"
            + "could you give me either the title or a tag of the task...?";
    public static final String ADD_SUCCESS = "ok... i'm adding..\n";
    public static final String LIST_SUCCESS = "ok... i'm listing..\n";
    public static final String LIST_FAIL = "sorry... there is no task right now... ㅠㅅㅠ\n";
    public static final String MARK_SUCCESS = "ok... I'm marking...-ㅅ-\n";
    public static final String UNMARK_SUCCESS = "ok... I'm unmarking...-ㅅ-\n";
}
