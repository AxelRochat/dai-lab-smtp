package code;

import java.io.IOException;
import java.util.List;

/**
 * Main application for generating and sending prank emails
 */
public class MainApp {
    /**
     * Entry point of the application
     * @param args Command-line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        int numberOfGroups = 2; // Adjust as needed
        String smtpServerAddress = "localhost";
        int smtpPort = 1025;

        try {
            // Load configuration
            ConfigurationLoader configLoader = new ConfigurationLoader();
            List<String> emails = configLoader.loadVictimsFromJson();  
            List<EmailMessage> messages = configLoader.loadMessagesFromJson();

            // Generate groups
            PrankGenerator prankGenerator = new PrankGenerator(emails, messages, numberOfGroups);
            List<Group> groups = prankGenerator.createGroups();

            // Connect to SMTP server
            EmailClient emailClient = new EmailClient(smtpServerAddress, smtpPort);
            emailClient.connect();

            // Send prank emails
            for (Group group : groups) {
                EmailMessage prankMessage = prankGenerator.getRandomMessage();
                emailClient.sendEmail(group.getSender(), group.getRecipients(), prankMessage);
            }

            emailClient.disconnect();
            System.out.println("Prank emails sent successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
