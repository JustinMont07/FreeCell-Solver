import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * An A* algorithm to find a list of actions to take from a starting GameState until a win, or if no solution is possible, 
 * return an empty ArrayList
 *
 * @author Justin Montagne
 * @version 2/15/23
 */

public class Algorithm {
    
    private PriorityQueue<Node> pq;
    private GameState startState;
    private ArrayList<GameState> visitedStates = new ArrayList<GameState>();

    public Algorithm(GameState startState){

        this.startState = startState;
        pq = new PriorityQueue<Node>(new Node());
        
    }

    public ArrayList<Action> a(){
        GameState curState;
        GameState newState;
        ArrayList<Action> children;
        boolean skip = false;
        
        pq.add(new Node(startState.h(), startState));
        while(!pq.isEmpty()){
           
            Node cur = pq.remove();
            curState = cur.gs;
          

            visitedStates.add(curState);
            if(curState.isWin()){
                System.out.println(curState.toDisplayString());
                return curState.listOfActions;
            }

            //Find all neighbors of this node
            children = curState.getLegalActions();
            
            for(int i = 0; i < children.size(); i++){
                skip = false;
                newState = curState.nextState(children.get(i));
                newState.listOfActions.addAll(curState.listOfActions);
                newState.listOfActions.add(children.get(i));
                
                for(int j = 0; j < visitedStates.size(); j++){ 
                    if(visitedStates.get(j).stateEquals(newState)){     //Go through visited states to see if this is repeat, if so set skip to true
                        skip = true;
                        break;
                    } 
                    
                }
                 
                if(skip){   //Continue to the next part of this loop if skip is true so we dont add repeat nodes
                    continue;
                }
                
                    pq.add(new Node(newState.h() + newState.listOfActions.size(), newState));
                
                
            }
        }

        //Return an empty ArrayList if we didn't find a winner
        
        return new ArrayList<Action>();
        
    }
    
}



/*
     * Class for a node that will include the score of the move and a comparator to be used to find the best score
     */
    class Node implements Comparator<Node> {
 
       
        
        public int score;
        public GameState gs;
     
        
        public Node() {}
     
    
        public Node(int score, GameState gs)
        {
     
            
            
            this.score = score;
            this.gs = gs;
        }
    
     
        @Override public int compare(Node node1, Node node2)
        {
     
            if (node1.score < node2.score)
                return -1;
     
            if (node1.score > node2.score)
                return 1;
     
            return 0;
        }
}
