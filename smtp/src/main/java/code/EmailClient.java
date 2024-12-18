package code;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class EmailClient {
    private String addressSMTP;
    private int portSMTP;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    /**
     * Constructs an EmailClient instance
     * @param addressSMTP SMTP server address
     * @param portSMTP SMTP server port
     */
    public EmailClient(String addressSMTP, int portSMTP) {
        this.addressSMTP = addressSMTP;
        this.portSMTP = portSMTP;
    }

    /**
     * Connects to the SMTP server
     * @throws IOException if an I/O error occurs during connection
     */
    public void connect() throws IOException {
        socket = new Socket(addressSMTP, portSMTP);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        System.out.println(reader.readLine()); // Server Greeting
    }

    /**
     * Sends an email using the SMTP protocol
     * @param sender Email address of the sender
     * @param recipients List of recipient email addresses
     * @param message EmailMessage object containing the subject and body of the email
     * @throws IOException if an I/O error occurs during email sending
     */
    public void sendEmail(String sender, List<String> recipients, EmailMessage message) throws IOException {
        writer.println("HELO localhost");
        System.out.println(reader.readLine());

        writer.println("MAIL FROM:<" + sender + ">");
        System.out.println(reader.readLine());

        for (String recipient : recipients) {
            writer.println("RCPT TO:<" + recipient + ">");
            System.out.println(reader.readLine());
        }

        writer.println("DATA");
        System.out.println(reader.readLine());

        writer.println("From: " + sender);
        writer.print("To: ");
        for (int i = 0; i < recipients.size(); i++) {
            writer.print(recipients.get(i));
            if (i < recipients.size() - 1) {
                writer.print(", ");
            }
        }

        writer.println();
        writer.println("Content-Type: text/plain; charset=UTF-8");
        writer.println("Subject: " + message.getSubject());
        writer.println();
        writer.println(message.getBody());
        writer.println(".");
        System.out.println(reader.readLine());

        writer.println("QUIT");
        System.out.println(reader.readLine());
    }

    /**
     * Disconnects from the SMTP server
     * @throws IOException if an I/O error occurs during disconnection
     */
    public void disconnect() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
