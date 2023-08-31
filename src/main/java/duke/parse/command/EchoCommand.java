package duke.parse.command;

import duke.Duke;

public class EchoCommand implements Command {
    private String command;

    public EchoCommand(String command) {
        this.command = command;
    }

    @Override
    public boolean execute(Duke bot) {
        bot.echo(this.command);
        return true;
    }
}
