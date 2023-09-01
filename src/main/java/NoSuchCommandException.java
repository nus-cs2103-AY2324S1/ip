public class NoSuchCommandException extends Exception {

    public NoSuchCommandException() {
    }

    @Override
    public String toString() {

        String result = "\n\tOOPS!!! I'm sorry , but I don't know what that means :-( ";
        return Ui.showLine() + result + " \n" + Ui.showLine();
    }
}
