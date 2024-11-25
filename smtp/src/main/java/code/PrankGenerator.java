package code;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Generates prank groups and selects random prank email messages
 */
public class PrankGenerator {
    private List<String> emails;
    private List<EmailMessage> messages;
    private int numberOfGroups;

    /**
     * Constructs a PrankGenerator instance
     * @param emails List of email addresses to be used for prank groups
     * @param messages List of available prank email messages
     * @param numberOfGroups Number of prank groups to generate
     */
    public PrankGenerator(List<String> emails, List<EmailMessage> messages, int numberOfGroups) {
        this.emails = emails;
        this.messages = messages;
        this.numberOfGroups = numberOfGroups;
    }

    /**
     * Creates prank groups by shuffling the emails and dividing them into groups
     * @return List of generated prank groups
     */
    public List<Group> createGroups() {
        Collections.shuffle(emails);
        List<Group> groups = new ArrayList<>();

        for (int i = 0; i < numberOfGroups; i++) {
            int groupSize = 3 + new Random().nextInt(3); 
            int startIndex = i * groupSize;
            if (startIndex + groupSize <= emails.size()) {
                String sender = emails.get(startIndex);
                List<String> recipients = emails.subList(startIndex + 1, startIndex + groupSize);
                groups.add(new Group(sender, recipients));
            }
        }
        return groups;
    }

    /**
     * Selects a random email message from the list of available messages
     * @return A randomly selected EmailMessage
     */
    public EmailMessage getRandomMessage() {
        Random random = new Random();
        return messages.get(random.nextInt(messages.size()));
    }
}
