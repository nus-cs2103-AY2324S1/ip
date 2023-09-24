package urchatbot.common;

/**
 * Contains user visible messages.
 */
public class Messages {
    public static final String LOGO =
            "             ____   _____\n"
                    + "|   | |  |  /  ___|  |   )   |\n"
                    + "|   | |  |  | |        | ___ /\n"
                    + "|   |_| |  | |___    |   )  \\\n"
                    + "\\___,_|  \\_____| |_____|\n";
    public static final String MESSAGE_WELCOME = "Hello! I'm URChatBot.\nWhat can I do for you?\n" + LOGO;
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_LIST = "Here are the tasks in your list: ";
    public static final String MESSAGE_CLEAR = "All tasks are cleared";
    public static final String MESSAGE_MARK = "Nice! I've marked this task as done:\n";
    public static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:\n";
    public static final String MESSAGE_FIND = "Here are the matching tasks in your list: ";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task: ";
    public static final String MESSAGE_ADD = "Noted. I've added this task:\n ";
    public static final String MESSAGE_NOW_YOU_HAVE = "\nNow you have ";
    public static final String MESSAGE_TASK_IN_THE_LIST = " task in the list.";
    public static final String MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL = " tasks in the list.";
    public static final String MESSAGE_PRINT = "There are a total of ";
    public static final String MESSAGE_PRINT_TWO = " task on ";
    public static final String MESSAGE_PRINT_TWO_PLURAL = " tasks on ";
    public static final String MESSAGE_LOADING_ERROR = "Loading failed";
    public static final String MESSAGE_FIND_FREE_TIME = "Free time slots within the specified date range:";
    public static final String MESSAGE_NO_FREE_TIME = "No free time dates found within the specified date range.";
}
