package code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationLoader {
    public List<String> loadEmailsFromJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(filePath));
        List<String> emails = new ArrayList<>();
        root.get("victims").forEach(node -> emails.add(node.asText()));
        return emails;
    }

    public List<EmailMessage> loadMessagesFromJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(filePath));
        List<EmailMessage> messages = new ArrayList<>();
        root.get("email").forEach(emailNode -> {
            String subject = emailNode.elements().next().get("subject").asText();
            String body = emailNode.elements().next().get("body").asText();
            messages.add(new EmailMessage(subject, body));
        });
        return messages;
    }
}
