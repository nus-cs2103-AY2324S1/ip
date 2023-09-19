package catbot.task;

import catbot.internal.NamedParameterMap;
import catbot.io.ErrorIndicatorIo;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.BiConsumer;

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

    public static Optional<Task> createIfValidElse(
            NamedParameterMap namedParameterMap,
            BiConsumer<ErrorIndicatorIo.InvalidArgumentState, NamedParameterMap> invalidStateHandler
    ) {

        Optional<InvalidArgumentStruct> optionalInvalidParameterState =
                Task.invalidStateIfTaskParametersMissingOrBlank(
                        namedParameterMap,
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

        String description = namedParameterMap.get(DESC_KEY);
        NamedParameterMap invalidArgs = new NamedParameterMap();
        Optional<LocalDate> start = Task.parseOptionalDateElseMap(namedParameterMap, invalidArgs, START_DATE_KEY);
        Optional<LocalDate> end = Task.parseOptionalDateElseMap(namedParameterMap, invalidArgs, END_DATE_KEY);
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
