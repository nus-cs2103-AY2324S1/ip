package corgi.parsers;

/**
 * The Parser abstract class defines a common interface for parsing strings
 * and converting them into objects of a specified type.
 *
 * @param <T> The type of object that the parser can parse strings into.
 */
public abstract class Parser<T> {
    /**
     * Parses the given string and converts it into an object of type T.
     *
     * @param s The string to be parsed.
     * @return An object of type T parsed from the input string.
     */
    public abstract T parse(String s) throws ParsingException;
}
