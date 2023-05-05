package MazeGameServer;

import tage.ai.behaviortrees.BTCondition;

public class GetBig extends BTCondition { 
    NPC npc;
    NPCcontroller npcc;
    GameServerUDP server;

    public GetBig(NPC n) { 
        super(false);
        npc = n;
    }

    protected boolean check() { 
        // server.sendCheckForAvatarNear();
        return npcc.getBigFlag();
    } 
}