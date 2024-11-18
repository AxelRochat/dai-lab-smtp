import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PrankGenerator {
    private List<String> emails;
    private List<EmailMessage> messages;
    private int numberOfGroups;

    public PrankGenerator(List<String> emails, List<EmailMessage> messages, int numberOfGroups) {
        this.emails = emails;
        this.messages = messages;
        this.numberOfGroups = numberOfGroups;
    }

    public List<Group> createGroups() {
        Collections.shuffle(emails);
        List<Group> groups = new ArrayList<>();

        for(int i=0; i < numberOfGroups; i++){
           int groupSize =  2 + new Random().nextInt(4);
            int startIndex = i * groupSize;
            if (startIndex + groupSize <= emails.size()) {
                String sender = emails.get(startIndex);
                List<String> recipients = emails.subList(startIndex + 1, startIndex + groupSize);
                groups.add(new Group(sender, recipients));
            }
        }
        return groups;
    }

    public EmailMessage getRandomMessage(){
        Random random = new Random();
        return messages.get(random.nextInt(messages.size()));
    }
}
