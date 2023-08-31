package chatbot.exceptions;

public class FindMissingFieldException extends MissingFieldException{
    @Override
    public String toString() {
        return "\tOOPS, to find a task, use find <Description>\n" +
                "\tThe description cannot be empty.";
    }
}