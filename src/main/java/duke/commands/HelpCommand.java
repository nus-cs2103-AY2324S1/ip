package duke.commands;

/**
 * A command to print all the commands available in the chatBot.
 */
public class HelpCommand extends Command {

    /**
     * The command that will display the helper lines.
     */
    @Override
    public void execute() {
        String commands = (
                "____________________________________________________________\n"
                        + "I currently can record 3 types of tasks. tasks to do, tasks with deadlines and events \n"
                        + "Here is how you can record down your events.\n"
                        + "To record tasks to do, simply begin your command with \"todo\" "
                        + "followed by a space and the task you need to do.\n"
                        + "To record tasks with deadlines, simply begin your command with \"deadlines\" "
                        + "followed by the task, / and then the due date in DD/MM/YYYY HHmm format.\n"
                        + "To record events, simply begin your command with \"events\" followed by the event, "
                        + " \"/from\" and start time , "
                        + "then \"/to\" with the end time. All times must be in DD/MM/YYYY HHmm format \n"
                        + "To view your list of events, type list. \n" + "To mark your events as done or undone, "
                        + "followed by the index of the task \n"
                        + "To delete tasks from the list, type delete task number, such as delete 2 \n"
                        + "You can even filter the list to find certain tasks. Just type \"find\" followed "
                        + "by the keyword you want to search"
                        + "Lastly, to exit the chatBot, type \"bye\" \n"
                        + "____________________________________________________________\n"
        );
        System.out.println(commands);
    }
}
