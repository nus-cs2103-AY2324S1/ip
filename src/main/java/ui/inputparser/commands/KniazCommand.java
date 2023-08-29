package ui.inputparser.commands;

import commandhandling.CommandHandler;
import main.KniazSession;

@FunctionalInterface
public interface KniazCommand<T extends  CommandHandler> {

    public String execute(T handler, KniazSession session, String[] args);

}
