package ui.inputparser.commands;

import commandhandling.CommandHandler;
import main.KniazSession;


public class KniazCommand<T extends  CommandHandler> {

    T command;
    String[] args;
    public KniazCommand(T command, String[] args){
        this.command = command;
        this.args = args;
    }

    public String execute(KniazSession session, String[] args){
        return command.handle(session, args);
    }

}
