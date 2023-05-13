package MazeGameServer;

public class NPC { 
    double locationX, locationY, locationZ;
    double dir = 0.1;
    double size = 1.0;
    
    public NPC() { 
        locationX=0.0;
        locationY=0.0;
        locationZ=0.0;
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

    public void updateLocation() { 
        double centerX = 0.0;
        double centerZ = 0.0;
        double radius = 15.0;
    
        // Calculate new X and Z coordinates based on circle equation
        locationX = centerX + radius * Math.cos(dir);
        locationZ = centerZ + radius * Math.sin(dir);
    
        // Increment direction to continue moving in the circle
        dir += 0.05;
    } 
}