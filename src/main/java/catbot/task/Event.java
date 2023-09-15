package catbot.task;

import catbot.internal.NamedParameterMap;
import catbot.io.ErrorIndicatorIo;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.BiConsumer;

public class Event extends Task {

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
                        "", "from", "to"
                );
        if (optionalInvalidParameterState.isPresent()) {
            InvalidArgumentStruct invalidArgumentStruct = optionalInvalidParameterState.get();
            invalidArgumentStruct.parameters.moveToNewKey("", "description");
            invalidArgumentStruct.parameters.moveToNewKey("from", "start date");
            invalidArgumentStruct.parameters.moveToNewKey("to", "end date");
            invalidStateHandler.accept(invalidArgumentStruct.state, invalidArgumentStruct.parameters);
            return Optional.empty();
        }

        String description = namedParameterMap.get("");
        NamedParameterMap invalidArgs = new NamedParameterMap();
        Optional<LocalDate> start = Task.parseOptionalDateElseMap(namedParameterMap, invalidArgs, "from");
        Optional<LocalDate> end = Task.parseOptionalDateElseMap(namedParameterMap, invalidArgs, "to");
        if (start.isEmpty() || end.isEmpty()) {
            invalidArgs.moveToNewKey("", "description");
            invalidArgs.moveToNewKey("from", "start date");
            invalidArgs.moveToNewKey("to", "end date");
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
}
