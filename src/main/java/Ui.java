/**
 * The UI Class deals with the App's User Interface, inclusive of but not limited to
 * System.out formatting, line formatting, and other output formatting.
 */
public class Ui {

    /**
     * Prints any given text with the appropriate tab-spacing
     */
    public static void tabPrinter(String s) {
        System.out.println("      " + s);
    }

    public static void linePrinter() {
        Ui.tabPrinter
                ("___________________________________________________________");
        System.out.println(" ");
    }

}
