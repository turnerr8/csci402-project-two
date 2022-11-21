
public class WorldState {
    
    //variables
    private String roomMonkeyIn;
    private String roomBoxIn;
    private String roomBananasIn;
    private String monkeyHeight;
    private boolean monkeyHasBananas;

    public static final String ROOM_A = "A";
    public static final String ROOM_B = "B";
    public static final String ROOM_C = "C";
    public static final String HEIGHT_LOW = "LOW";
    public static final String HEIGHT_HIGH = "HIGH";

    //constructor
    public WorldState(String monkeyRoom, String boxRoom, String bananaRoom, String monkeyHight, boolean mHasBananas){
        this.roomMonkeyIn = monkeyRoom.toUpperCase();
        this.roomBoxIn = boxRoom.toUpperCase();
        this.roomBananasIn = bananaRoom.toUpperCase();
        this.monkeyHeight = monkeyHight.toUpperCase();
        this.monkeyHasBananas = mHasBananas;
    }

    //print worldstate
    public void print(){
        System.out.printf("roomMonkeyIn: %s, roomBoxIn: %s, roomBananasIn: %s, monkeyHight: %s, monkeyHasBanana %b\n", this.roomMonkeyIn, this.roomBoxIn, this.roomBananasIn, this.monkeyHeight, this.monkeyHasBananas);
    }

    //gets
    public String getRoomMonkeyIn(){
        return this.roomMonkeyIn;
    }

    public String getRoomBoxIn(){
        return this.roomBoxIn;
    }

    public String getRoomBananasIn(){
        return this.roomBananasIn;
    }

    public String getMonkeyHeight(){
        return this.monkeyHeight;
    }

    public boolean getMonkeyHasBananas(){
        return this.monkeyHasBananas;
    }

    //testers
    public boolean isMonkeyAt(String room){
        return this.roomMonkeyIn.equalsIgnoreCase(room);
    }

    public boolean isBoxAt(String room){
        return this.roomBoxIn.equalsIgnoreCase(room);
    }

    public boolean isBananasIn(String room){
        return this.roomBananasIn.equalsIgnoreCase(room);
    }

    public boolean isMonkeyHeight(String height){
        return this.monkeyHeight.equalsIgnoreCase(height);
    }

    //compare passed in worldstate to see if it is exact same state
    public boolean sameState(WorldState curState){
        if(!isMonkeyAt(curState.getRoomMonkeyIn())) {
            return false;
        }
        if (!isBoxAt(curState.getRoomBoxIn())) {
            return false;
        }
        if(!isBananasIn(curState.getRoomBananasIn())){
            return false;
        }
        if(!isMonkeyHeight(curState.getMonkeyHeight())){
            return false;
        }
        if(this.getMonkeyHasBananas() != curState.getMonkeyHasBananas()){
            return false;
        }
        return true;
    }

}
