public class Move {
    //vars
    private String operatorName = "MOVE";
    private String moveFrom;
    private String moveTo;

    //constructor
    public Move(String from, String to){
        this.moveFrom = from;
        this.moveTo = to;
    }

    public boolean checkPreconditions(WorldState worldState){
        if(!worldState.isMonkeyAt(moveFrom)){
            return false;
        }
        if(!worldState.isMonkeyHeight(WorldState.HEIGHT_LOW)){
            return false;
        }
        return true;
    }

    public WorldState applyPostConditions(WorldState worldState){
        return new WorldState(moveTo, worldState.getRoomBoxIn(), worldState.getRoomBananasIn(), worldState.getMonkeyHeight(), worldState.getMonkeyHasBananas());
    }

    public String getOpperator(){
        return this.operatorName+"("+moveFrom+","+moveTo+")";
    }


}
