package simon;

/**
 * The {@code SimonAscii} class provides an ASCII representation for the name "Simon".
 * This class contains a static method to retrieve the ASCII representation.
 */
public class SimonAscii {

    /** ASCII representation of the name "Simon". */
    private static final String name =
            "     _______. __  .___  ___.   ______   .__   __. \n" +
                    "    /       ||  | |   \\/   |  /  __  \\  |  \\ |  | \n" +
                    "   |   (----`|  | |  \\  /  | |  |  |  | |   \\|  | \n" +
                    "    \\   \\    |  | |  |\\/|  | |  |  |  | |  . `  | \n" +
                    ".----)   |   |  | |  |  |  | |  `--'  | |  |\\   | \n" +
                    "|_______/    |__| |__|  |__|  \\______/  |__| \\__| \n";

    /**
     * Returns the ASCII representation of the name "Simon".
     *
     * @return A string containing the ASCII representation.
     */
    public static String toStr() {
        return name;
    }
}