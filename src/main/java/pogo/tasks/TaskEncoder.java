package pogo.tasks;

import java.util.List;

/**
 * Interface for encoding and decoding tasks to some format.
 */
public interface TaskEncoder {
    /**
     * Encodes a list of tasks to some format.
     *
     * @param tasks List of tasks to encode.
     * @return Encoded string.
     */
    String encode(List<Task> tasks);

    /**
     * Decodes a list of tasks from some format.
     *
     * @param lines List of strings to decode.
     * @return List of decoded tasks.
     */
    List<Task> decode(List<String> lines);
}
