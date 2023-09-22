package ally.exceptions;

public class EmptyArgumentException extends AllyException {
    public EmptyArgumentException() {
        super("YOOO, argumnt cannot be empty. Please type 'help' for the list of commands!😎");
    }
}
