import java.util.List;

import java.net.ServerSocket;
import java.net.Socket;

import java.lang.Runnable;
import java.lang.Thread;

public class Server {

    int numUsers = 0;
    int numChannels = 0;

    List<User> users;
    List<ITUser> ITUsers;
    List<Channel> channels;

    /**
     * Client Handler to handle clients
     * @see Client
     */
    public class ClientHandler implements Runnable
    {
        void run()
        {
            try
            {
                while (true)
                {

                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {

            }

        }

        ClientHandler()
        {

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


            }
        } catch (Exception e) {
            // TODO: handle exception
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