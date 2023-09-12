package duke.helper;

/**
 * FormatHelper class to help with formatting needs like articles for strings
 */
public class FormatHelper {
    /**
     * Returns an article for string s. Assumes s is not empty.
     *
     * @param s string to return article for
     * @return the article for s
     */
    public static String getArticle(String s) {
        switch (s.charAt(0)) {
        case 'a':
        case 'e':
        case 'i':
        case 'o':
        case 'u':
            return "an";
        default:
            return "a";
        }
    }
}
