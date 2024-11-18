import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class EmailClient {
    private String AdressSMTP;
    private int PortSMTP;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader; 

    public EmailClient(String AdressSMTP, int PortSMTP){
        this.AdressSMTP = AdressSMTP;
        this.PortSMTP = PortSMTP;
    }

    public void connect() throws IOException {
        
    }
}
