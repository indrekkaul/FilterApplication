package com.example.filters.service;

import com.example.filters.exception.CustomServiceException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomServiceExceptionTest {

    @Test
    void testCustomServiceExceptionConstructorWhenValidMessageAndCauseThenExceptionThrown() {
        String message = "Test message";
        Throwable cause = new Throwable("Test cause");

        CustomServiceException exception = assertThrows(CustomServiceException.class, () -> {
            throw new CustomServiceException(message, cause);
        });

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testCustomServiceExceptionConstructorWhenValidMessageAndNullCauseThenExceptionThrown() {
        String message = "Test message";
        Throwable cause = null;

        CustomServiceException exception = assertThrows(CustomServiceException.class, () -> {
            throw new CustomServiceException(message, cause);
        });

        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testCustomServiceExceptionConstructorWhenNullMessageAndValidCauseThenExceptionThrown() {
        String message = null;
        Throwable cause = new Throwable("Test cause");

        CustomServiceException exception = assertThrows(CustomServiceException.class, () -> {
            throw new CustomServiceException(message, cause);
        });

        assertNull(exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testCustomServiceExceptionConstructorWhenNullMessageAndNullCauseThenExceptionThrown() {
        String message = null;
        Throwable cause = null;

        CustomServiceException exception = assertThrows(CustomServiceException.class, () -> {
            throw new CustomServiceException(message, cause);
        });

        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }
}
