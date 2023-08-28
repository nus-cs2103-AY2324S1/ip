import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeDetector {
    // List of formats
    DateTimeFormatter T1_12h = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
    DateTimeFormatter T2_12h = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm");
    DateTimeFormatter T3_12h = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
    DateTimeFormatter T1_24h = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    DateTimeFormatter T2_24h = DateTimeFormatter.ofPattern("dd-MMM-yyyy HHmm");
    DateTimeFormatter T3_24h = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    DateTimeFormatter D1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter D2 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    DateTimeFormatter D3 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public DateTimeDetector(){}

    public String format(String date) {
        LocalDate d = null;
        if (date.contains(" ")) {
            // date and time given
            try {
                if (date.contains(":")) {
                    // time is 12h format
                    if (date.contains("-")) {
                        int len = date.split("-")[0].length();
                        if (len == 4) {
                            // date is yyyy-MM-dd hh:mm
                            d = LocalDate.parse(date, T1_12h);
                        } else {
                            // date is dd-MMM-yyyy hh:mm
                            d = LocalDate.parse(date, T2_12h);
                        }
                    } else {
                        // date is dd/MM/yyyy hh:mm
                        d = LocalDate.parse(date, T3_12h);
                    }
                } else {
                    // time is 24h format
                    if (date.contains("-")) {
                        int len = date.split("-")[0].length();
                        if (len == 4) {
                            // date is yyyy-MM-dd HHmm
                            d = LocalDate.parse(date, T1_24h);
                        } else {
                            // date is dd-MMM-yyyy HHmm
                            d = LocalDate.parse(date, T2_24h);
                        }
                    } else {
                        // date is dd/MM/yyyy HHmm
                        d = LocalDate.parse(date, T3_24h);
                    }
                }
                return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                return date;
            }

        } else {
            // only date given
            try {
                if (date.contains("-")) {
                    int len = date.split("-")[0].length();
                    if (len == 4) {
                        // Case 1: Date is in yyyy-MM-dd
                        d = LocalDate.parse(date, D1);
                    } else {
                        // Case 2: Date is dd-MMM-yyyy
                        d = LocalDate.parse(date, D2);
                    }
                } else {
                    // Case 3: Date is dd/MM/yyyy
                    d = LocalDate.parse(date, D3);
                }
                return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                return date;
            }
        }
    }
}
