package duke;

enum TaskStatus {
    DONE("[X]"), NOT_DONE("[ ]");
    private final String symbol;
    TaskStatus(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
