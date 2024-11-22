package code;
public class EmailMessage {
    private String subject;
    private String body;

    public EmailMessage(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Subject: " + subject + "\nBody: " + body;
    }
}
