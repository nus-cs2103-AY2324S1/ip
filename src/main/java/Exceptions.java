class IncompleteDescriptionException extends Exception {
    @Override
    public String toString() {
        return Constants.divider + "\n"
                + "☹ OOPS!!! The description of the task is incomplete or incorrect."
                + "\n" + Constants.divider + "\n";
    }
}

class UnknownCommandException extends Exception {
    @Override
    public String toString() {
        return Constants.divider + "\n"
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                + "\n" + Constants.divider + "\n";
    }
}