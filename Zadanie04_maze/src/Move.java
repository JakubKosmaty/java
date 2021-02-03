public class Move implements MoveInterface {

    public int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1},
            {3, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1}
    };

    int x = 1;
    int y = 1;

    @Override
    public void move(Direction dir) throws RoomOnFireException, WallException, HereIsTheFinishException {
        int saveX = x;
        int saveY = y;

        if (dir == Direction.EAST) {
            x++;
        } else if (dir == Direction.WEST) {
            x--;
        } else if (dir == Direction.NORTH) {
            y++;
        } else if (dir == Direction.SOUTH) {
            y--;
        }

        if (maze[y][x] == 1) {
            x = saveX;
            y = saveY;
            throw new WallException();
        } else if (maze[y][x] == 0) {
            return;
        } else if (maze[y][x] == 3) {
            System.out.println("WON!!!!!!!!!!!!!");
            throw new HereIsTheFinishException();
        }


    }

    @Override
    public void useFireExtinguisher() throws FireExtinguisherIsWornOutException {

    }
}
