package commandhandling;

import main.KniazSession;

public class InvalidHandler implements  CommandHandler{
    @Override
    public String handle(KniazSession session, String[] args) {
        return null;
    }
}
