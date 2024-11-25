package code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationLoader {

    /**
     * Loads email addresses from the victims.json file
     * @return List of email addresses
     * @throws IOException if the file cannot be found or read
     */
    public List<String> loadVictimsFromJson() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("victims.json");
        if (is == null) {
            throw new IOException("Fichier victims.json introuvable dans le classpath.");
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(is);
        List<String> victims = new ArrayList<>();

        JsonNode victimsNode = rootNode.path("victims");
        for (JsonNode victimNode : victimsNode) {
            victims.add(victimNode.asText());
        }
        return victims;
    }

    /**
     * Loads email messages from the email.json file
     * @return List of EmailMessage objects
     * @throws IOException if the file cannot be found or read
     */
    public List<EmailMessage> loadMessagesFromJson() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("email.json");
        if (is == null) {
            throw new IOException("Fichier email.json introuvable dans le classpath.");
        }
    
        ObjectMapper mapper = new ObjectMapper();
        List<EmailMessage> messages = new ArrayList<>();
    
        JsonNode rootNode = mapper.readTree(is);
        for (JsonNode emailNode : rootNode) {
            String subject = emailNode.path("subject").asText();
            String body = emailNode.path("body").asText();
            messages.add(new EmailMessage(subject, body));
        }
    
        return messages;
    }
}
