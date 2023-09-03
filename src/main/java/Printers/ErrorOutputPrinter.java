package Printers;

public class ErrorOutputPrinter {
    public static void printErrorOutput(String output) {
        System.err.println("_______________________________ ERROR!: _______________________________\n" +
                output +
                "\n_______________________________________________________________________\n");
    }
}
