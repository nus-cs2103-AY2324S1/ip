package duchess;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class to manage Utility functions.
 */
public class Utility {
    /**
     * Checks whether the given string matches the given regex pattern.
     *
     * @param s               - the string to check if it matches the pattern.
     * @param patternString   - the pattern to match against.
     * @param caseInsensitive - whether the match should be performed ignoring the case.
     * @return                  whether the string matches the pattern.
     */
    public static boolean matchesRegex(String s, String patternString, boolean caseInsensitive) {
        return Utility.parseRegex(s, patternString, caseInsensitive).find(0);
    }

    /**
     * Checks whether the given string matches the given regex pattern. Defaults to case-sensitive matching.
     *
     * @param s               - the string to check if it matches the pattern.
     * @param patternString   - the pattern to match against.
     * @return                  whether the string matches the pattern.
     */
    public static boolean matchesRegex(String s, String patternString) {
        return Utility.parseRegex(s, patternString).find(0);
    }

    /**
     * Returns the regex groups that is parsed from the regex pattern.
     *
     * @param s               - the string to check if it matches the pattern.
     * @param patternString   - the pattern to match against.
     * @param caseInsensitive - whether the match should be performed ignoring the case.
     * @return                  the matcher containing the parsed regex groups.
     */
    public static Matcher parseRegex(String s, String patternString, boolean caseInsensitive) {
        Pattern pattern;

        if (caseInsensitive) {
            pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        } else {
            pattern = Pattern.compile(patternString);
        }

        Matcher matcher = pattern.matcher(s);
        matcher.find(0);
        return matcher;
    }

    /**
     * Returns the regex groups that is parsed from the regex pattern. By default, is case sensitive.
     *
     * @param s               - the string to check if it matches the pattern.
     * @param patternString   - the pattern to match against.
     * @return                  the matcher containing the parsed regex groups.
     */
    public static Matcher parseRegex(String s, String patternString) {
        return Utility.parseRegex(s, patternString, false);
    }

    /**
     * Converts a date string into a LocalDate.
     * Currently supports yyyy-mm-dd format.
     *
     * @param dateString        - the String to be converted into a LocalDate.
     * @return                    the LocalDate.
     * @throws DuchessException   if the dateString is not in any recognized date format.
     */
    public static LocalDate parseDateString(String dateString) throws DuchessException {
        if (Utility.matchesRegex(dateString, "[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            // yyyy-mm-dd format.
            try {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateString, dateTimeFormatter);

                return localDate;
            } catch (Exception e) {
                // Something went wrong... perhaps date is out of bounds.
                throw new DuchessException("(´；ω；`) Sorry, I don't recognize that date... ;-;");
            }
        }

        throw new DuchessException("(´；ω；`) Sorry, I don't recognize that date... ;-;");
    }
}
