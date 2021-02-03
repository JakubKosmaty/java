public class StrangeWorld implements StrangeWorldInterface{
    private PhysicsInterface[] physicsInterface;
    private int mapSize;

    @Override
    public void setSize(int size) {
        if (size <= 0) {
            return;
        }

        mapSize = size;
        physicsInterface = new PhysicsInterface[mapSize * mapSize];
    }

    private boolean fallDown(int row, int col) {
        int blockIndex = getIndex(row, col);
        boolean hasFall = false;

        while (row >= 0) {
            blockIndex = getIndex(row, col);
            PhysicsInterface block = physicsInterface[blockIndex];
            if (block.canFloatInTheAirWithoutSupport() && !block.willFallIfNotSupportedFromBelow()) {
                return false;
            }

            if (block.willFallIfNotSupportedFromBelow()) {
                if (row == 0) {
                    physicsInterface[blockIndex] = null;
                    return true;
                }

                if (physicsInterface[getIndex(row - 1, col)] != null) {
                    return hasFall;
                }

            } else if (blockHasNeighbor(row, col)) {
                return hasFall;
            }

            if (row == 0) {
                physicsInterface[blockIndex] = null;
                return hasFall;
            }

            hasFall = true;
            physicsInterface[getIndex(row - 1, col)] = physicsInterface[blockIndex];
            physicsInterface[blockIndex] = null;
            row -= 1;
        }
        return hasFall;
    }

    // 0 - Right 1 - Left 2 - Up 3 - Down
    private int blockGetNeighbor(int dir, int row, int col) {
        int index = -1;
        int dRow = 0;
        int dCol = 0;
        if (dir == 0) {
            dCol = 1;
        } else if (dir == 1) {
            dCol = -1;
        } else if (dir == 2) {
            dRow = 1;
        } else if (dir == 3) {
            dRow = -1;
        } else {
            return -1;
        }

        int neighbourRow = row + dRow;
        int neighbourCol = col + dCol;
        if (neighbourRow >= mapSize || neighbourRow < 0 || neighbourCol >= mapSize || neighbourCol < 0) {
            return -1;
        }

        return getIndex(neighbourRow, neighbourCol);
    }

    private boolean blockHasNeighbor(int row, int col) {
        for (int i = 0; i < 4; i++) {
            int index = blockGetNeighbor(i, row, col);
            if (index != -1 && !isIndexAvailable(index)) {
                return true;
            }
        }
        return false;
    }

    private void checkBlock(int row, int col) {
        for (int i = 0; i < 4; i++) {
            int neighborIndex = blockGetNeighbor(i, row, col);
            if (neighborIndex != -1 && !isIndexAvailable(neighborIndex)) {
                if (fallDown(getRow(neighborIndex), getCol(neighborIndex))) {
                    checkBlock(getRow(neighborIndex), getCol(neighborIndex));
                    break;
                }
            }
        }
    }

    @Override
    public boolean tryAdd(int row, int col, PhysicsInterface block) {
        int index = getIndex(row, col);
        if (!isIndexAvailable(index)) {
            return false;
        }

        if (block.mustBeSupportedFromAnySideToBePlaced()) {
           if (!blockHasNeighbor(row, col)) {
               return false;
           }
        }

        if (block.canFloatInTheAirWithoutSupport() && !block.willFallIfNotSupportedFromBelow()) {
            physicsInterface[index] = block;
            return true;
        }

        physicsInterface[index] = block;
        fallDown(row, col);
        return true;
    }

    @Override
    public PhysicsInterface delAndGet(int row, int col) {
        int index = getIndex(row, col);
        if (isIndexAvailable(index)) {
            return null;
        }

        PhysicsInterface block = physicsInterface[index];
        physicsInterface[index] = null;
        checkBlock(row, col);
        return block;
    }

    @Override
    public PhysicsInterface get(int row, int col) {
        return physicsInterface[getIndex(row, col)];
    }

    private boolean isIndexAvailable(int index) {
        return (physicsInterface[index] == null);
    }

    private int getRow(int index) {
        return (int)index / mapSize;
    }

    private int getCol(int index) {
        return (int)index % mapSize;
    }

    private int getIndex(int row, int col) {
        return row * mapSize + col;
    }

    public void printMap() {
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                int index = x * mapSize + y;
                if (physicsInterface[index] == null) {
                    System.out.print("0");
                } else {
                    System.out.print("1");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }
}
