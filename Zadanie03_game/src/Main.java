public class Main {
    public static void main(String[] args) {
        StrangeWorld strangeWorld = new StrangeWorld();
        strangeWorld.setSize(5);

        Block block = new Block();

        block.setPhysics(false, true, false);
        strangeWorld.tryAdd(0, 0, block);

        block.setPhysics(false, false, true);
        strangeWorld.tryAdd(1, 0, block);

        block.setPhysics(false, false, false);
        strangeWorld.tryAdd(1, 1, block);

        strangeWorld.printMap();
        //strangeWorld.delAndGet(0, 0);
        strangeWorld.delAndGet(1, 0);

        strangeWorld.printMap();
    }
}
