package duke.processors;

import duke.exception.DukeDateOutOfRange;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * A class used to transform dates of certain format to a String
 * of another format.
 */
public class TimeProcessor {

    /**
     * To convert string that is a date to another format of date.
     * @param info the string date
     * @return a date or time given by the user
     * @throws DukeDateOutOfRange will be thrown if the date is wrong
     */
    public static String StringToDate(String info)
            throws DukeDateOutOfRange {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String reg = "\\d{1,2}\\/\\d{1,2}\\/\\d{2,4}";
        LocalDate current = LocalDate.now();
        LocalDate date;

        if (isCorrect(info, dateFormatter)) {
            date = LocalDate.parse(info, dateFormatter);
        } else {
            if (info.matches(reg) || info.matches("[0-9]+")) {
                throw new DukeDateOutOfRange();
            }
            date = null;
        }

        if(date == null) {
           try {
               DayOfWeek endDay = DayOfWeek.valueOf(info.toUpperCase());
               DayOfWeek today = current.getDayOfWeek();
               int dayDiff = (7 + (endDay.getValue() - today.getValue())) % 7;
               date = current.plusDays(dayDiff);
           } catch (IllegalArgumentException e) {
               if (info.isEmpty()) {
                   date = current;
               } else {
                   if (!Character.isDigit(info.charAt(0))) {
                       System.out.println("Can only supports day of weeks "
                               + "or date in format dd/MM/yyyy "
                               + "Cannot support abbreviations "
                               +"and months + day !!!" );
                   }
               }
           }
        }

        return date == null ? info :
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy",
                        Locale.ENGLISH));
    }

    private static boolean isCorrect(String info, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(info, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}

