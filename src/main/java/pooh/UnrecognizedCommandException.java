package pooh;

public class UnrecognizedCommandException extends PoohException {
    @Override
    public String toString() {
        return "      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
