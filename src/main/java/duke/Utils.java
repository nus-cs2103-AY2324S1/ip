package duke;
/**
 * Contains utility functions to be used in several contexts.
 */

class Utils {
    /**
     * Takes an array of strings, a start and end index. Returns
     * a string with all tokens between start and end (not inclusive)
     * index separated by a space.
     *
     * @param strArr Array of strings
     * @param start  start index
     * @param end end index
     * @returns String with all tokens between start and end (not inclusive) index separated by space
     */
    public static String splitStringBySpaces(String[] strArr, int start, int end) {
        String res = "";
        for (int i = start; i < end; i++) {
            res += strArr[i];
            if (i != end - 1) {
                res += " ";
            }
        }
        return res;
    }
}