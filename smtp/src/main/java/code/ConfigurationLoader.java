package code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationLoader {

    // Charge les adresses emails depuis le fichier victims.json
    public List<String> loadVictimsFromJson() throws IOException {
        // Charger le fichier JSON depuis le classpath
        InputStream is = getClass().getClassLoader().getResourceAsStream("victims.json");
        if (is == null) {
            throw new IOException("Fichier victims.json introuvable dans le classpath.");
        }

        // Utilisation de ObjectMapper pour parser le JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(is);
        List<String> victims = new ArrayList<>();

        // Parcourir le JSON et ajouter les adresses email dans la liste
        JsonNode victimsNode = rootNode.path("victims");
        for (JsonNode victimNode : victimsNode) {
            victims.add(victimNode.asText());
        }
        return victims;
    }

    // Charge les messages d'email depuis le fichier email.json
    public List<EmailMessage> loadMessagesFromJson() throws IOException {
        // Charger le fichier JSON depuis le classpath
        InputStream is = getClass().getClassLoader().getResourceAsStream("email.json");
        if (is == null) {
            throw new IOException("Fichier email.json introuvable dans le classpath.");
        }

        // Utilisation de ObjectMapper pour parser le JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(is);
        List<EmailMessage> messages = new ArrayList<>();

        // Parcourir le JSON et ajouter les messages dans la liste
        JsonNode emailsNode = rootNode.path("email");
        for (JsonNode emailNode : emailsNode) {
            String subject = emailNode.path("subject").asText();
            String body = emailNode.path("body").asText();
            messages.add(new EmailMessage(subject, body));
        }

        return messages;
    }
}
