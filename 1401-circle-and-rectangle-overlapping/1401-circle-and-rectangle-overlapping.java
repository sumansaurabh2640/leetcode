class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter,
                                int x1, int y1, int x2, int y2) {

        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        long dx = closestX - xCenter;
        long dy = closestY - yCenter;

        return dx * dx + dy * dy <= (long) radius * radius;
    }
}

// class Solution {
//     public boolean checkOverlap(int radius, int xCenter, int yCenter,
//                                 int x1, int y1, int x2, int y2) {

//         // Find the closest point of the rectangle to the circle center
//         int closestX = Math.max(x1, Math.min(xCenter, x2));
//         int closestY = Math.max(y1, Math.min(yCenter, y2));

//         // Check whether the closest point lies inside/on the circle
//         long dx = closestX - xCenter;
//         long dy = closestY - yCenter;

//         return dx * dx + dy * dy <= (long) radius * radius;
//     }
// }