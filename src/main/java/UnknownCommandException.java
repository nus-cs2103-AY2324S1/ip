public class UnknownCommandException extends Exception{
    public UnknownCommandException() {
        super("_________________________________________________\n"
                + " OOPS!! I'm sorry, but I don't know what that means :-(\n"
                + "_________________________________________________\n");
    }
}
