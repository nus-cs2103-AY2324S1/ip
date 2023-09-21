package duke.processors;

import duke.exception.DukeDateOutOfRange;
import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

/**
 * A class used to transform dates of certain format to a String
 * of another format.
 */
public class TimeProcessor {

    /**
     * To convert string that is a date to another format of date.
     *
     * @param info the string date.
     * @return a date or time given by the user.
     * @throws DukeDateOutOfRange will be thrown if the date is wrong.
     */
    public static String StringToDate(String info)
            throws DukeException {
        info = info.trim();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String reg = "\\d{1,2}/\\d{1,2}/\\d{4}";
        LocalDate current = LocalDate.now();
        LocalDate date;

        if (isCorrect(info, dateFormatter)) {
            date = LocalDate.parse(info, dateFormatter);
        } else {
            if (info.matches(reg) || info.matches("[0-9]{8,}")) {
                throw new DukeDateOutOfRange();
            }
            if (info.matches("([01]?[0-9]|2[0-3])[0-5][0-9]")) {
                return twentyfourToTwelve(info);
            }
            date = null;
        }

        if (date == null) {
            try {
                info = abbreviationExpansion(info);
                DayOfWeek endDay = DayOfWeek.valueOf(info.toUpperCase());
                DayOfWeek today = current.getDayOfWeek();
                int dayDiff = (7 + (endDay.getValue() - today.getValue())) % 7;
                date = current.plusDays(dayDiff);
            } catch (IllegalArgumentException e) {
                if (info.isEmpty()) {
                    date = current;
                } else if (!Character.isDigit(info.charAt(0))) {
                    throw new DukeException("Can only supports day of weeks "
                            + "or date in format dd/MM/yyyy "
                            + "Cannot support abbreviations "
                            + "and MM/dd !!!");
                }
            }
        }

        return date == null ? info :
                date.format(DateTimeFormatter.ofPattern(
                        "MMM d yyyy", Locale.ENGLISH));
    }

    private static String twentyfourToTwelve(String info) throws DukeException {
        String temp = info.substring(0, info.length() / 2) + ":"
                + info.substring(info.length()/2);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);
        Date time;
        try {
            time = format.parse(temp);
        } catch (ParseException e) {
            throw new DukeException("Time given is wrong");
        }
        return format1.format(time);
    }

    private static String abbreviationExpansion(String abbr) {
        String regex = "^(Mon|mon|Tue|tue|Wed|wed|Thu"
                + "|thu|Fri|fri|Sat|sat|Sun|sun)$";
        if (abbr.matches(regex)) {
            switch (abbr.toLowerCase()) {
            case "tue":
                abbr = abbr + "sday";
                break;
            case "wed":
                abbr = abbr + "nesday";
                break;
            case "thu":
                abbr = abbr + "rsday";
                break;
            case "sat":
                abbr = abbr + "urday";
                break;
            default:
                abbr = abbr + "day";
            }
        }
        return abbr;
    }

    private static boolean isCorrect(String info, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(info, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static void main(String[] args) throws DukeException {
        System.out.println(StringToDate("tue"));
    }

}

