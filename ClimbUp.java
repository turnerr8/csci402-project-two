public class ClimbUp {
    private String operatorName = "CLIMB UP";

    
    public boolean checkPreconditions(WorldState worldState){
        if(!worldState.isMonkeyHeight(WorldState.HEIGHT_LOW) || !worldState.isMonkeyAt(worldState.getRoomBoxIn())) {
            return false;
        }
        return true;
    }

    public WorldState applyPostConditions(WorldState worldState){
        return new WorldState(worldState.getRoomMonkeyIn(), worldState.getRoomBoxIn(), worldState.getRoomBananasIn(), WorldState.HEIGHT_HIGH, worldState.getMonkeyHasBananas());
    }

    public String getOpperator(){
        return this.operatorName+"()";
    }
}
