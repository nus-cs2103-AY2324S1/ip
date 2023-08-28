public enum Keyword {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    PRINT_DATE("print_date");

    private final String keyword;

    public String getKeyword() {
        return this.keyword;
    }

    private Keyword(String keyword) {
        this.keyword = keyword;
    }
}

