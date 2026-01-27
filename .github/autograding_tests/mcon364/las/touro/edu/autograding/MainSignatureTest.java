package mcon364.las.touro.edu;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MainSignatureTest {

    /**
     * Required:
     * - Class: mcon364.las.touro.edu.Main
     * - Method: public static Optional<String> getUserName()
     *
     * Behavior (in autograder):
     * - The environment variable USER is set (e.g., "Chana")
     * - getUserName() must return Optional.of(the env value)
     */
    @Test
    void main_has_static_getUserName_returning_optional() throws Exception {
        Class<?> clazz = Class.forName("mcon364.las.touro.edu.Main");

        Method m = clazz.getDeclaredMethod("getUserName", String.class);

        assertTrue(Modifier.isStatic(m.getModifiers()), "getUserName must be static");
        assertEquals(Optional.class, m.getReturnType(), "getUserName must return java.util.Optional");

        Object result = m.invoke(null,"NO_SUCH_ENV_VAR");
        assertNotNull(result, "getUserName must not return null");
        assertTrue(result instanceof Optional, "getUserName must return Optional");
    }

    @Test
    void getUserName_returns_student_name_env_var_when_present() throws Exception {
        String expected = System.getenv("USER");
        assertNotNull(expected, "Autograder misconfigured: USER env var must be set");
        assertFalse(expected.isBlank(), "Autograder misconfigured: USER must be non-blank");

        Class<?> clazz = Class.forName("mcon364.las.touro.edu.Main");
        Method m = clazz.getDeclaredMethod("getUserName", String.class);

        @SuppressWarnings("unchecked")
        Optional<String> result = (Optional<String>) m.invoke(null, "USER");

        assertNotNull(result, "getUserName must not return null");
        assertTrue(result.isPresent(), "Expected Optional.of(USER) when USER is set");
        assertEquals(expected, result.get(), "getUserName must return the exact value of USER");
    }
}
