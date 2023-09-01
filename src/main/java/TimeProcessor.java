import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;


public class TimeProcessor {
    public static String StringToDate(String info) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate current = LocalDate.now();
        LocalDate date;

        if (isCorrect(info, dateFormatter)) {
            date = LocalDate.parse(info, dateFormatter);
        } else {
            date = null;
        }

        if(date == null) {
           try {
               DayOfWeek endDay = DayOfWeek.valueOf(info.toUpperCase());
               DayOfWeek today = current.getDayOfWeek();
               int dayDiff = (7 + (endDay.getValue() - today.getValue())) % 7;
               date = current.plusDays(dayDiff);
           } catch (IllegalArgumentException e) {
               if (info.isEmpty()){
                   date = current;
               } else {
                   if (!Character.isDigit(info.charAt(0))) {
                       System.out.println("Cannot specify abbreviations " +
                               "or simplified dates!");
                   }
               }
           }
        }

        return date == null ? info :
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
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
