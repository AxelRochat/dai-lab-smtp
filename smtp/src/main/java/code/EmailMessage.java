package code;
/**
 * Represents an email message with a subject and body
 */
public class EmailMessage {
    private String subject;
    private String body;

    /**
     * Constructs an EmailMessage instance
     * @param subject Subject of the email
     * @param body Body of the email
     */
    public EmailMessage(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    /**
     * Retrieves the subject of the email
     * @return The subject as a string
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Retrieves the body of the email
     * @return The body as a string
     */
    public String getBody() {
        return body;
    }

    /**
     * Returns a string representation of the email message
     * @return A formatted string containing the subject and body
     */
    @Override
    public String toString() {
        return "Subject: " + subject + "\nBody: " + body;
    }
}
