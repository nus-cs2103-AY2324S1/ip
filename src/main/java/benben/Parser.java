
package benben;
public class Parser {


    public static void parse(BenBen bb, String next) throws BenBenException{
        boolean isParsed = false;

        if (!isParsed && next.equals("bye")) {
            bb.exit();
            isParsed = true;
        }

        if (!isParsed && next.equals("list")) {
            bb.iterList();
            isParsed = true;
        }

        if (!isParsed && next.startsWith("mark")) {
            bb.mark(next);
            isParsed = true;
        }

        if (!isParsed && next.startsWith("unmark")) {
            bb.unmark(next);
            isParsed = true;
        }

        if (!isParsed && next.startsWith("todo")) {
            bb.todo(next);
            isParsed = true;
        }

        if (!isParsed && next.startsWith("deadline")) {
            bb.deadline(next);
            isParsed = true;
        }

        if (!isParsed && next.startsWith("event")) {
            bb.event(next);
            isParsed = true;
        }

        if (!isParsed && next.startsWith("delete")) {
            bb.remove(next);
            isParsed = true;
        }

        if (!isParsed && next.startsWith("find")) {
            bb.find(next);
            isParsed = true;
        }

        if (!isParsed && next.startsWith("exit")) {
            bb.exit();
            isParsed = true;
        }

        if (!isParsed) {
            throw new BenBenException("bb does not understand your instruction:(");
        }

    }

}
