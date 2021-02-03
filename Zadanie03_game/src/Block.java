public class Block implements PhysicsInterface {
    private boolean mustNeighbor;
    private boolean canFly;
    private boolean willFall;

    public void setPhysics(boolean mustNeighbor, boolean canFly, boolean willFall) {
        this.mustNeighbor = mustNeighbor;
        this.canFly = canFly;
        this.willFall = willFall;
    }

    @Override
    public boolean mustBeSupportedFromAnySideToBePlaced() {
        return mustNeighbor;
    }

    @Override
    public boolean canFloatInTheAirWithoutSupport() {
        return canFly;
    }

    @Override
    public boolean willFallIfNotSupportedFromBelow() {
        return willFall;
    }
}
