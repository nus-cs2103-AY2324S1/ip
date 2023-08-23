package Exceptions;

public class NoTasksException extends Exception{
    public NoTasksException(String message) {
        super(":( OPPS!!! There are no tasks to show in the list!");
    }

}
