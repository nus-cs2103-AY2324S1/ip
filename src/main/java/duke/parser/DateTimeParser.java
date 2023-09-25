package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.KoraException;

/**
 * Parser class for parsing DateTime variables.
 */
public class DateTimeParser {
    private DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm");
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Class constructor of DateTimeParser.
     */
    public DateTimeParser() {
    }

    /**
     * Returns String date time that is formatted for Kora reply.
     * @param dateTime String date time.
     * @return Formatted message for Kora reply.
     * @throws KoraException When there is error parsing to date time.
     */
    public String outFormatted(String dateTime) throws KoraException {
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
            return LocalDateTime.parse(dT, saveFormatter).format(outFormatter);
        } catch (DateTimeParseException e) {
            throw new KoraException("Omo! The date format should be yyyy-MM-dd HH-mm!");
        }
    }

    /**
     * Returns String date time that is formatted for saving in Storage.
     * @param dateTime String date time.
     * @return Formatted message for saving in Storage.
     * @throws KoraException When there is error parsing to date time.
     */
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
            throw new KoraException("Omo! The date format should be yyyy-MM-dd HH-mm!");
        }
    }
}
