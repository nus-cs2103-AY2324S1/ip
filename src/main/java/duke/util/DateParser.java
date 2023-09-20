package duke.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to parse strings to either LocalDate or LocalDateTime Format
 */
public class DateParser {
    private static final List<DateTimeFormatter> dateFormatters = new ArrayList<>();
    private static final List<DateTimeFormatter> dateTimeFormatters = new ArrayList<>();
    private static final List<DateTimeFormatter> timeFormatters = new ArrayList<>();
    private static final LocalDate today = LocalDate.now();

    // Initialize date and date-time formatters
    static {
        dateFormatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateFormatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateFormatters.add(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        dateFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy"));

        dateTimeFormatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd h[.mm]a"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy h[.mm]a"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("MM/dd/yyyy h[.mm]a"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy h[.mm]a"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));

        timeFormatters.add(DateTimeFormatter.ofPattern("h[.mm]a"));
        timeFormatters.add(DateTimeFormatter.ofPattern("HHmm"));
        timeFormatters.add(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Parses a String to a LocalDate object.
     *
     * @param date String representation of a potential date.
     * @return A LocalDate object if the string can be parsed, null otherwise.
     */
    public static LocalDate parseDate(String date) {
        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException ignored) {
                //returns null if it fails
            }
        }
        return parseNaturalDate(date);
    }

    /**
     * Parses a String to a LocalDateTime object.
     *
     * @param dateTime String representation of a potential date and time.
     * @return A LocalDateTime object if the string can be parsed, null otherwise.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(dateTime.toUpperCase(), formatter);
            } catch (DateTimeParseException ignored) {
                //returns null if it fails
            }
        }
        return parseNaturalDateTime(dateTime);
    }

    public static LocalTime parseTime(String time) {
        for (DateTimeFormatter formatter : timeFormatters) {
            try {
                return LocalTime.parse(time, formatter);
            } catch (DateTimeParseException ignored) {
                // Ignore parsing errors and continue with the next formatter
            }
        }
        return null; // Parsing failed with all formatters
    }

    /**
     * Parses a String as a natural language date, e.g., "Mon" for the next Monday.
     *
     * @param input String representation of a natural language date.
     * @return A LocalDate object representing the parsed date or null if parsing fails.
     */
    private static LocalDate parseNaturalDate(String input) {
        Pattern todayPattern = Pattern.compile("\\b(today)\\b", Pattern.CASE_INSENSITIVE);
        Pattern tomorrowPattern = Pattern.compile("\\b(tomorrow)\\b", Pattern.CASE_INSENSITIVE);
        Pattern dayOfWeekPattern = Pattern.compile(
                "(\\bMon|Tue(s)?|Wed(s)|Wed(nes)?|Thu(rs)?|Fri|Sat(ur)?|Sun)(day)?\\b", Pattern.CASE_INSENSITIVE);
        Matcher todayMatcher = todayPattern.matcher(input);
        if (todayMatcher.find()) {
            return today;
        }

        Matcher tomorrowMatcher = tomorrowPattern.matcher(input);
        if (tomorrowMatcher.find()) {
            return today.plusDays(1);
        }

        Matcher dayOfWeekmatcher = dayOfWeekPattern.matcher(input);
        if (dayOfWeekmatcher.find()) {
            //get instance that matches pattern
            String dayOfWeekStr = dayOfWeekmatcher.group(1).toLowerCase();
            DayOfWeek dayOfWeek = getDayOfWeekFromAbbreviation(dayOfWeekStr);
            int daysUntilNext = calculateNoOfDays(dayOfWeek);
            return today.plusDays(daysUntilNext);
        }

        return null; // Return null if no natural language date match is found
    }

    /**
     * Parses a String as a natural language date and time, e.g., "Mon 4pm".
     *
     * @param input String representation of a natural language date and time.
     * @return A LocalDateTime object representing the parsed date and time or null if parsing fails.
     */
    public static LocalDateTime parseNaturalDateTime(String input) {
        // Extract day of the week and time using regex
        Pattern pattern = Pattern.compile(
                "\\b(Today|Tomorrow|Mon|Tue(s)?|Wed(s)|Wed(nes)?|Thu(rs)?|Fri|Sat(ur)?|Sun)(day)?\\s+\\b",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String naturalDateStr = matcher.group(0).toLowerCase();
            int dayOfWeekStrLength = naturalDateStr.length();
            String potentialTimeStr = input.substring(dayOfWeekStrLength).trim().toUpperCase();
            LocalDate date = parseNaturalDate(naturalDateStr.trim());
            LocalTime time = parseTime(potentialTimeStr);
            if (date != null && time != null) {
                LocalDateTime dateTime = date.atTime(time);
                return dateTime;
            }
        }
        return null;
    }

    private static DayOfWeek getDayOfWeekFromAbbreviation(String abbreviation) {
        //Map to store the mappings from abbreviations to DayOfWeek enum constants
        Map<String, DayOfWeek> abbreviationMap = new HashMap<>();
        abbreviationMap.put("mon", DayOfWeek.MONDAY);
        abbreviationMap.put("tue", DayOfWeek.TUESDAY);
        abbreviationMap.put("tues", DayOfWeek.TUESDAY);
        abbreviationMap.put("wed", DayOfWeek.WEDNESDAY);
        abbreviationMap.put("weds", DayOfWeek.WEDNESDAY);
        abbreviationMap.put("wednes", DayOfWeek.WEDNESDAY);
        abbreviationMap.put("thu", DayOfWeek.THURSDAY);
        abbreviationMap.put("thur", DayOfWeek.THURSDAY);
        abbreviationMap.put("thurs", DayOfWeek.THURSDAY);
        abbreviationMap.put("fri", DayOfWeek.FRIDAY);
        abbreviationMap.put("sat", DayOfWeek.SATURDAY);
        abbreviationMap.put("satur", DayOfWeek.SATURDAY);
        abbreviationMap.put("sun", DayOfWeek.SUNDAY);

        return abbreviationMap.get(abbreviation.toLowerCase());
    }
    private static int calculateNoOfDays(DayOfWeek dayOfWeek) {
        int daysUntilNext = dayOfWeek.getValue() - today.getDayOfWeek().getValue();
        if (daysUntilNext <= 0) { //check if weekday has passed
            daysUntilNext += 7; // Move to the next week
        }
        return daysUntilNext;
    }

}
