package todoify.storage;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * An internal path is a class that represents the path from an arbitrary base directory.
 */
public class InternalPath {

    private static final Path DEFAULT_BASE_JAVA_NIO_FILE_PATH = Path.of(".", "todoifydata");

    private final String[] components;

    /**
     * Constructs a new path with path components representing the internal relative path starting from the base
     * directory.
     *
     * @param components The path components.
     */
    private InternalPath(String... components) {
        this.components = components;
    }

    /**
     * Constructs a new path with path components representing the internal relative path starting from the base
     * directory.
     *
     * <p>
     * All components are separated by path separators automatically, do not supply them. Instead, supply each component
     * in the path as a separate argument.
     * </p>
     *
     * @param components The path components.
     */
    public static InternalPath of(String... components) {
        return new InternalPath(components);
    }

    /**
     * Returns the internal relative path components, representing the path if joined with a path separator.
     *
     * @return The internal relative path components.
     */
    public String[] getComponents() {
        return Arrays.copyOf(this.components, this.components.length);
    }

    /**
     * Returns a new path relative to the configured base directory, while removing the last component in the path.
     *
     * @return The path after removing the last path component.
     * @throws NegativeArraySizeException if there are no more components in the path to strip.
     */
    public InternalPath excludingLastComponent() {
        String[] pathComponents = Arrays.copyOf(this.components, this.components.length - 1);
        return new InternalPath(pathComponents);
    }

    /**
     * Returns a new path relative to the configured base directory, while adding the given last component to the path
     *
     * @param component The new component to append to the end of the path.
     * @return The path after adding the new last path component.
     */
    public InternalPath appendingLastComponent(String component) {
        String[] pathComponents = Arrays.copyOf(this.components, this.components.length + 1);
        pathComponents[pathComponents.length - 1] = component;
        return new InternalPath(pathComponents);
    }

    /**
     * Returns the given path's full path relative to the configured base directory, as a {@link java.nio.file.Path}
     * instance.
     *
     * @param basePath The Java NIO path to the base directory. May be null to use default.
     * @return The path for the given file as a {@link java.nio.file.Path} instance.
     */
    public Path toJavaNioFilePath(Path basePath) {
        if (basePath == null) {
            basePath = DEFAULT_BASE_JAVA_NIO_FILE_PATH;
        }
        return java.nio.file.Path.of(basePath.toString(), this.components);
    }

    /**
     * Returns a new File instance constructed from this path.
     *
     * @param basePath The Java NIO path to the base directory. May be null to use default.
     * @return The Java IO File instance.
     */
    public File toFile(Path basePath) {
        return this.toJavaNioFilePath(basePath).toFile();
    }

    @Override
    public String toString() {
        return String.format("%s=%s", this.getClass().getTypeName(), Arrays.toString(this.components));
    }
}
