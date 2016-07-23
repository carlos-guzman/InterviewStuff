import java.util.ArrayList;

public class Main {
    public static ArrayList<State> path = new ArrayList<>();
    public static void main(String[] args){
        State initialState = new State("FWCG");
        ArrayList<State> toVisit = new ArrayList<>();
        ArrayList<String> visited = new ArrayList<>();
        toVisit.add(initialState);
        if(bfs(toVisit, visited, null)){
            System.out.println("Path found:");
            for(int i=path.size()-1; i>=0; i--){
                System.out.println(path.get(i));
            }
        }else{
            System.out.println("Path not found");
        }
    }

    public static boolean bfs(ArrayList<State> toVisit, ArrayList visited, State fromState){
        if (toVisit.isEmpty()) return false;
        State current = toVisit.remove(0);
        visited.add(current.toString());
        for(State s : current.getNextStates()){
            if(!visited.contains(s.toString())) {
                s.fromState = current;
                toVisit.add(s);
            }
        }

        if (current.isFinal()){
            State tmp = current;
            while(tmp != null){
                path.add(tmp);
                tmp = tmp.fromState;
            }
            return true;
        }
        if (bfs(toVisit, visited, current)){
            return true;
        }
        return false;

    }
}
