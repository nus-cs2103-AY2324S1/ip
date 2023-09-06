package chatbot.exceptions;

public class FindMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "OOPS, to find a task, use find <Description>\n"
                + "The description cannot be empty.";
    }
}
