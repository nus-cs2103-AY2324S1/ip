package task;

public enum TaskTypes {
    TODO,
    DEADLINE,
    EVENT;

    public char getCharRep() {
        if (this == TODO) {
            return 'T';
        } else if (this == DEADLINE) {
            return 'D';
        } else if (this == EVENT) {
            return 'E';
        }
        return ' ';
    }
}
