package catbot.task;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.BiConsumer;

import catbot.internal.NamedParameterMap;
import catbot.io.ErrorIndicatorIo;

/**
 * Task with start and end dates.
 */
public class Event extends Task {

    private static final String DESC_KEY = "";
    private static final String START_DATE_KEY = "from";
    private static final String END_DATE_KEY = "to";


    private LocalDate eventStart;
    private LocalDate eventEnd;

    private Event(String desc, LocalDate start, LocalDate end) {
        setDescription(desc);
        setEventStart(start);
        setEventEnd(end);
    }

    public void setEventEnd(LocalDate eventEnd) {
        this.eventEnd = eventEnd;
    }

    public void setEventStart(LocalDate eventStart) {
        this.eventStart = eventStart;
    }

    /**
     * Optionally creates a Deadline, if the given NamedParameterMap has valid arguments.
     *
     * @param map                 map of parameters and arguments to attempt to create a Deadline.
     * @param invalidStateHandler consumer to accept information about the error in case of argument invalidity.
     * @return an Optional Task if arguments are valid, otherwise an empty Optional.
     */
    public static Optional<Task> createIfValidElse(
            NamedParameterMap map,
            BiConsumer<ErrorIndicatorIo.InvalidArgumentState, NamedParameterMap> invalidStateHandler
    ) {

        Optional<InvalidArgumentStruct> optionalInvalidParameterState =
                Task.invalidStateIfTaskParametersMissingOrBlank(
                        map,
                        DESC_KEY, START_DATE_KEY, END_DATE_KEY
                );
        if (optionalInvalidParameterState.isPresent()) {
            InvalidArgumentStruct invalidArgumentStruct = optionalInvalidParameterState.get();
            invalidArgumentStruct.parameters.moveToNewKey(DESC_KEY, "description");
            invalidArgumentStruct.parameters.moveToNewKey(START_DATE_KEY, "start date");
            invalidArgumentStruct.parameters.moveToNewKey(END_DATE_KEY, "end date");
            invalidStateHandler.accept(invalidArgumentStruct.state, invalidArgumentStruct.parameters);
            return Optional.empty();
        }

        String description = map.get(DESC_KEY);
        NamedParameterMap invalidArgs = new NamedParameterMap();
        Optional<LocalDate> start = Task.parseOptionalDateElseMap(map, invalidArgs, START_DATE_KEY);
        Optional<LocalDate> end = Task.parseOptionalDateElseMap(map, invalidArgs, END_DATE_KEY);
        if (start.isEmpty() || end.isEmpty()) {
            invalidArgs.moveToNewKey(DESC_KEY, "description");
            invalidArgs.moveToNewKey(START_DATE_KEY, "start date");
            invalidArgs.moveToNewKey(END_DATE_KEY, "end date");
            invalidStateHandler.accept(ErrorIndicatorIo.InvalidArgumentState.NOT_A_DATE, invalidArgs);
            return Optional.empty();
        } else {
            return Optional.of(new Event(description, start.get(), end.get()));
        }
    }

    @Override
    public String toString() {
        return super.toString() + " [from: " + Task.formatDate(this.eventStart) + " | to: "
                + Task.formatDate(this.eventEnd) + "]";
    }

    @Override
    public void edit(NamedParameterMap map) {
        map.moveToNewKey("desc", "description");
        if (map.containsKey("description")) {
            this.setDescription(map.get("description"));
        }
        if (map.containsKey(START_DATE_KEY)) {
            Optional<LocalDate> optDate = Task.parseOptionalDateElseMap(map, null, START_DATE_KEY);
            optDate.ifPresent(this::setEventStart);
        }
        if (map.containsKey(START_DATE_KEY)) {
            Optional<LocalDate> optDate = Task.parseOptionalDateElseMap(map, null, END_DATE_KEY);
            optDate.ifPresent(this::setEventEnd);
        }

    }
}
