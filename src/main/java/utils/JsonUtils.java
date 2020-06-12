package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.json.AnaliticsModel;

import java.util.List;
import java.util.stream.Collectors;

public class JsonUtils {

    public static List<String> getTestNames(String responseResult) {
        //create model
        ObjectMapper mapper = new ObjectMapper();
        List<AnaliticsModel> participantJsonList = null;
        try {
            participantJsonList = mapper.readValue(responseResult, new TypeReference<List<AnaliticsModel>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //create list of tests
        return participantJsonList.stream()
                .filter(model -> ("FAILED".equals(model.getStatus()) || "SKIPPED".equals(model.getStatus()))
                        && "UNKNOWN".equals(model.getReason()))
                .map(AnaliticsModel::getName).map(JsonUtils::getTestName).collect(Collectors.toList());

    }

    private static String getTestName(String fullName) {
        int index = fullName.lastIndexOf(".");
        return fullName.substring(0, index);
    }
}
