package bot.command;

public class TerminateCommand extends Command {
    private static final String MESSAGE = "____________________________________________________________\n" +
            " Bye. Hope to see you again soon!\n" +
            "____________________________________________________________";

    public void execute() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return TerminateCommand.MESSAGE;
    }
}
