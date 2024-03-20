import java.util.List;

public class SkiLift {
    private String name;
    private int number;
    private double averageSpeed;
    private String liftType;
    
    // Constructor
    public SkiLift(String name, int number, double averageSpeed, String liftType) {
        this.name = name;
        this.number = number;
        this.averageSpeed = averageSpeed;
        this.liftType = liftType;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public String getLiftType() {
        return liftType;
    }

    public void setLiftType(String liftType) {
        this.liftType = liftType;
    }
    
    // Method to determine if the user is riding the ski lift
    public boolean isUserRidingSkiLift(List<TrackPoint> trackPoints) {
        if (trackPoints.size() < 2) {
            return false; // Not enough data, now what the appropriate threshold for this is, not yet clear.
        }

        // Thresholds to determine movement of ski lift, need to test with data to determine appropriate values
        double altitudeChangeThreshold = 10.0;
        double speedThreshold = 2.0;

        // Get the first and last track points
        TrackPoint firstPoint = trackPoints.get(0);
        TrackPoint lastPoint = trackPoints.get(trackPoints.size() - 1);

        // Check if altitude has increased significantly
        double altitudeChange = lastPoint.getAltitude() - firstPoint.getAltitude();
        if (altitudeChange < altitudeChangeThreshold) {
            return false; // Altitude change is not significant
        }

        // Calculate total distance
        double totalDistance = calculateTotalDistance(trackPoints);

        // Calculate total time
        double totalTime = calculateTotalTime(trackPoints);

        // Calculate average speed
        double averageSpeed = totalDistance / totalTime;

        // Check if average speed is below a certain threshold
        if (averageSpeed > speedThreshold) {
            return false; // Average speed is too high
        }

        // If both conditions are met, user is riding the ski lift
        return true;
    }

    // Helper method to calculate total distance covered
    private double calculateTotalDistance(List<TrackPoint> trackPoints) {
        double totalDistance = 0.0;
        return totalDistance;
    }

    // Helper method to calculate total time duration
    private double calculateTotalTime(List<TrackPoint> trackPoints) {
        return 0.0; // Need to think this out more lol
    }
}
