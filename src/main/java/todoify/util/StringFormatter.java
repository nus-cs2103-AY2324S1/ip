package todoify.util;

import java.util.Arrays;
import java.util.stream.Stream;

import javafx.util.Pair;
import todoify.chatbot.ChatCommand;
import todoify.taskmanager.task.Task;

/**
 * A utility class for formatting data into strings.
 */
public final class StringFormatter {

    private StringFormatter() {
        // A utility class constructor should not be used.
    }

    /**
     * Renders the task list into the given string builder, and optionally apply the modifier to the stream before
     * doing so.
     *
     * @param taskIndexedStream The stream to use as the index-task-pair list to render.
     */
    public static String formatTaskIndexedStreamToString(Stream<Pair<Integer, Task>> taskIndexedStream) {
        StringBuilder builder = new StringBuilder();
        taskIndexedStream.forEach(integerTaskPair -> {
            builder.append("\n");
            builder.append(integerTaskPair.getKey() + 1);
            builder.append(". ");
            builder.append(integerTaskPair.getValue().toString());
        });
        return builder.toString().trim();
    }

    /**
     * Renders the given parameter list into a user-readable description string.
     *
     * <p>
     *     For example, an input with parameters A and B, and placeholders C and D, will yield the output
     *     "--A &lt;C&gt; --B &lt;D&gt;".
     * </p>
     *
     * @param parameters The array of parameters. May have null values to skip the corresponding placeholder.
     * @param placeholders The array of placeholders. May have null values to omit the placeholder.
     * @return The final formatted parameter list string.
     */
    public static String formatParameterListWithPlaceholdersToString(String[] parameters, String[] placeholders) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < parameters.length; i++) {
            String parameter = parameters[i];
            String placeholder = i < placeholders.length ? placeholders[i] : null;

            if (parameter == null) {
                continue;
            }

            if (placeholder == null) {
                placeholder = "";
            }

            if (!parameter.startsWith(ChatCommand.PARAMETER_PREFIX)) {
                parameter = ChatCommand.PARAMETER_PREFIX + parameter;
            }

            builder.append(" ");
            builder.append(parameter);
            if (!placeholder.isBlank()) {
                builder.append(" <");
                builder.append(placeholder);
                builder.append(">");
            }
        }

        return builder.toString().trim();
    }

    /**
     * Renders the given parameter list into a user-readable description string.
     *
     * <p>
     *     For example, an input with parameters A and B, and placeholder C, will yield the output
     *     "--A &lt;C&gt; --B &lt;C&gt;".
     * </p>
     *
     * @param parameters The array of parameters. Null values in the array are skipped.
     * @param placeholder The placeholder to use. May be null to omit the placeholder.
     * @return The final formatted parameter list string.
     */
    public static String formatParameterListWithPlaceholderToString(String[] parameters, String placeholder) {
        String[] placeholders = Arrays.copyOf(parameters, parameters.length);
        Arrays.fill(placeholders, placeholder);
        return formatParameterListWithPlaceholdersToString(parameters, placeholders);
    }
}
