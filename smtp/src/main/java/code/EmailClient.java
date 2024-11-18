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

    public EmailClient(String addressSMTP, int portSMTP) {
        this.addressSMTP = addressSMTP;
        this.portSMTP = portSMTP;
    }

    public void connect() throws IOException {
        socket = new Socket(addressSMTP, portSMTP);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        System.out.println(reader.readLine()); // Server Greeting
    }

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

        writer.println("Subject: " + message.getSubject());
        writer.println("Content-Type: text/plain; charset=UTF-8");
        writer.println();
        writer.println(message.getBody());
        writer.println(".");
        System.out.println(reader.readLine());

        writer.println("QUIT");
        System.out.println(reader.readLine());
    }

    public void disconnect() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
