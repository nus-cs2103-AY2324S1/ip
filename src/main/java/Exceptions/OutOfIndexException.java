package Exceptions;

public class OutOfIndexError extends DukeException{
    public OutOfIndexError(){
        super("You have provided a number out of index of the stored tasks");
    }
}
