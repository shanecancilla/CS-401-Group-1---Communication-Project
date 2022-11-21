import java.util.List;
import java.util.ArrayList;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.File;

import java.io.InputStream;
import java.io.OutputStream;

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
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                ObjectInputStream ObjectInputStream = new ObjectInputStream(inputStream);

                List<NetworkMessage> outputMessages = new ArrayList<NetworkMessage>();
                List<NetworkMessage> inputMessages = new ArrayList<NetworkMessage>();
                
                boolean shouldQuit = false;

                while (shouldQuit == false)
                {
                    inputMessages = (List<NetworkMessage>) ObjectInputStream.readObject();

                    for (NetworkMessage msg : inputMessages)
                    {
                        switch(msg.getNetworkMessageType())
                        {
                            case None:
                            {
                                break;
                            }
                            case Login:
                            {
                                break;
                            }
                            case Logout:
                            {
                                NetworkMessage logOutSucess;
                                shouldQuit = true;
                                outputMessages.add(logOutSucess);
                                break;   
                            }
                            case CreateUser:
                            {
                                String newUsername = "";
                                String newPassword = "";
                                for (int i = 0; i < users.size(); i++)
                                {
                                    if (users.get(i).getUsername().equals(newUsername))
                                    {
                                        shouldQuit = true;
                                    }
                                }
                                break;
                            }
                            case CreateChannel:
                            {
                                String newChannelName = "";
                                for (int i = 0; i < channels.size(); i++)
                                {
                                    if (channels.get(i).getName().equals(newChannelName))
                                    {
                                        break;        
                                    }
                                }

                                Channel newChannel = new Channel(numChannels, newChannelName);
                                channels.add(newChannel);
                                break;
                            }
                            case HideChannel:
                            {
                                String channelName = "";
                                for (int i = 0; i < channels.size(); i++)
                                {
                                    if (channels.get(i).getName().equals(channelName))
                                    {
                                        channels.get(i).hideChannel();
                                        break;
                                    }

                                }
                                break;
                            }
                            case CreateMessage:
                            {
                                String message = "";
                                String channel = "";

                                for (int i = 0; i < channels.size(); i++)
                                {
                                    if (channels.get(i).getName().equals(channel))
                                    {
                                        Message newMessage = new Message(channels.get(i).messages.size(), 0, message);
                                        channels.get(i).addMessage(newMessage);
                                        break;
                                    }

                                } 
                                break;
                            }
                            case HideMessage:
                            {
                                break;
                            }

                            default:
                            {
                                break;
                            }
                        }

                    }

                    objectOutputStream.writeObject(outputMessages);
                    objectOutputStream.reset();
                }

                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
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