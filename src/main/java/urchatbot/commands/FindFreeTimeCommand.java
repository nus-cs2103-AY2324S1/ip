package urchatbot.commands;

import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Event;
import urchatbot.tasks.Task;
import urchatbot.ui.Ui;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Finds dates that have free time slot of specified time duration in a date duration
 */
public class FindFreeTimeCommand extends Command {
    private int freeTimeDuration;
    private LocalDate startingDate;
    private LocalDate endingDate;

    /**
     * Constructs the FindTimeCommand class.
     *
     * @param freeTimeDuration Time duration of free time.
     * @param startingDate Starting date of the date range.
     * @param endingDate Ending date of the date range.
     */
    public FindFreeTimeCommand(int freeTimeDuration, LocalDate startingDate, LocalDate endingDate) {
        super("findtime");
        this.freeTimeDuration = freeTimeDuration;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<LocalDate> freeTimeSlots = findFreeTimeSlots(tasks);

        if (freeTimeSlots.isEmpty()) {
            return ui.showNoFreeTimeFoundMessage();
        } else {
            return ui.showFindFreeTimeMessage(freeTimeSlots);
        }
    }

    private List<LocalDate> findFreeTimeSlots(TaskList tasks) {
        List<LocalDate> freeTimeSlots = new ArrayList<>();
        List<Event> sortedEvents = getSortedEvents(tasks);
        LocalDate currentDate = startingDate;

        while (!currentDate.isAfter(endingDate)) {
            List<Event> taskOfTheDay = new ArrayList<>();

            for (Event event : sortedEvents) {
                LocalDateTime fromDateTime = parseDateTime(event.getFrom());
                LocalDate eventDate = fromDateTime.toLocalDate();

                if (eventDate.isEqual(currentDate)) {
                    taskOfTheDay.add(event);
                }
            }

            LocalDateTime startTime = currentDate.atTime(8, 0); // Starting working hour
            LocalDateTime endTime = currentDate.atTime(18, 0); // Ending working hour

            if (taskOfTheDay.isEmpty()) {
                if (Duration.between(startTime, endTime).toMinutes() >= freeTimeDuration) {
                    freeTimeSlots.add(currentDate);
                }
                currentDate = currentDate.plusDays(1);
            } else {
                LocalDateTime currentDateTime = startTime;
                boolean isFree = true;

                for (Event event : taskOfTheDay) {
                    LocalDateTime fromDateTime = parseDateTime(event.getFrom());
                    LocalDateTime toDateTime = parseDateTime(event.getTo());

                    if (Duration.between(currentDateTime, fromDateTime).toMinutes() >= freeTimeDuration) {
                        freeTimeSlots.add(currentDate);
                        isFree = false;
                        break;
                    }
                    currentDateTime = toDateTime;
                }

                if (isFree && Duration.between(currentDateTime, endTime).toMinutes() >= freeTimeDuration) {
                    freeTimeSlots.add(currentDate);
                }

                currentDate = currentDate.plusDays(1);
            }
        }

        return freeTimeSlots;
    }


    private ArrayList<Event> getSortedEvents(TaskList tasks) {
        ArrayList<Event> sortedEvents = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task instanceof Event) {
                Event event = (Event) task;
                sortedEvents.add(event);
            }
        }
        sortedEvents.sort(Comparator.comparing(Event::getFrom));
        return sortedEvents;
    }

    private static LocalDateTime parseDateTime(String time) {
        try {
            // Attempt to parse with both date-time and date-only formats
            DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
            DateTimeFormatter formatterDateOnly = DateTimeFormatter.ofPattern("dd MM yyyy");

            try {
                LocalDateTime dateTimeWithTime = LocalDateTime.parse(time, formatterWithTime);
                return dateTimeWithTime;
            } catch (DateTimeParseException e) {
                try {
                    // Try parsing without the date part for the "HH:mm" format
                    LocalTime timeOnly = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                    // Get the current date and combine it with the parsed time
                    LocalDate currentDate = LocalDate.now();
                    // Check if the timeOnly is earlier than the current time; if yes, assume it's for the next day
                    if (timeOnly.isBefore(LocalTime.now())) {
                        currentDate = currentDate.plusDays(1);
                    }
                    return LocalDateTime.of(currentDate, timeOnly);
                } catch (DateTimeParseException ex) {
                    LocalDate dateOnly = LocalDate.parse(time, formatterDateOnly);
                    // Set the time part to midnight (00:00) for date-only input
                    return dateOnly.atStartOfDay();
                }
            }
        } catch (DateTimeParseException e) {
            return null; // Invalid format
        }
    }
}

