package MazeGameServer;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;

public class NPC { 
    double locationX, locationY, locationZ;
    double dir = 0.1;
    double size = 1.0;
    double centerX = 0.0;
    double centerZ = 0.0;
    double radius = 15.0;
    int currentPathIndex;
    ArrayList<double[]> path;

    public NPC() { 
        locationX=0.0;
        locationY=0.0;
        locationZ=0.0;
        path = new ArrayList<double[]>();
        currentPathIndex = 0;
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
        // If there is no path, generate a new one
        if (path == null || path.size() == 0) {
            generatePath();
        }
    
        // If we've reached the end of the path, generate a new one
        if (currentPathIndex >= path.size()) {
            generatePath();
            currentPathIndex = 0;
        }
    
        // Move towards the next point on the path
        double speed = 0.1;
        double[] nextPoint = path.get(currentPathIndex);
        double dx = nextPoint[0] - locationX;
        double dz = nextPoint[1] - locationZ;
        double distance = Math.sqrt(dx*dx + dz*dz);
        if (distance < speed) {
            // We've reached the next point on the path, move on to the next one
            currentPathIndex++;
            return;
        }
        double vx = dx/distance * speed;
        double vz = dz/distance * speed;
        locationX += vx;
        locationZ += vz;
    }
    
    private void generatePath() {
        // Generate a new path by selecting a random point within the radius
        double angle = Math.random() * 2 * Math.PI;
        double distance = Math.random() * 15.0;
        double x = centerX + distance * Math.sin(angle);
        double z = centerZ + distance * Math.cos(angle);
        
        // Generate a path to the selected point
        double[][] graph = new double[360][360];
        for (int i = 0; i < 360; i++) {
            for (int j = 0; j < 360; j++) {
                double angleDiff = Math.abs(i - j);
                angleDiff = Math.min(angleDiff, 360 - angleDiff);
                distance = Math.sin(Math.toRadians(angleDiff)) * radius * 2;
                graph[i][j] = distance;
            }
        }
    
        int startNode = (int) Math.round(locationX + 180) % 360;
        int endNode = (int) Math.round(Math.toDegrees(Math.atan2(x - centerX, z - centerZ)));
        if (endNode < 0) {
            endNode += 360;
        }
    
        double[] dist = new double[360];
        int[] prev = new int[360];
        boolean[] visited = new boolean[360];
    
        for (int i = 0; i < 360; i++) {
            dist[i] = Double.POSITIVE_INFINITY;
            prev[i] = -1;
        }
        dist[startNode] = 0;
    
        while (true) {
            double minDist = Double.POSITIVE_INFINITY;
            int nextNode = -1;
            for (int i = 0; i < 360; i++) {
                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    nextNode = i;
                }
            }
            if (nextNode == -1) {
                break;
            }
    
            visited[nextNode] = true;
    
            for (int i = 0; i < 360; i++) {
                double alt = dist[nextNode] + graph[nextNode][i];
                if (alt < dist[i]) {
                    dist[i] = alt;
                    prev[i] = nextNode;
                }
            }
        }
    
        // Build the path by following the parent pointers from the end node
        path = new ArrayList<double[]>();
        int currentNode = endNode;
        while (currentNode != -1) {
            angle = Math.toRadians(currentNode);
            double x2 = centerX + radius * Math.sin(angle);
            double z2 = centerZ + radius * Math.cos(angle);
            path.add(new double[] {x2, z2});
            currentNode = prev[currentNode];
        }
        Collections.reverse(path);
    }
    
}