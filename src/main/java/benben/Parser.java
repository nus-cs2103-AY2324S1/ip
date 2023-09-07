
package benben;

/**
 * The type Parser parses the comment to the action that need to be taken
 */
public class Parser {

    /**
     * Parses the command and tells benben what to do next
     *
     * @param bb   the BenBen Object
     * @param next the command
     * @throws BenBenException if the command cannot be recognised
     */
    public static String parse(BenBen bb, String next) throws BenBenException {

        if (next.equals("bye")) {
            return bb.exit();
        }

        if (next.equals("list")) {
            return bb.iterList();
        }

        if (next.startsWith("mark")) {
            return bb.mark(next);
        }

        if (next.startsWith("unmark")) {
            return bb.unmark(next);
        }

        if (next.startsWith("todo")) {
            return bb.todo(next);
        }

        if (next.startsWith("deadline")) {
            return bb.deadline(next);
        }

        if (next.startsWith("event")) {
            return bb.event(next);
        }

        if (next.startsWith("delete")) {
            return bb.remove(next);
        }

        if (next.startsWith("find")) {
            return bb.find(next);
        }

        if (next.startsWith("exit")) {
            return bb.exit();
        }

            throw new BenBenException("BenBen does not understand your instruction:(");
    }

}
