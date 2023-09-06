package catbot.task;

import catbot.internal.NamedParameterMap;
import catbot.io.ErrorIndicatorIo.InvalidState;

import java.io.Serializable;
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

    private static void mapParameterName(NamedParameterMap namedParameterMap, String oldKey, String newKey) {
        if (!namedParameterMap.containsKey(newKey) && namedParameterMap.containsKey(oldKey)) {
            namedParameterMap.addNamedParameter(newKey, namedParameterMap.get(oldKey));
            namedParameterMap.remove(oldKey);
        }
    }
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
                Task.mapParameterName(invalidParameterState.parameters, "", "description");
                invalidStateHandler.accept(invalidParameterState.state, invalidParameterState.parameters);
                return Optional.empty();
            }
        }
    }

    public static class Deadline extends Task {

        private String dueDate;

        public Deadline(String desc, String date) {
            setDescription(desc);
            setDueDate(date);
        }

        public void setDueDate(String dueDate) {
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
                return Optional.of(new Deadline(
                        namedParameterMap.get(""),
                        namedParameterMap.get("by")
                ));
            } else {
                InvalidParameterState invalidParameterState = optionalInvalidParameterState.get();
                Task.mapParameterName(invalidParameterState.parameters, "", "description");
                Task.mapParameterName(invalidParameterState.parameters, "by", "due date");
                invalidStateHandler.accept(invalidParameterState.state, invalidParameterState.parameters);
                return Optional.empty();
            }
        }

        @Override
        public String toString() {
            return super.toString() + " [due: " + this.dueDate + "]";
        }
    }

    public static class Event extends Task {

        private String eventStart;
        private String eventEnd;

        public Event(String desc, String start, String end) {
            setDescription(desc);
            setEventStart(start);
            setEventEnd(end);
        }

        public void setEventEnd(String eventEnd) {
            this.eventEnd = eventEnd;
        }

        public void setEventStart(String eventStart) {
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
                return Optional.of(new Event(
                        namedParameterMap.get(""),
                        namedParameterMap.get("from"),
                        namedParameterMap.get("to")
                ));
            } else {
                InvalidParameterState invalidParameterState = optionalInvalidParameterState.get();
                Task.mapParameterName(invalidParameterState.parameters, "", "description");
                Task.mapParameterName(invalidParameterState.parameters, "due", "due date");
                invalidStateHandler.accept(invalidParameterState.state, invalidParameterState.parameters);
                return Optional.empty();
            }
        }

        @Override
        public String toString() {
            return super.toString() + " [from: " + this.eventStart + " | to: " + this.eventEnd + "]";
        }
    }

    //endregion

}
