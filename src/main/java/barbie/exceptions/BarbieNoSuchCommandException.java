package barbie.exceptions;

/**
 * Represents a "no such command" error.
 */
public class BarbieNoSuchCommandException extends BarbieException {
    /**
     * Represents an exception that is thrown when there is no clear command the user is asking.
     */
    public BarbieNoSuchCommandException() {
        super("There seems to be no matching command for what you're asking.. these are the following commands "
                + "you can use!\n\n"
                + "\t1. todo <task>\n"
                + "\t2. deadline <task> /<deadline>\n"
                + "\t3. party <party name> /<start> /<end\n"
                + "\t4. mark <number>\n"
                + "\t5. unmark <number>\n"
                + "\t6. list\n"
                + "\t7. find <keyword>\n"
                + "\t8. del <number>\n"
                + "\t9. bye"
        );
    }
}
