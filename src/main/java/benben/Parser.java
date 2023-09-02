
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
    public static void parse(BenBen bb, String next) throws BenBenException{
        boolean bool = false;

        if (!bool && next.equals("bye")) {
            bb.exit();
            bool = true;
        }

        if (!bool && next.equals("list")) {
            bb.iterList();
            bool = true;
        }

        if (!bool && next.startsWith("mark")) {
            bb.mark(next);
            bool = true;
        }

        if (!bool && next.startsWith("unmark")) {
            bb.unmark(next);
            bool = true;
        }

        if (!bool && next.startsWith("todo")) {
            bb.todo(next);
            bool = true;
        }

        if (!bool && next.startsWith("deadline")) {
            bb.deadline(next);
            bool = true;
        }

        if (!bool && next.startsWith("event")) {
            bb.event(next);
            bool = true;
        }

        if (!bool && next.startsWith("delete")) {
            bb.remove(next);
            bool = true;
        }

        if (!bool) {
            throw new BenBenException("bb does not understand your instruction:(");
        }

    }

}
