import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Eliminator implements PointEliminator {
    private final List<Point> pointList = new ArrayList<>();
    private final Point[] points = new Point[4];

    private BiFunction<Point, Point, Double> distance;

    Point pointResult1 = null;
    Point pointResult2 = null;

    void calcPoints(int id, int from, int to, BiFunction<Point, Point, Double> distance, Point point) {
        Point minPoint = null;
        double minDistance = Double.MAX_VALUE;
        for (int i = from; i < to; i++) {
            double tempDistance = distance.apply(point, pointList.get(i));
            if (tempDistance < minDistance) {
                minDistance = tempDistance;
                minPoint = pointList.get(i);
            }
        }

        points[id] = minPoint;
    }

    private void elminatePoints() {
        double minDistance = Double.MAX_VALUE;

        Thread thread_1 = null;
        Thread thread_2 = null;
        Thread thread_3 = null;
        Thread thread_4 = null;

        int arraySize = pointList.size();
        for (int i = 1; i < arraySize; i++) {
            int j = i;
            int currentSize = arraySize - i;
            Point tempPoint = pointList.get(i - 1);
            thread_1 = new Thread(() -> calcPoints(0, j, currentSize / 4 + j, distance, tempPoint));
            thread_1.start();

//            System.out.printf("Index: %d\n", i - 1);
//            System.out.printf("From: %d | To: %d\n", j, currentSize / 4 + j);

            thread_2 = new Thread(() -> calcPoints(1, currentSize / 4 + j, currentSize / 2 + j, distance, tempPoint));
            thread_2.start();

//            System.out.printf("From: %d | To: %d\n", currentSize / 4 + j, currentSize / 2 + j);

            thread_3 = new Thread(() -> calcPoints(2, currentSize / 2 + j, currentSize / 2 + currentSize / 4 + j, distance, tempPoint));
            thread_3.start();

//            System.out.printf("From: %d | To: %d\n", currentSize / 2 + j, currentSize / 2 + currentSize / 4 + j);

            thread_4 = new Thread(() -> calcPoints(3, currentSize / 2 + currentSize / 4 + j, arraySize, distance, tempPoint));
            thread_4.start();

//            System.out.printf("From: %d | To: %d\n", currentSize / 2 + currentSize / 4 + j, arraySize);
//            System.out.println("----------------------------------------------------------");

            try {
                thread_1.join();
                thread_2.join();
                thread_3.join();
                thread_4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int x = 0; x < 4; x++) {
                if (points[x] == null) {
                    continue;
                }

                double dist = distance.apply(tempPoint, points[x]);
                if (dist < minDistance) {
                    minDistance = dist;
                    pointResult1 = points[x];
                    pointResult2 = tempPoint;
                }
            }
        }
    }

    @Override
    public void setDistanceCalculator(BiFunction<Point, Point, Double> distance) {
        this.distance = distance;
    }

    @Override
    public void accept(Supplier<Point> pointSupplier) {
        Point point = pointSupplier.get();
        while (point != null) {
            pointList.add(point);
            point = pointSupplier.get();
        }
        elminatePoints();
    }

    @Override
    public Point get() {
        if (pointList.isEmpty()) {
            return null;
        }
        Point point = pointList.remove(pointList.size() - 1);
        if (point.equals(pointResult1) || point.equals(pointResult2)) {
            return pointList.remove(pointList.size() - 1);
        }
        return point;
    }
}
