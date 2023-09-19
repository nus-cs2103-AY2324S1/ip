package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidTimeException;

public class FixedTask extends Task{
    class Duration {
        int days;
        int hours;
        int minutes;
        private Duration(int days, int hours, int minutes) throws DukeException{
            if (hours >= 24) {
                throw new InvalidTimeException("Invalid input of hours (hours needs to be below 24)");
            }
            if (minutes >= 60) {
                throw new InvalidTimeException("Invalid input of minutes (minutes needs to be below 60)");
            }
            if (days == 0 && hours == 0 && minutes == 0) {
                throw new InvalidTimeException("Invalid input of time");
            }
            this.days = days;
            this.hours = hours;
            this.minutes = minutes;
        }

        private String getInput() {
            String duration = "";
            if (days > 0) {
                duration += days + "d";
            }
            if (hours > 0) {
                if (duration.length() > 0) {
                    duration += " ";
                }
                duration += hours + "h";
            }
            if (minutes > 0) {
                if (duration.length() > 0) {
                    duration += " ";
                }
                duration += minutes + "m";
            }
            return duration;
        }
        @Override
        public String toString() {
            String duration = "";
            if (days > 0) {
                duration += days + " days";
            }
            if (hours > 0) {
                if (duration.length() > 0) {
                    duration += " ";
                }
                duration += hours + " hours";
            }
            if (minutes > 0) {
                if (duration.length() > 0) {
                    duration += " ";
                }
                duration += minutes + " minutes";
            }
            return duration;
        }
    }

    protected Duration duration;
    /**
     * Constructs a deadline with a specified description and date.
     *
     * @param description A string describing the deadline task.
     * @throws DukeException If the deadline date is not a valid date.
     */
    public FixedTask(String description) throws DukeException {
        super(description.substring(0, description.indexOf("/need") - 1));
        int needIndex = description.indexOf("/need");
        String tempDuration = description.substring(needIndex + 6);
        int checkDaysIndex = tempDuration.indexOf("d");
        int checkHoursIndex = tempDuration.indexOf("h");
        int checkMinutesIndex = tempDuration.indexOf("m");
        if ((checkDaysIndex > checkHoursIndex && checkHoursIndex != -1) ||
                (checkHoursIndex > checkMinutesIndex && checkMinutesIndex != -1) ||
                (checkDaysIndex > checkMinutesIndex && checkMinutesIndex != -1)) {
            throw new InvalidTimeException("Invalid input of Date");
        }
        try {
            Integer tempDays;
            Integer tempHours;
            Integer tempMinutes;
            if (checkDaysIndex == -1) {
                tempDays = 0;
            } else {
                tempDays = Integer.parseInt(tempDuration.substring(0, checkDaysIndex));
            }
            if (checkHoursIndex == -1) {
                tempHours = 0;
            } else if (checkDaysIndex == -1) {
                tempHours = Integer.parseInt(tempDuration.substring(0, checkHoursIndex));
            } else {
                tempHours = Integer.parseInt(tempDuration.substring(checkDaysIndex + 2, checkHoursIndex));
            }
            if (checkMinutesIndex == -1) {
                tempMinutes = 0;
            } else if (checkDaysIndex == -1 && checkHoursIndex == -1) {
                tempMinutes = Integer.parseInt(tempDuration.substring(0, checkMinutesIndex));
            } else {
                tempMinutes = Integer.parseInt(tempDuration.substring(Integer.max(checkHoursIndex + 2, checkDaysIndex + 2),
                        checkMinutesIndex));
            }
            this.duration = new Duration(tempDays, tempHours, tempMinutes);
        } catch (NumberFormatException e) {
            throw new InvalidTimeException("Invalid input of Date");
        }
    }

    /**
     * Returns a String representing the Deadline that will be stored.
     *
     * @return the string representing the Deadline that will be stored.
     */
    @Override
    public String getInput() {
        return "F | " + this.getStatusIcon() + " | " + this.description + " | " + this.duration.getInput();
    }

    private String getDuration() {
        return this.duration.toString();
    }

    /**
     * Returns the String representation of a Deadline.
     *
     * @return the String representation of a Deadline.
     */
    @Override
    public String toString() {
        return "[F] [" + this.getStatusIcon() + "] " + this.description + " (needs "
                + getDuration() + ")";
    }
}
