public class NoDescriptionException extends Exception {
    public NoDescriptionException(String type) {
        super("_________________________________________________\n"
                + String.format(" OOPS!! The description of a %s cannot be empty.\n", type)
                + "_________________________________________________\n");
    }
}
