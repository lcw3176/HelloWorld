package com.joebrooks.mapshotserver.service;

import com.joebrooks.mapshotserver.domain.OnFailed;
import com.joebrooks.mapshotserver.domain.OnSuccess;
import org.springframework.stereotype.Service;

@Service
public class JsonMessageService {

    private String successFrame = "{\n" +
            "        \"blocks\": [\n" +
            "        {\n" +
            "            \"type\": \"header\",\n" +
            "                \"text\": {\n" +
            "            \"type\": \"plain_text\",\n" +
            "                    \"text\": \"Success\"\n" +
            "        }\n" +
            "        },\n" +
            "\n" +
            "        {\n" +
            "            \"type\": \"section\",\n" +
            "                \"fields\": [\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*When:*\\n %tT\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*Type:*\\n Success\"\n" +
            "            }\n" +
            "\t\t\t]\n" +
            "        },\n" +
            "\n" +
            "        {\n" +
            "            \"type\": \"section\",\n" +
            "                \"fields\": [\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*isFirstVisit:*\\n %b\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*usingCount:*\\n %d\"\n" +
            "            },\n" +
            "           {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*usedFunc:*\\n %s\"\n" +
            "            }" +
            "\t\t\t]\n" +
            "        }\n" +
            "\t]\n" +
            "}";

    private String failedFrame = "{\n" +
            "        \"blocks\": [\n" +
            "        {\n" +
            "            \"type\": \"header\",\n" +
            "                \"text\": {\n" +
            "            \"type\": \"plain_text\",\n" +
            "                    \"text\": \"Failed\"\n" +
            "        }\n" +
            "        },\n" +
            "\n" +
            "        {\n" +
            "            \"type\": \"section\",\n" +
            "                \"fields\": [\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*When:*\\n %tT\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*Type:*\\n Failed\"\n" +
            "            }\n" +
            "\t\t\t]\n" +
            "        },\n" +
            "\n" +
            "        {\n" +
            "            \"type\": \"section\",\n" +
            "                \"fields\": [\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*Error Title:*\\n %s\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*Error Message:*\\n %s\"\n" +
            "            }\n" +
            "\t\t\t]\n" +
            "        },\n" +
            "\n" +
            "        {\n" +
            "            \"type\": \"section\",\n" +
            "                \"fields\": [\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*isFirstVisit:*\\n %b\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*usingCount:*\\n %d\"\n" +
            "            },\n" +
            "           {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*usedFunc:*\\n %s\"\n" +
            "            }" +
            "\t\t\t]\n" +
            "        }\n" +
            "\t]\n" +
            "}";

    private String dailyFrame = "{\n" +
            "        \"blocks\": [\n" +
            "        {\n" +
            "            \"type\": \"header\",\n" +
            "                \"text\": {\n" +
            "            \"type\": \"plain_text\",\n" +
            "                    \"text\": \"Daily Report\"\n" +
            "        }\n" +
            "        },\n" +
            "\n" +
            "        {\n" +
            "            \"type\": \"section\",\n" +
            "                \"fields\": [\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*todayVisitor:*\\n %d\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*successCount:*\\n %d\"\n" +
            "            },\n" +
            "           {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*failedCount:*\\n %d\"\n" +
            "            }" +
            "\t\t\t]\n" +
            "        }\n" +
            "\t]\n" +
            "}";

    public String makeSuccessMessage(OnSuccess success){
        return String.format(successFrame, success.getDate(), success.isFirstVisit(), success.getUsingCount(), success.getUsedFunc());
    }

    public String makeFailedMessage(OnFailed failed){
        return String.format(failedFrame, failed.getDate(), failed.getTitle(), failed.getMessage(), failed.isFirstVisit(), failed.getUsingCount(), failed.getUsedFunc());
    }

    public String makeDaliyMessage(int userNumber, int successCount, int failedCount){
        return String.format(dailyFrame, userNumber, successCount, failedCount);
    }

}
