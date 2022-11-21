public class Push {

    public String operatorName = "PUSH";
    public String moveFrom;
    public String moveTo;

    //constructor
    public Push(String from, String to){
        this.moveFrom = from;
        this.moveTo = to;
    }

    public boolean checkPreconditions(WorldState worldState){
        if(!worldState.isMonkeyAt(moveFrom) || !worldState.isBoxAt(moveFrom) || !worldState.isMonkeyHeight(WorldState.HEIGHT_LOW)){
            return false;
        }
        return true;
    }

    public WorldState applyPostConditions(WorldState worldState){
        return new WorldState(moveTo, moveTo, worldState.getRoomBananasIn(), worldState.getMonkeyHeight(), worldState.getMonkeyHasBananas());
    }

    public String getOpperator(){
        return this.operatorName+"("+moveFrom+","+moveTo+")";
    }
}
