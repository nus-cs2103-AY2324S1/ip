package hachi;

import hachi.exceptions.HachiException;

/**
 * Parent abstract command class.
 */
public abstract class Command {

    private String[] arguments;

    public Command(String[] arguments) {
        this.arguments = arguments;
    }

    public String[] getArguments() {
        return arguments;
    }

    public abstract String execute() throws HachiException;


    protected abstract void checkArgumentLength(int argumentLength) throws HachiException;
}
