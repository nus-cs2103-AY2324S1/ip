package todoify.storage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class InternalPathTest {

    @Test
    public void excludingLastComponent_nonEmptyPath_correctEquivalentPath() {
        InternalPath path1;
        InternalPath path2;
        path1 = InternalPath.of("aaa", "bbb", "ccc");
        path2 = InternalPath.of("aaa", "bbb");

        assertArrayEquals(new String[] { "aaa", "bbb" }, path1.excludingLastComponent().getComponents());
        assertArrayEquals(new String[] { "aaa", "bbb" }, path2.getComponents());

        path1 = InternalPath.of("123");

        assertArrayEquals(new String[] {}, path1.excludingLastComponent().getComponents());

        path2 = InternalPath.of("456", "789", "012");

        assertArrayEquals(
                new String[] { "456" },
                path2.excludingLastComponent().excludingLastComponent().getComponents()
        );
    }

    @Test
    public void excludingLastComponent_emptyPath_exceptionThrown() {
        try {
            InternalPath path = InternalPath.of("");
            path.excludingLastComponent();
        } catch (NegativeArraySizeException e) {
            // Success.
        }
    }

    @Test
    public void toJavaNioFilePath_correctEquivalentNioPath() {
        InternalPath path;

        path = InternalPath.of("aaa", "bbb", "ccc");
        assertEquals(
                Path.of("/base", "aaa", "bbb", "ccc"),
                path.toJavaNioFilePath(Path.of("/base"))
        );

        path = InternalPath.of("a1", "b2");
        assertEquals(
                Path.of("", "a1", "b2"),
                path.toJavaNioFilePath(Path.of(""))
        );

        path = InternalPath.of("zzz");
        assertEquals(
                Path.of("x/yy", "zzz"),
                path.toJavaNioFilePath(Path.of("x/yy"))
        );

        path = InternalPath.of("");
        Path basePath = Path.of("aaa/bbb");
        assertEquals(
                basePath,
                path.toJavaNioFilePath(basePath)
        );
    }

}
