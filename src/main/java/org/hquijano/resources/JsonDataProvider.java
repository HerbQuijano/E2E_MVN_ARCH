package org.hquijano.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonDataProvider {

    public static List<Map<String, String>> readJsonFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(filePath);
        return objectMapper.readValue(jsonFile, List.class);
    }
}