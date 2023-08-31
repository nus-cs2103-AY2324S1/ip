package cloud.main.todo;



/**
 * Represents a deadline TODO, which has an ending timestamp.
 */
public class Deadline extends Todo {
    private String timestampEnd;

    public Deadline(String _description, String end) {
        super(_description);

        this.timestampEnd = end;
    }

    @Override
    public String toString(int number) {
        return String.format(
            "%s | BY %s",
            super.toString(number),
            this.timestampEnd
        );
    }

    @Override
    public String getTypeString() {
        return "D";
    }

    public String getEnd() {
        return this.timestampEnd;
    }
}
