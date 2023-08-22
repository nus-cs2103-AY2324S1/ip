public class EmptyDescriptionException extends Exception {
    private final String type;

    EmptyDescriptionException(String type) {
        super();
        this.type = type;
    }

    public String toString(){
        return ("☹ OOPS!!! The description of a " + type + " cannot be empty.\n");
    }
}
