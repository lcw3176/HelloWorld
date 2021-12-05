package com.joebrooks.mapshotserver.log.service;

import com.joebrooks.mapshotserver.log.domain.Failed;
import com.joebrooks.mapshotserver.log.domain.Success;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private String successFrame = "{\n" +
            "        \"blocks\": [\n" +
            "        {\n" +
            "            \"type\": \"header\",\n" +
            "                \"text\": {\n" +
            "            \"type\": \"plain_text\",\n" +
            "                    \"text\": \"Mapshot Alert\"\n" +
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
            "            }\n" +
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
            "                    \"text\": \"Mapshot Alert\"\n" +
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
            "                    \"text\": \"*Type:*\\n Error\"\n" +
            "            }\n" +
            "\t\t\t]\n" +
            "        },\n" +
            "\n" +
            "        {\n" +
            "            \"type\": \"section\",\n" +
            "                \"fields\": [\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*Exception Type:*\\n %s\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"mrkdwn\",\n" +
            "                    \"text\": \"*Exception Message:*\\n %s\"\n" +
            "            }\n" +
            "\t\t\t]\n" +
            "        }\n" +
            "\t]\n" +
            "}";


    public String makeSuccessMessage(Success success){
        return String.format(successFrame, success.getDate(), success.getUser().isFirstVisit(), success.getUser().getUsingCount());
    }

    public String makeFailedMessage(Failed failed){
        return String.format(failedFrame, failed.getDate(), failed.getTitle(), failed.getMessage());
    }

}
