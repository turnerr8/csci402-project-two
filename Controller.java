import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Controller {
    //variables
    private String bananaRoom;
    private String boxRoom;
    private String monkeyRoom;
    private Scanner in;
    private List<String> operationsList;
    private WorldState goalState;
    private WorldState initialState;

    //constructor
    public Controller(){
        in = new Scanner(System.in);
        operationsList = new ArrayList<String>();
    }

    public void Start(){

        //initial setup
        printLine();
        monkeyRoom = getRoom("monkey");
        boxRoom = getRoom("box");
        bananaRoom = getRoom("bananas");
        

        //create initial and goal worldstate
        goalState = new WorldState(bananaRoom, bananaRoom, bananaRoom, WorldState.HEIGHT_HIGH, true);
        initialState = new WorldState(monkeyRoom, boxRoom, bananaRoom, WorldState.HEIGHT_LOW, false);

        //call operate function
        opperation(initialState);

        printPlanTitle();
        //print out plan
        for(int i = 0; i < operationsList.size(); i++){
            System.out.println(operationsList.get(i));
        }
       
        printLine();
    }

    /*recursive function, checks whether states are equal, if not do a change by priority, if equal, return the moves
    priority
    1. monkey is not in same room as box
    2. box and monkey are not in same room as bananas
    3. monkey is low
    4. monkey does not have bananas
    */

    private WorldState opperation(WorldState worldState) {
        //base case: world state is same as goal
        if(worldState.sameState(goalState)) {
            return worldState;
        }
        //world states are not the same. do opperations in order of importance

        //monkey is not in same room as box, 
        if(!worldState.isMonkeyAt(worldState.getRoomBoxIn())){
            Move m = new Move(worldState.getRoomMonkeyIn(), worldState.getRoomBoxIn());
            if(m.checkPreconditions(worldState)) {
                WorldState nState = m.applyPostConditions(worldState);
                operationsList.add(m.getOpperator());
                return opperation(nState);
            }
        }

        //monkey is same room as box, box and monkey not in same room as bananas
        if(!worldState.isMonkeyAt(worldState.getRoomBananasIn())) {
            Push p = new Push(boxRoom, bananaRoom);
            if(p.checkPreconditions(worldState)){
                WorldState nState = p.applyPostConditions(worldState);
                operationsList.add(p.getOpperator());
                return opperation(nState);
            }
        }

        //monkey and box in same room as bananas, monkey is low
        if(!worldState.isMonkeyHeight(goalState.getMonkeyHeight())){
            ClimbUp c = new ClimbUp();
            if(c.checkPreconditions(worldState)){
                WorldState nState  = c.applyPostConditions(worldState);
                operationsList.add(c.getOpperator());
                return opperation(nState);
            }
        }

        //monkey, box, bananas in same room, monkey is high, monkey has not grabbed bananas
        if(worldState.getMonkeyHasBananas() != goalState.getMonkeyHasBananas()){
            Grab g = new Grab();
            if(g.checkPreconditions(worldState)){
                WorldState nState = g.applyPostConditions(worldState);
                operationsList.add(g.getOpperator());
                return opperation(nState);
            }
        }

        //should never reach this point
        return null;
    }

    private void printLine(){
        System.out.printf("\n===================================================================================\n\n");
    }

    private void printPlanTitle(){
        System.out.printf("\nPlan:\n");
    }

    private String getRoom(String object){
        boolean goodInput = false;
        int room = 0;

        System.out.printf("Select which room the %s starts in:\n[1] Room A\n[2] Room B\n[3] Room C\n", object);

        do {
            System.out.print("==> ");
            String input = in.next();
            if(isParsable(input) && Integer.parseInt(input)>0 && Integer.parseInt(input) < 4){
                room = Integer.parseInt(input);
                goodInput = true;
            } else {
                System.out.println("Bad input, try again.");
            }
        } while(!goodInput);

        return getRoom(room);

    }

    //checks if you can parse an int from input
    boolean isParsable (String str){
        try{
            Integer.parseInt(str);
            return true;
        } catch(final NumberFormatException e){
            return false;
        }
    }

    //takes in integer, outputs correct room string
    private String getRoom(int roomNum){
        switch(roomNum){
            case 1:
            return WorldState.ROOM_A;
            case 2:
            return WorldState.ROOM_B;
            case 3:
            return WorldState.ROOM_C;
            default:
            return null;
        }
    }
}
