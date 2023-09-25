package duke;

/**
 * The <code>Parser</code> class takes in strings from the commands and parses them
 * for the <code>Handler</code> object to use.
 */

public class Parser {
    /**
     * Parses a <code>String</code>, splitting it by the first space encountered.
     *
     * @param str The String to be split.
     * @return Two strings
     */
    public static String[] splitSpace (String str) {
        return str.split(" ", 2);
    }

    /**
     * Parses a <code>String</code>, splitting it by the /from and /to keywords.
     *
     * @param str The String to be split.
     * @return Three strings
     */
    public static String[] splitEvent (String str) {
        String[] res = new String[3];
        String noCommand = Parser.splitSpace(str)[1];
        String[] temp = noCommand.split("/from ", 2);
        res[0] = temp[0];
        if (temp.length < 2) {
            res[1] = "";
            res[2] = "";
            return res;
        }
        String[] temp2 = temp[1].split("/to ", 2);
        res[1] = temp2[0];
        if (temp2.length < 2) {
            res[2] = "";
            return res;
        }
        res[2] = temp2[1];
        assert res.length == 3 : "There should be 3 elements after splitting an Event command.";

        //removes leading and trailing spaces
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i].trim();
        }

        return res;
    }

    /**
     * Parses a <code>String</code>, splitting it by the /by keyword.
     *
     * @param str The String to be split.
     * @return Two strings.
     */
    public static String[] splitDeadline (String str) {
        String noCommand = Parser.splitSpace(str)[1];
        return noCommand.split("/by ", 2);
    }
}
