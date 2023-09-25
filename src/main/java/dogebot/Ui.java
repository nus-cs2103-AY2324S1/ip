package dogebot;

/**
 * The Ui class handles interactions with the user.
 *
 * @author Kenvyn Kwek
 */
public class Ui {
    private static final String LOGO = "    ___\n"
            + " __/_  `.  .-\"\"\"-." + "           |                      |             |   \n"
            + " \\_,` | \\-'  /   )`-')" + "    _` |   _ \\    _` |   _ \\  __ \\    _ \\   __| \n"
            + "  \"\") `\"`    \\  ((`\"`" + "    (   |  (   |  (   |   __/  |   |  (   |  |   \n"
            + " ___Y  ,    .'7 /|" + "      \\__,_| \\___/  \\__, | \\___| _.__/  \\___/  \\__| \n"
            + "(_,___/...-` (_/_/" + "                    |___/\n";

    /**
     * DogeBot's intro logo and message.
     *
     * @return intro logo and message.
     */
    public String intro() {
        return LOGO + "Hi ! I'm DogeBot \nHow may I help you today ?";
    }
}
