package emiya.commands;

public class ListCommand extends Command {
    @Override
    public String giveHelpDescription() {
        return "List: This command shows you all tasks currently within the list of tasks!\n"
                + "The format for the input is as follows:\n"
                + "list\n";
    }
}
