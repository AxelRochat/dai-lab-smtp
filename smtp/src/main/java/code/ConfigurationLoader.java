package code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationLoader {

    // Charger adresses emails depuis victims.json
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
        System.out.println("debug " + victims);
        return victims;
    }

    // Charger messages d'email depuis email.json
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
