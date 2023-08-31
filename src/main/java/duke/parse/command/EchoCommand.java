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

    @Override
    public boolean equals(Object another) {
        if (another instanceof EchoCommand) {
            EchoCommand anotherEcho = (EchoCommand) another;
            return this.command.equals(anotherEcho.command);
        }
        return false;
    }
}
