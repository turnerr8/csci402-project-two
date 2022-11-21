public class Grab {
    private String operatorName = "GRAB";

    public boolean checkPreconditions(WorldState worldState){
        if(!worldState.isMonkeyAt(worldState.getRoomBananasIn()) || ! worldState.isMonkeyHeight(WorldState.HEIGHT_HIGH)){
            return false;
        }
        return true;
    }

    public WorldState applyPostConditions(WorldState worldState){
        return new WorldState(worldState.getRoomMonkeyIn(), worldState.getRoomBoxIn(), worldState.getRoomBananasIn(), worldState.getMonkeyHeight(), true);
    }

    public String getOpperator(){
        return this.operatorName+"()";
    }

}
