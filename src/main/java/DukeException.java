public class DukeException extends Exception{
    String msg;

    DukeException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
