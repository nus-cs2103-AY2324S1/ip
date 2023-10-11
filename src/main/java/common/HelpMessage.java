package common;

/**
 * Utility class for getting the help messages for the user.
 *
 * @author Ho Khee Wei
 */
public class HelpMessage {

    public static final String TODO = "Function: Add a to-do task to the tasklist.\n\n"
            + "Syntax: todo [Description] /priority(optional) [Priority]\n\n"
            + "Priority = Any integer from 0-5.\n\n"
            + "Example: todo Find food for Thorndike.\n\n"
            + "Example: todo Find food for Thorndike /priority 2.";

    public static final String DEADLINE = "Function: Add a task with deadline to the tasklist.\n\n"
            + "Syntax: deadline [Description] /by [Date] /priority(optional) [Priority]\n\n"
            + "Priority = Any integer from 0-5.\n\n"
            + "Date = Any date and time. Time is optional and will default to 0000 if not specified.\n\n"
            + "Example: deadline Find food for Thorndike. /by 19-9-2023 1900.\n\n"
            + "Example: deadline Find food for Thorndike. /by 19-9-2023 1900. /priority 2\n\n";

    public static final String EVENT = "Function: Add a event to the tasklist.\n\n"
            + "Syntax: deadline [Description] /from [Date] /to [Date]\n\n"
            + "Priority = Any integer from 0-5.\n\n"
            + "Date = Any date and time. Time is optional and will default to 0000 if not specified.\n\n"
            + "Example: deadline Attend Thorndike's birthday party. /by 19-9-2023 1900 /to 20-9-2023 0800.\n\n"
            + "Example: deadline Attend Thorndike's birthday party. /by 19-9-2023 /to 20-9-2023. /priority 2\n\n";

    public static final String EXIT = "Function: Exit the program.\n\n";

    public static final String DELETE = "Function: Delete task in the task list by index.\n\n"
            + "Syntax: delete [Index]\n\n"
            + "Example: delete 5\n\n";

    public static final String FIND = "Function: Find a task that matches the keyword given.\n\n"
            + "Syntax: find [Keyword]\n\n"
            + "Example: find catfood";

    public static final String LIST = "Function: Lists all tasks in the tasklist.\n";

    public static final String MARK = "Function: Mark a task at a given index as done.\n\n"
            + "Syntax: mark [Index]\n\n"
            + "Index = The index of the task in the tasklist. Send 'list' to view the tasklist.\n\n"
            + "Example: mark 3";

    public static final String UNMARK = "Function: Mark a task at a given index as not done.\n\n"
            + "Syntax: unmark [Index]\n\n"
            + "Index = The index of the task in the tasklist. Send 'list' to view the tasklist.\n\n"
            + "Example: unmark 3";

    public static final String PRIORITY = "Function: Change the priority of a task.\n\n"
            + "Syntax: priority [Index] /set [Priority]\n\n"
            + "Index = The index of the task in the tasklist. Send 'list' to view the tasklist.\n\n"
            + "Priority = Any integer from 0-5.\n\n"
            + "Example: priority 3 /set 5";

    public static final String TOGGLE = "Function: Switch to dark/light mode.\n\n";

    public static final String INVALID = "Here are the list of commands:\n"
            + "1. todo\n"
            + "2. deadline\n"
            + "3. event\n"
            + "4. bye\n"
            + "5. delete\n"
            + "6. find\n"
            + "7. list\n"
            + "8. mark\n"
            + "9. unmark\n"
            + "10. priority\n"
            + "11. toggle\n"
            + "Send 'help [Command]' to see the details of each commands.";

}
