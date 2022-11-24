import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

import java.net.ServerSocket;
import java.net.Socket;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.File;
import java.io.FileWriter;

import java.io.IOException;

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
    List<PrivateChannels> privateMessages;

    Logs serverLogs;

    void loadUsers()
    {
        File fp = new File("users.txt");
        try
        {
            Scanner scan = new Scanner(fp);

            while(scan.hasNextLine())
            {
                String line = scan.nextLine();
                String tokens[] = line.split(";");

                users.add(new User(Integer.parseInt(tokens[0]), tokens[1], tokens[2]));
                numUsers += 1;
            }
            scan.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    void saveUsers()
    {
        String str = "";

		for (int user = 0; user < users.size(); user++)
		{
			str += Integer.toString(user)+ ";" + 
            users.get(user).getUsername() + ";" +
            users.get(user).getPassword() + "\n";
			
		}
        
		FileWriter fp;
		
		try
		{
			fp = new FileWriter("users.txt");
			fp.write(str);
		}
		catch (Exception e)
		{
			System.out.println("Cannot write " + "users.txt");
			return;
		}
		try {
		fp.close();
		}
		catch(Exception e)
		{
			return;
		}
		
    }

    void loadChannels()
    {
        File fp = new File("channels.txt");

        try {
            Files.createDirectory(Paths.get("channels"));
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        try
        {
            Scanner scan = new Scanner(fp);
            while (scan.hasNextLine())
            {
                String line = scan.nextLine();

                // https://stackoverflow.com/a/18893443
                String tokens[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                tokens[1] = tokens[1].substring(1, tokens.length - 1);

                Channel channel = new Channel(Integer.parseInt(tokens[0]), tokens[1]);
                if (Boolean.parseBoolean(tokens[2]))
                {
                    channel.hideChannel();
                }
                
                // Read message from file in channels directory
                try
                {
                    File channelFP = new File("channels/" + channel.getName() + ".txt");

                    Scanner scan0 = new Scanner(channelFP);
                    while (scan0.hasNextLine())
                    {
                        String secondLine = scan0.nextLine();
                        String secondTokens[] = secondLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                        Message msg = new Message(
                            Integer.parseInt(secondTokens[0]),
                            Integer.parseInt(secondTokens[1]),
                            secondTokens[3].substring(1, secondTokens.length - 1)
                        );

                        if (Boolean.parseBoolean(secondTokens[2]))
                        {
                            msg.hideMessage();
                        }

                        channel.addMessage(msg);
                    }

                    scan0.close();
                }
                catch (Exception e0)
                {
                    e0.printStackTrace();
                }

                channels.add(channel);
                numChannels += 1;
            }

            scan.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    void saveChannels()
    {
        
        String str = "";
        try {
            Files.createDirectory(Paths.get("channels"));
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        List<String> channelMetadata = new ArrayList<>(channels.size());

		for (int channel = 0; channel < channels.size(); channel++)
		{
            try
            {
                String channelStr = "";

                String channelLine = 
                    Integer.toString(channels.get(channel).getID()) + ";" +
                    "\"" + channels.get(channel).getName() + "\"" + ";" +
                    Boolean.toString(channels.get(channel).isHidden());

                channelMetadata.add(channelLine);


                FileWriter fp = new FileWriter("channels/" + channels.get(channel).getName() + ".txt");
                for (int message = 0; message < channels.get(channel).messages.size(); message++)
                {
                    str += 
                        Integer.toString(channels.get(channel).messages.get(message).getID()) + ";" +
                        Integer.toString(channels.get(channel).messages.get(message).getUserID()) + ";" +
                        Boolean.toString(channels.get(channel).messages.get(message).isHidden()) + ";" +
                        "\"" + channels.get(channel).messages.get(message).getMessageContent() + "\""+ "\n";

                }
                fp.write(channelStr);
                fp.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

		}
        
		FileWriter fp;
		
		try
		{
			fp = new FileWriter("channels.txt");

            for (int channel = 0; channel < channelMetadata.size(); channel++)
            {
                str += channelMetadata.get(channel) + "\n";
            }

			fp.write(str);
		}
		catch (Exception e)
		{
			System.out.println("Cannot write " + "channels.txt");
			return;
		}
		try {
		fp.close();
		}
		catch(Exception e)
		{
			return;
		}
    }

    void loadITUser()
    {
        File fp = new File("ITUsers.txt");
        try
        {
            Scanner scan = new Scanner(fp);

            while(scan.hasNextLine())
            {
                String line = scan.nextLine();
                String tokens[] = line.split(";");

                ITUsers.add(new ITUser(Integer.parseInt(tokens[0]), tokens[1], tokens[2]));
                numITUsers += 1;
            }
            scan.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    void saveITUsers()
    {
        String str = "";

		for (int user = 0; user < ITUsers.size(); user++)
		{
			str += Integer.toString(user)+ ";" + 
            ITUsers.get(user).getUsername() + ";" +
            ITUsers.get(user).getPassword() + "\n";
		}
        
		FileWriter fp;
		
		try
		{
			fp = new FileWriter("ITUsers.txt");
			fp.write(str);
		}
		catch (Exception e)
		{
			System.out.println("Cannot write " + "users.txt");
			return;
		}
		try {
		fp.close();
		}
		catch(Exception e)
		{
			return;
		}
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
            serverLogs.addLog("Connected " + socket.getInetAddress().getHostAddress());
            try
            {
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                ObjectInputStream ObjectInputStream = new ObjectInputStream(inputStream);

                List<NetworkMessage> outputMessages = new ArrayList<NetworkMessage>();
                List<NetworkMessage> inputMessages = new ArrayList<NetworkMessage>();
                
                boolean shouldQuit = false;
                boolean hasLogin = false;
                while (hasLogin == false)
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
                                boolean successful = false;
                                
                                String newUsername = "";
                                String newPassword = "";
                                for (int i = 0; i < users.size(); i++)
                                {
                                    if (users.get(i).getUsername().equals(newUsername))
                                    {
                                        if (users.get(i).getPassword().equals(newPassword))
                                        {
                                            successful = true;
                                        }   
                                        break;
                                    }
                                }

                                NetworkMessage newMessage = 
                                    (successful == true) ? 
                                    new SuccessMessage(NetworkMessageType.Success) :
                                    new SuccessMessage(NetworkMessageType.Failure);
                                
                                outputMessages.add(newMessage);
                                break;
                            }
                            case CreateUser:
                            {
                                boolean successful = false;

                                String newUsername = "";
                                String newPassword = "";
                                for (int i = 0; i < users.size(); i++)
                                {
                                    if (users.get(i).getUsername().equals(newUsername))
                                    {
                                        users.add(new User(users.size(), newUsername, newPassword));
                                        successful = true;
                                        shouldQuit = true;
                                        break;
                                    }
                                }

                                NetworkMessage newMessage = 
                                    (successful == true) ? 
                                    new SuccessMessage(NetworkMessageType.Success) :
                                    new SuccessMessage(NetworkMessageType.Failure);
                                
                                outputMessages.add(newMessage);
                                break;
                            }
                            default:
                            {
                                break;
                            }
                        }
                    }
                }

                while (shouldQuit == false && hasLogin == true)
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

                            case Logout:
                            {
                                NetworkMessage logOutSucess = new SuccessMessage();
                                shouldQuit = true;
                                outputMessages.add(logOutSucess);
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
                                int userID = 0;

                                for (int i = 0; i < channels.size(); i++)
                                {
                                    if (channels.get(i).getName().equals(channel))
                                    {
                                        Message newMessage = new Message(channels.get(i).messages.size(), userID, message);
                                        channels.get(i).addMessage(newMessage);
                                        break;
                                    }

                                } 
                                break;
                            }
                            case HideMessage:
                            {
                                String message = "";
                                String channel = "";
                                int userID = 0;
                                int messageID = 0;

                                for (int i = 0; i < channels.size(); i++)
                                {
                                    if (channels.get(i).getName().equals(channel))
                                    {
                                        break;
                                    }

                                } 
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
        try {
            Files.createDirectory(Paths.get("channels"));
            Files.createDirectory(Paths.get("logs"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        serverLogs = new Logs();

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