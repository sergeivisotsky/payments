package org.sergei.payments;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

/**
 * @author Sergei Visotsky
 */
public abstract class AbstractTest {

    public static String readFromJSONFile(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream != null) {
                return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            } else {
                throw new RuntimeException("Required test JSON file not found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
