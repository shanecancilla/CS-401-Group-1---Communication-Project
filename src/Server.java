import java.util.List;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.File;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.Runnable;
import java.lang.Thread;

public class Server {

    int numUsers = 0;
    int numITUsers = 0;
    int numChannels = 0;
    int numPrivateChannels = 0;

    List<User> users;
    List<ITUser> ITUsers;

    List<Channel> channels;
    List<PrivateMessage> privateMessages;

    void loadUsers()
    {
        File fp = new File("users.txt");
    }
    void loadChannels()
    {
        File fp = new File("channels.txt");
    }
    void loadITUser()
    {
        File fp = new File("itusers.txt");
    }

    void broadcastMessages()
    {

    }

    /**
     * Client Handler to handle clients
     * @see Client
     */
    public class ClientHandler implements Runnable
    {
        Socket socket;
        public void run()
        {
            try
            {
                ObjectOutputStream outputStream;
                ObjectInputStream inputStream;

                while (true)
                {

                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                try
                {
                    if(socket != null)
                        if (socket.isClosed() == false)
                            socket.close();
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                
            }
            finally
            {

            }

        }

        ClientHandler(Socket newSocket)
        {
            this.socket = newSocket;
        }
    }

    /**
     * The entry point for server side of the communication system
     * @param args Arguments for server (unused)
     */
    public void main(String[] args)
    {
        ServerSocket server = null;
        try {
            server = new ServerSocket(1234);
            while (true)
            {
                Socket client = server.accept();

                ClientHandler handler = new ClientHandler(client);

                Thread t = new Thread(handler);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (server != null)
            {
                if (server.isClosed())
                {
                    try
                    {
                        server.close();
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        }

    }
    
}