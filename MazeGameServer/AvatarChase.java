package MazeGameServer;

import tage.ai.behaviortrees.BTBehavior;
import tage.ai.behaviortrees.BTStatus;
import org.joml.Vector3f;

public class AvatarChase extends BTBehavior {
    NPCcontroller npcc;
    NPC npc;
    GameServerUDP server;
    
    public AvatarChase(GameServerUDP s, NPCcontroller c, NPC n) {
        server = s; npcc = c; npc = n;
    }
    
    @Override
    protected BTStatus update(float elapsedTime) {
        server.sendCheckForAvatarNear();
        boolean isNear = npcc.getNearFlag();
        if (isNear) {
            // Avatar is near, move towards it
            System.out.println("Is Chasing ===================================");
            return BTStatus.BH_SUCCESS;
        } else {
            return BTStatus.BH_FAILURE;
        }
    }
}
