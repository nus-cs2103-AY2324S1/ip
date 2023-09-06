package catbot.task;

import catbot.internal.NamedParameterMap;
import catbot.io.ErrorIndicatorIo.InvalidState;

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

    private static Optional<NamedParameterMap> cloneIfPresent(NamedParameterMap map, String... arguments) {
        NamedParameterMap newMap = new NamedParameterMap();
        for (String arg : arguments) {
            if (!map.containsKey(arg)) {
                return Optional.empty();
            } else {
                newMap.addNamedParameter(arg, map.get(arg));
            }
        }
        return Optional.of(newMap);
    }

    private static class InvalidParameterState {
        private final InvalidState state;
        private final NamedParameterMap parameters;

        private InvalidParameterState(InvalidState state, NamedParameterMap map) {
            this.state = state;
            this.parameters = map;
        }
    }

    private static Optional<InvalidParameterState> stateIfTaskParametersInvalid(NamedParameterMap namedParameterMap, String... arguments) {

        // parameters cannot be missing
        Optional<NamedParameterMap> optionalNamedParameterMap = Task.mapIfArgumentsMissing(namedParameterMap, arguments);
        if (optionalNamedParameterMap.isPresent()) {
            return Optional.of(new InvalidParameterState(InvalidState.PARAMETER_MISSING, optionalNamedParameterMap.get()));
        }

        // description cannot be empty
        optionalNamedParameterMap = Task.mapIfDescriptionEmpty(namedParameterMap);
        //noinspection OptionalIsPresent for readability
        if (optionalNamedParameterMap.isPresent()) {
            return Optional.of(new InvalidParameterState(InvalidState.PARAMETER_EMPTY, optionalNamedParameterMap.get()));
        }

        // if all ok
        return Optional.empty();
    }

    private static Optional<LocalDate> optionalDateElseMap(NamedParameterMap cmdargs, NamedParameterMap elseMap, String arg) {
        String val = cmdargs.get(arg);
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
                BiConsumer<InvalidState, NamedParameterMap> invalidStateHandler
        ) {

            Optional<InvalidParameterState> optionalInvalidParameterState =
                Task.stateIfTaskParametersInvalid(
                        namedParameterMap,
                        ""
                );

            if (optionalInvalidParameterState.isEmpty()) {
                return Optional.of(new Todo(
                        namedParameterMap.get("")
                ));
            } else {
                InvalidParameterState invalidParameterState = optionalInvalidParameterState.get();
                invalidParameterState.parameters.moveToNewKey("", "description");
                invalidStateHandler.accept(invalidParameterState.state, invalidParameterState.parameters);
                return Optional.empty();
            }
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
                NamedParameterMap namedParameterMap,
                BiConsumer<InvalidState, NamedParameterMap> invalidStateHandler
        ) {

            Optional<InvalidParameterState> optionalInvalidParameterState =
                    Task.stateIfTaskParametersInvalid(
                            namedParameterMap,
                            "", "by"
                    );

            if (optionalInvalidParameterState.isEmpty()) {
                String description = namedParameterMap.get("");
                LocalDate date;
                try {
                    date = LocalDate.parse(namedParameterMap.get("by"));
                    return Optional.of(new Deadline(description, date));
                } catch (DateTimeParseException ignored) {
                    NamedParameterMap invalidArgs = Task.cloneIfPresent(namedParameterMap, "by").orElseGet(() -> namedParameterMap);
                    invalidArgs.moveToNewKey("by", "due date");
                    invalidStateHandler.accept(InvalidState.NOT_A_DATE, invalidArgs);
                    return Optional.empty();
                }
            } else {
                InvalidParameterState invalidParameterState = optionalInvalidParameterState.get();
                invalidParameterState.parameters.moveToNewKey("", "description");
                invalidParameterState.parameters.moveToNewKey("by", "due date");
                invalidStateHandler.accept(invalidParameterState.state, invalidParameterState.parameters);
                return Optional.empty();
            }
        }

        @Override
        public String toString() {
            return super.toString() + " [due: " + formatDate(this.dueDate) + "]";
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
                BiConsumer<InvalidState, NamedParameterMap> invalidStateHandler
        ) {

            Optional<InvalidParameterState> optionalInvalidParameterState =
                    Task.stateIfTaskParametersInvalid(
                            namedParameterMap,
                            "", "from", "to"
                    );

            if (optionalInvalidParameterState.isEmpty()) {
                String description = namedParameterMap.get("");
                NamedParameterMap invalidArgs = new NamedParameterMap();
                Optional<LocalDate> start = Task.optionalDateElseMap(namedParameterMap, invalidArgs, "from");
                Optional<LocalDate> end = Task.optionalDateElseMap(namedParameterMap, invalidArgs, "to");
                if (start.isEmpty() || end.isEmpty()) {
                    invalidArgs.moveToNewKey("", "description");
                    invalidArgs.moveToNewKey("from", "start date");
                    invalidArgs.moveToNewKey("to", "end date");
                    invalidStateHandler.accept(InvalidState.NOT_A_DATE, invalidArgs);
                    return Optional.empty();
                } else {
                    return Optional.of(new Event(description, start.get(), end.get()));
                }
            } else {
                InvalidParameterState invalidParameterState = optionalInvalidParameterState.get();
                invalidParameterState.parameters.moveToNewKey("", "description");
                invalidParameterState.parameters.moveToNewKey("from", "start date");
                invalidParameterState.parameters.moveToNewKey("to", "end date");
                invalidStateHandler.accept(invalidParameterState.state, invalidParameterState.parameters);
                return Optional.empty();
            }
        }

        @Override
        public String toString() {
            return super.toString() + " [from: " + formatDate(this.eventStart) + " | to: " +
                    formatDate(this.eventEnd) + "]";
        }
    }

    //endregion

    public String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(
                date.getYear() == Year.now().getValue()?
                "MMM d": "MMM d yyyy")
        );
    }

}
