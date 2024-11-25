package code;
import java.util.List;


/**
 * Represents a group of email participants, including a sender and a list of recipients
 */
public class Group {
    private String sender;
    private List<String> recipients;

    /**
     * Constructs a Group instance
     * @param sender Email address of the sender
     * @param recipients List of recipient email addresses
     */
    public Group(String sender, List<String> recipients) {
        this.sender = sender;
        this.recipients = recipients;
    }

    /**
     * Retrieves the sender's email address
     * @return The sender's email address as a string
     */
    public String getSender() {
        return this.sender;
    }

    /**
     * Retrieves the list of recipients' email addresses
     * @return A list of email addresses
     */
    public List<String> getRecipients() {
        return recipients;
    }
}
