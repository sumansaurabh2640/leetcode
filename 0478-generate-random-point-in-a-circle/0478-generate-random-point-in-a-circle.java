import java.util.*;

class Solution {
    private double radius, xCenter, yCenter;
    private Random random = new Random();

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.xCenter = x_center;
        this.yCenter = y_center;
    }

    public double[] randPoint() {
        double x, y;

        do {
            x = random.nextDouble() * 2 * radius - radius;
            y = random.nextDouble() * 2 * radius - radius;
        } while (x * x + y * y > radius * radius);

        return new double[]{xCenter + x, yCenter + y};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */