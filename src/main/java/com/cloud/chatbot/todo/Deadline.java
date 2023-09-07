package com.cloud.chatbot.todo;



/**
 * Represents a deadline TODO, which has an ending timestamp.
 */
public class Deadline extends Todo {
    private String timestampEnd;

    /**
     * @param description The TODO description.
     * @param end The ending timestamp for the TODO.
     */
    public Deadline(String _description, String end) {
        super(_description);

        this.timestampEnd = end;
    }

    @Override
    public String toString(int number) {
        return String.format(
            "%s | BY %s",
            this.getBasicString(number),
            this.getEnd()
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
