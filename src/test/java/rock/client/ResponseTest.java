package rock.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ResponseTest {
    @Test
    public void testValidResponseMsg() {
        final String TEST_STR = "TEST";
        final Response TEST_RESPONSE = Response.createValidResponse(TEST_STR);
        assertEquals(TEST_STR, TEST_RESPONSE.getMessage());
    }
    @Test
    public void testValidResponseIsError() {
        final Response TEST_RESPONSE = Response.createValidResponse("Irrelevant");
        assertEquals(false, TEST_RESPONSE.isError());
    }
    @Test
    public void testErrorResponseMsg() {
        final String TEST_STR = "TEST";
        final Response TEST_RESPONSE = Response.createErrorResponse(TEST_STR);
        assertEquals(TEST_STR, TEST_RESPONSE.getMessage());
    }
    @Test
    public void testErrorResponseIsError() {
        final Response TEST_RESPONSE = Response.createErrorResponse("Irrelevant");
        assertEquals(true, TEST_RESPONSE.isError());
    }
}

