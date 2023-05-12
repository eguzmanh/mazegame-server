package MazeGameServer;

import org.joml.Vector3f;

import tage.rml.Vector3;

public class NPC { 
    double locationX, locationY, locationZ;
    double dir = 0.1;
    double size = 1.0;
    boolean seePlyr = false, seeGhst = false;
    float speed = 0.01f;
    Vector3f npcLocation, targetLocAv, targetLocGhost;
    
    public NPC() { 
        locationX=0.0;
        locationY=0.0;
        locationZ=0.0;
        npcLocation = new Vector3f();
    }

    public void randomizeLocation(int seedX, int seedZ) { 
        locationX = ((double)seedX)/4.0 - 5.0;
        locationY = 0;
        locationZ = -2;
    }
    
    public double getX() { return locationX; }
    public double getY() { return locationY; }
    public double getZ() { return locationZ; }

    public void getBig() { size=2.0; }
    public void getSmall() { size=1.0; }
    public double getSize() { return size; }

    public void setTargetLocationAvatar(Vector3f plyrPos){ targetLocAv = plyrPos; }
    public void setTargetLocationGhost(Vector3f ghostPos){ targetLocGhost = ghostPos; }

    public void setSeePlyr(boolean b){ seePlyr = b; }
    public void setSeeGhost(boolean b){ seeGhst = b; }

    public void updateLocation() { 
        if(seeGhst || seePlyr){
            if(seePlyr){
                Vector3f direction = new Vector3f();
                targetLocAv.sub(npcLocation, direction).normalize();
                direction.mul(speed);
                npcLocation.add(direction);
            } else if (seeGhst){
                Vector3f direction = new Vector3f();
                targetLocGhost.sub(npcLocation, direction).normalize();
                direction.mul(speed);
                npcLocation.add(direction);
            }
        } else if(seePlyr == false && seeGhst == false){
            if (locationX > 10) dir=-0.1;
            if (locationX < -10) dir=0.1;
            locationX = locationX + dir;
        }
    } 
}