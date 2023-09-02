package aj;

/**
 * IndexOutOfRangeException class for exceptions related to index being out of range.
 */
public class IndexOutOfRangeException extends AjException {
    IndexOutOfRangeException(int val) {
        super("OOPS!!! Please choose an index within the number of items in the list " + "(" + val + ")");
    }
}
