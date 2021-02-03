import java.util.Stack;

public class Main {

    private static Stack<Point> points = new Stack<>();
    private static Point getPoint() {
        if (!points.empty()) {
            return points.pop();
        }
        return null;
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        Eliminator eliminator = new Eliminator();
        eliminator.setDistanceCalculator((a, b) -> {
            return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2) + Math.pow(b.z - a.z, 2));
        });

        points.add(new Point(0, 8, 20));
        points.add(new Point(4, 3, 3));
        points.add(new Point(2, 6, 40));
        points.add(new Point(3, 5, 30));
        points.add(new Point(4, 4, 30));
        points.add(new Point(5, 3, 30));
        points.add(new Point(6, 3, 20));
        points.add(new Point(7, 3, 10));
        points.add(new Point(2, 2, 2));
        points.add(new Point(80, 2, 10));
        points.add(new Point(80, 2, 10));
        points.add(new Point(80, 2, 10));

        eliminator.accept(Main::getPoint);

//        eliminator.accept(() -> new Point(0, 0, 0));
//        eliminator.accept(() -> new Point(10, 10, 1));
//        eliminator.accept(() -> new Point(2, 2, 2));
//        eliminator.accept(() -> new Point(3, 3, 3));
//        eliminator.accept(() -> new Point(4, 4, 4));
//        for (int i = 0; i < 20; i++) {
//            final int j = i;
//            eliminator.accept(() -> new Point(0, 0, j));
//        }

//        eliminator.accept(() -> null);



        
        System.out.println(eliminator.get());
        System.out.println(eliminator.get());
        System.out.println((long)(System.currentTimeMillis() - time));
    }
}
