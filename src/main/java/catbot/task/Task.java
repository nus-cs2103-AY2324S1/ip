package catbot.task;

import catbot.internal.NamedParameterMap;
import catbot.io.ErrorIndicatorIo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.function.BiConsumer;

public abstract class Task implements Serializable {

    //region Fields

    private String description;
    private boolean isDone = false;

    //endregion

    //region Getter/setter

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }
    public void setUndone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "[" + (isDone()? "X":" ") + "] " + getDescription();
    }

    //endregion

    //region Handling NamedParameterMaps

    private static Optional<NamedParameterMap> mapIfDescriptionEmpty(NamedParameterMap map) {
        String desc = map.get("");
        if (desc == null || desc.isEmpty() || desc.isBlank()) {
            NamedParameterMap newMap = new NamedParameterMap();
            newMap.addNamedParameter("description", "");
            return Optional.of(newMap);
        } else {
            return Optional.empty();
        }
    }
    private static Optional<NamedParameterMap> mapIfArgumentsMissing(
            NamedParameterMap map,
            String... arguments
    ) {
        NamedParameterMap newMap = new NamedParameterMap();
        boolean missing = false;
        for (String arg : arguments) {
            if (!map.containsKey(arg)) {
                missing = true;
                newMap.addNamedParameter(arg, "");
            }
        }
        if (missing) {
            return Optional.of(newMap);
        } else {
            return Optional.empty();
        }
    }

    private static class InvalidArgumentStruct {
        private final ErrorIndicatorIo.InvalidArgumentState state;
        private final NamedParameterMap parameters;

        private InvalidArgumentStruct(ErrorIndicatorIo.InvalidArgumentState state, NamedParameterMap map) {
            this.state = state;
            this.parameters = map;
        }
    }

    private static Optional<InvalidArgumentStruct> invalidStateIfTaskParametersMissingOrBlank(NamedParameterMap namedParameterMap, String... arguments) {

        // parameters cannot be missing
        Optional<NamedParameterMap> optionalNamedParameterMap = Task.mapIfArgumentsMissing(namedParameterMap, arguments);
        if (optionalNamedParameterMap.isPresent()) {
            return Optional.of(new InvalidArgumentStruct(ErrorIndicatorIo.InvalidArgumentState.PARAMETER_MISSING, optionalNamedParameterMap.get()));
        }

        // description cannot be empty
        optionalNamedParameterMap = Task.mapIfDescriptionEmpty(namedParameterMap);
        //noinspection OptionalIsPresent for readability, to distinguish default return value
        if (optionalNamedParameterMap.isPresent()) {
            return Optional.of(new InvalidArgumentStruct(ErrorIndicatorIo.InvalidArgumentState.PARAMETER_EMPTY, optionalNamedParameterMap.get()));
        }

        // if all ok
        return Optional.empty();
    }

    private static Optional<LocalDate> parseOptionalDateElseMap(NamedParameterMap map, NamedParameterMap elseMap, String arg) {
        String val = map.get(arg);
        try {
            return Optional.of(LocalDate.parse(val));
        } catch (DateTimeParseException ignored) {
            elseMap.addNamedParameter(arg, val);
            return Optional.empty();
        }
    }

    //endregion

    //region Subclasses

    public static class Todo extends Task {
        private Todo(String desc) {
             setDescription(desc);
        }

        public static Optional<Task> createIfValidElse(
                NamedParameterMap namedParameterMap,
                BiConsumer<ErrorIndicatorIo.InvalidArgumentState, NamedParameterMap> invalidStateHandler
        ) {

            Optional<InvalidArgumentStruct> optionalInvalidParameterState =
                Task.invalidStateIfTaskParametersMissingOrBlank(
                        namedParameterMap,
                        ""
                );
            if (optionalInvalidParameterState.isPresent()) {
                InvalidArgumentStruct invalidArgumentStruct = optionalInvalidParameterState.get();
                invalidArgumentStruct.parameters.moveToNewKey("", "description");
                invalidStateHandler.accept(invalidArgumentStruct.state, invalidArgumentStruct.parameters);
                return Optional.empty();
            }

            return Optional.of(new Todo(
                    namedParameterMap.get("")
            ));
        }
    }

    public static class Deadline extends Task {

        private LocalDate dueDate;

        public Deadline(String desc, LocalDate dateTime) {
            setDescription(desc);
            setDueDate(dateTime);
        }

        public void setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
        }

        public static Optional<Task> createIfValidElse(
                NamedParameterMap map,
                BiConsumer<ErrorIndicatorIo.InvalidArgumentState, NamedParameterMap> invalidStateHandler
        ) {

            Optional<InvalidArgumentStruct> optionalInvalidParameterState =
                    Task.invalidStateIfTaskParametersMissingOrBlank(
                            map,
                            "", "by"
                    );
            if (optionalInvalidParameterState.isPresent()) {
                InvalidArgumentStruct invalidArgumentStruct = optionalInvalidParameterState.get();
                invalidArgumentStruct.parameters.moveToNewKey("", "description");
                invalidArgumentStruct.parameters.moveToNewKey("by", "due date");
                invalidStateHandler.accept(invalidArgumentStruct.state, invalidArgumentStruct.parameters);
                return Optional.empty();
            }

            String description = map.get("");
            NamedParameterMap invalidArgs = new NamedParameterMap();
            Optional<LocalDate> optionalDueDate = Task.parseOptionalDateElseMap(map, invalidArgs, "by");
            if (optionalDueDate.isPresent()) {
                return Optional.of(new Deadline(description, optionalDueDate.get()));
            } else {
                invalidArgs.moveToNewKey("by", "due date");
                invalidStateHandler.accept(ErrorIndicatorIo.InvalidArgumentState.NOT_A_DATE, invalidArgs);
                return Optional.empty();
            }
        }

        @Override
        public String toString() {
            return super.toString() + " [due: " + Task.formatDate(this.dueDate) + "]";
        }
    }

    public static class Event extends Task {

        private LocalDate eventStart;
        private LocalDate eventEnd;

        public Event(String desc, LocalDate start, LocalDate end) {
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
            return super.toString() + " [from: " + Task.formatDate(this.eventStart) + " | to: " +
                    Task.formatDate(this.eventEnd) + "]";
        }
    }

    //endregion

    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(
                date.getYear() == Year.now().getValue()?
                "MMM d": "MMM d yyyy")
        );
    }

}
