package ekud.ui;

import ekud.command.Command;

@FunctionalInterface
public interface CommandHandler {
    public boolean handle(Command command);
}
