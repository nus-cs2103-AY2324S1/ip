package duke.exceptions;

public class InvalidFindException extends DukeException{
    public InvalidFindException(){
        super("Invalid find command. Enter find (keyword / key phrases)");
    }
}
