import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationLoader {

    public List<String> loadEmails(String filePath) throws IOException {
        List<String> emails = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        //remove if it's not and email
        emails.removeIf(email -> !email.contains("@")); 
        emails.removeIf(email -> !email.contains("."));
        return emails;
    }

    public List<EmailMessage> loadMessages(String filePath) throws IOException {
        List<EmailMessage> messages = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);

        StringBuilder subject = new StringBuilder();
        StringBuilder body = new StringBuilder();
        for (String line : lines) {
            if (line.startsWith("Subject:")) {
                subject = new StringBuilder(line.substring(8).trim());
            } else if (line.equals("---")) {
                messages.add(new EmailMessage(subject.toString(), body.toString()));
                body = new StringBuilder();
            } else {
                body.append(line).append("\n");
            }
        }
        return messages;
    }
}
