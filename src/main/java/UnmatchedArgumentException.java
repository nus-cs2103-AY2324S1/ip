public class UnmatchedArgumentException extends Exception {

    private int got;
    private int actual;

    public UnmatchedArgumentException(int got, int actual) {

        this.got = got;
        this.actual = actual;
    }

    @Override
    public String toString() {

        String result = "\n\tOOPS! The argument passed does not match the requirement.";
        result += "\n\tExpected " + this.actual + " , got " + this.got + "\n";
        return Ui.showLine() + result + " \n" + Ui.showLine();
    }
}
