package main.logic.command;

import main.logic.handler.CommandHandler;
import main.KniazSession;
import ui.inputparser.InstructionType;

import java.util.List;
import java.util.Map;


public class KniazCommand{

    private CommandHandler commandHandler;

    private List<String> unnamedArgs;

    private Map<? extends  String, ? extends String> namedArgs;

    private InstructionType instruct;

    public KniazCommand(InstructionType instruct,
                        CommandHandler commandHandler,
                        List<String> unnamedArgs,
                        Map<? extends String, ? extends String> namedArgs){
        this.instruct = instruct;
        this.commandHandler = commandHandler;
        this.unnamedArgs = unnamedArgs;
        this.namedArgs = namedArgs;
    }

    public String execute(KniazSession session){
        return commandHandler.handle(session, this.unnamedArgs,this.namedArgs);
    }

    public InstructionType getInstruct() {
        return this.instruct;
    }


}
