public class EmptyListException extends BotException{
    public EmptyListException() {
        super("Oops, there are no tasks in your list!");
    }
}
