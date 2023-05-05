package MazeGameServer;

import tage.ai.behaviortrees.BTCondition;

public class OneSecPassed extends BTCondition { 
    NPC npc;
    NPCcontroller npcc;
    GameServerUDP server;

    public OneSecPassed(NPCcontroller npcc, NPC n, boolean toNegate) { 
        super(toNegate);
        this.npcc = npcc; npc = n;
    }


    protected boolean check() { 
        // return false;
        // server.sendCheckForAvatarNear();
        return npcc.getOneSecPassedFlag();
    } 
}
