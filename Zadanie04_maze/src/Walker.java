import java.util.HashMap;
import java.util.Stack;

class Pair {
    public final int x;
    public final int y;

    public Pair(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair)) {
            return false;
        }

        final Pair pair = (Pair) o;

        if (x != pair.x) {
            return false;
        }
        if (y != pair.y) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}

public class Walker implements WalkerInterface {
    private HashMap<Pair, Integer> maze = new HashMap<>();
    private Stack<Direction> currentPath = new Stack<>();

    private Pair addToPair(Pair pair, final int x, final int y) {
        return new Pair(pair.x + x, pair.y + y);
    }

    private Direction oppositeDirection(Direction direction) {
        Direction temp = null;
         switch (direction) {
             case EAST: temp = Direction.WEST; break;
             case WEST: temp = Direction.EAST; break;
             case NORTH: temp = Direction.SOUTH; break;
             case SOUTH: temp = Direction.NORTH; break;
        };
         return temp;
    }

    private Pair addDirToPos(Pair pair, Direction dir) {
        Pair temp = null;
        switch (dir) {
            case EAST: temp = addToPair(pair, 0, 1); break;
            case WEST: temp = addToPair(pair, 0, -1); break;
            case NORTH: temp = addToPair(pair, 1, 0); break;
            case SOUTH: temp = addToPair(pair, -1, 0); break;
        };
        return temp;
    }

    @Override
    public void setInterfaceAndStartWalk(MoveInterface moveInterface) {
        maze.put(new Pair(0, 0), 0);

        boolean usedFireExtinguisher = false;

        Direction nextMove = null;
        Pair currentPos = new Pair(0, 0);
        Pair nextPos = null;
        boolean returned = false;
        while (true) {
            try {
                returned = false;
                if (maze.get(addToPair(currentPos, 0, 1)) == null) {
                    nextMove = Direction.EAST;
                } else if (maze.get(addToPair(currentPos, 0, -1)) == null) {
                    nextMove = Direction.WEST;
                } else if (maze.get(addToPair(currentPos, 1, 0)) == null) {
                    nextMove = Direction.NORTH;
                } else if (maze.get(addToPair(currentPos, -1, 0)) == null) {
                    nextMove = Direction.SOUTH;
                } else {
                    returned = true;
                    nextMove = oppositeDirection(currentPath.pop());
                }

                nextPos = addDirToPos(currentPos, nextMove);
                moveInterface.move(nextMove);
            } catch (RoomOnFireException e) {
                if (usedFireExtinguisher) {
                    maze.put(nextPos, 1);
                    continue;
                } else {
                    try {
                        moveInterface.useFireExtinguisher();
                        usedFireExtinguisher = true;
                        continue;
                    } catch (FireExtinguisherIsWornOutException eFire) {

                    }
                }
            } catch (WallException e) {
                 maze.put(nextPos, 2);
                 continue;
            } catch (HereIsTheFinishException e) {
                break;
            }
            currentPos = nextPos;
            maze.put(nextPos, 0);
            if (!returned) {
                currentPath.push(nextMove);
            }
        }
    }
}
