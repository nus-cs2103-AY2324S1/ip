public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private String input;

    private TaskType(String input) {
        this.input = input;
    }
}
