package MazeGameServer;

import java.io.IOException;
import tage.networking.IGameConnection.ProtocolType;

public class NetworkingServer 
{
	private GameServerUDP thisUDPServer;
	private GameServerTCP thisTCPServer;
	private NPCcontroller npcCtrl;

	public NetworkingServer(int serverPort, String protocol) 
	{	
		try 
		{	if(protocol.toUpperCase().compareTo("TCP") == 0)
		{	thisTCPServer = new GameServerTCP(serverPort, npcCtrl);
		} else {	
			npcCtrl = new NPCcontroller();
			thisUDPServer = new GameServerUDP(serverPort, npcCtrl);
			npcCtrl.start(thisUDPServer);
		}
		} 
		catch (IOException e) 
		{	e.printStackTrace();
		}

	}

	public static void main(String[] args) 
	{	if(args.length > 1)
		{	NetworkingServer app = new NetworkingServer(Integer.parseInt(args[0]), args[1]);
		}
	}

}
