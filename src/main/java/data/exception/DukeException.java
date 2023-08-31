package data.exception;

public class DukeException extends Exception {
    private String msg;

    public DukeException(String msg) {
        this.msg = String.format("    %s", msg);
    }

    public DukeException(String[] msg) {
        String temp = "";
        for (String stub : msg) {
            temp += String.format("    %s\n", stub);
        }
        this.msg = temp.strip();
    }

    @Override
    public String toString() {
        return msg;
    }
}