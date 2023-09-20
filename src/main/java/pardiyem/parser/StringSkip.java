package pardiyem.parser;

public enum StringSkip {
    ONE (1),
    TWO (2),
    THREE (3),
    FOUR (4),
    FIVE (5),
    SIX (6),
    SEVEN (7),
    EIGHT (8),
    NINE (9);
    private final int skipBy;
    StringSkip(int skipBy) {
        this.skipBy = skipBy;
    }

    int getSkipBy() {
        return skipBy;
    }

}
