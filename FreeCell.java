import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Driver class for the Freecell game.
 *
 * @author Justin Montagne
 * @version 2/15/23
 */


public class FreeCell
{
    

    public static void main(String[] args) throws FileNotFoundException {
        GameState state = new GameState("ExampleGames/case_MS25_102.txt");
        ArrayList<Action> solution;

        solution = solve(state);

        for(int i = 0; i < solution.size(); i++){
            System.out.println("\n"+ (i+1) + ": " + solution.get(i).toDisplayString());
        }
        
    }

    

    /*
     * Added by Justin Montagne
     * 
     * Method to call the algorithm 
     */
    public static ArrayList<Action> solve (GameState gs){

        ArrayList<Action> actions = new ArrayList<Action>(); // Arraylist for the actions the game needs to take in order to solve 

        //Run the algorithm
        Algorithm s = new Algorithm(gs);
        actions = s.a();

        
        return actions;

    }
}

    

    /*
     * 
     * 
     * Function to return a tree of the actions possible throughout the game
     *
    public ArrayList<ArrayList<Node>> getStates(int parent, GameState gs){
        if(gs.gameover()){
            return list;
        }
        else{
            ArrayList<Action> curActions = gs.getLegalActions();
            for(int i = 0; i < curActions.size(); i++){
                GameState newState = gs.nextState(curActions.get(i));
                list.get(parent).add(new Node(i,newState.h() + newState.listOfActions.size(),newState));
                numVerts++;
                getStates(i, newState);
            }
        }
         

        return list;
    }
}
*/

    