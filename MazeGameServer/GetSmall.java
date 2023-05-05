package MazeGameServer;

import tage.ai.behaviortrees.BTCondition;

public class GetSmall extends BTCondition { 
    NPC npc;
    NPCcontroller npcc;
    GameServerUDP server;
    
    public GetSmall(NPC n) { 
        super(false);
        // server = s; npcc = c; npc = n;
        npc = n;
    }

    protected boolean check() { 
        // server.sendCheckForAvatarNear();
        return npcc.getSmallFlag();
    } 
}