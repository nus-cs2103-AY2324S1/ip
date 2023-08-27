package Exception;

public class TooManyCommandException extends Exception {
    @Override
    public String toString() {
        return " There are too many commands in your input. Please note that slashes are specially reserved for commands." +
                " You may use dashes or spaces for date inputs instead";
    }
}
