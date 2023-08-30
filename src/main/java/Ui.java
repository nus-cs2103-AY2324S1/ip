public class Ui {
    private static final String LINEBREAK = "    ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";

    /**
     * Displays the given message between horizontal lines
     *
     * @param msg String to output
     */
    public void displayMessage(String msg) {
        System.out.println(LINEBREAK);
        System.out.println(indent(msg));
        System.out.println(LINEBREAK);
    }

    private static String indent(String msg) {
        return msg.replaceAll("(?m)^", "\t");
    }
}
