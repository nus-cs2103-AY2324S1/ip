package Utilities.Exceptions;

import Utilities.Ui;

public class UnknownCommandException extends Exception{
    @Override
    public String toString() {
        return Ui.divider + "\n"
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                + "\n" + Ui.divider + "\n";
    }
}
