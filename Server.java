import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) throws IOException {
        Inventory inventory = new Inventory();
        final int SBAP_PORT = 6969;
        ServerSocket server = new ServerSocket(SBAP_PORT);

        while (true){

            System.out.println("Waiting for clients to connect...");
            //blocks the program from running until a client socket is accepted and returns the socket
            Socket client = server.accept();
            System.out.println("Client connected.");

            //client parameter is the new client socket and bank is the bank object
            InventoryService service = new InventoryService(client, inventory);
            Thread t = new Thread(service);
            t.start();
        }
    }
}

class InventoryService implements Runnable, Protocol {

    private DataInputStream in;
    private DataOutputStream out;
    private Socket client;
    private Inventory inventory;

    public InventoryService(Socket client, Inventory inventory)
    {
        this.client = client;
        this.inventory = inventory;
    }

    public void run()
    {
        try
        {
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            doService();

        }
        catch (IOException exception)
        {
            System.out.println("something is wrong");
            // do nothing
        }
        finally
        {
            try{
                client.close();
            }
            catch (IOException exception){
                // do nothing
            }
        }
    }

    /**
     *   Executes all commands until the QUIT command or the
     *   end of input.
     */
    private void doService() throws IOException {
        int command=0;
        int quantity;
        int unitsRemoved;
        int unitsAvailable = 0;
        String itemName;
        while(command!=QUIT)
        {
            command = in.readInt();
            switch(command){
                case ADD_ITEM:
                    itemName = in.readUTF();
                    quantity = in.readInt();
                    inventory.addItem(itemName, quantity);
                    out.writeInt(SUCCEED);
                    out.flush();
                    break;

                case CHECK_INVENTORY:
                    itemName = in.readUTF();
                    inventory.checkInventory(itemName);
                    out.writeInt(SUCCEED);
                    out.writeInt(unitsAvailable);
                    out.flush();
                    break;

                case TAKE_ITEM:
                    itemName = in.readUTF();
                    quantity = in.readInt();
                    unitsRemoved = inventory.takeItem(itemName, quantity);
                    out.writeInt(SUCCEED);
                    out.writeInt(unitsRemoved);
                    out.flush();
                    break;

                    // TODO case GET_THRESHOLD:

                case QUIT:
                    out.writeInt(QUIT);
                    out.flush();
                    break;

                default:
                    out.writeInt(INVALID_COMMAND);
                    out.flush();
            }

        }
    }
}

