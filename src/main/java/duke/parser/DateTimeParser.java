package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.exception.KoraException;

/**
 * Parser class for parsing DateTime variables.
 */
public class DateTimeParser {
    private DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm");
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public DateTimeParser() {
    }

    public String outFormatted(String dateTime) throws KoraException {
        String dT = "";
        String[] strArray = dateTime.trim().split(" ");
        System.out.println(Arrays.toString(strArray));
        if (strArray.length != 2) {
            for (int i = 0; i < strArray.length; i++) {
                if (!strArray[i].contains(" ") && !strArray[i].equals("")) {
                    String str = strArray[i].trim();
                    dT = dT + " " + str;
                }
            }
            dT = dT.trim();
        } else {
            dT = dateTime.trim();
        }
        try {
            return LocalDateTime.parse(dT, saveFormatter).format(outFormatter);
        } catch (DateTimeParseException e) {
            throw new KoraException("Omo! The date format should be yyyy-MM-dd HH-mm!" + dT + " " + Arrays.toString(strArray) + strArray[2].trim());
        }
    }

    public String saveFormatted(String dateTime) throws KoraException {
        String dT = "";
        String[] strArray = dateTime.trim().split(" ");
        if (strArray.length != 2) {
            for (int i = 0; i < strArray.length; i++) {
                if (!strArray[i].contains(" ") && !strArray[i].equals("")) {
                    String str = strArray[i].trim();
                    dT = dT + " " + str;
                }
            }
            dT = dT.trim();
        } else {
            dT = dateTime.trim();
        }
        try {
            return LocalDateTime.parse(dT, saveFormatter).format(saveFormatter);
        } catch (DateTimeParseException e) {
            throw new KoraException("Omo! The date format should be yyyy-MM-dd HH-mm!" + dT + " " + Arrays.toString(strArray) + strArray[2].trim());
        }
    }
}
