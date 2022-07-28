import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;


public class Client implements Protocol, Runnable {

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", Protocol.PORT);
        DataInputStream in = new DataInputStream(client.getInputStream);
        DataOutputStream out = new DataOutputStream(client.getOutputStream);
    }

    @Override
    public void run() {

        try {
            Socket client = new Socket("localhost", Protocol.PORT);
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            int randNum;
            Random rand = new Random();
            randNum = rand.ints(100, 255).findFirst().getAsInt();
            Thread.sleep(randNum);

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

