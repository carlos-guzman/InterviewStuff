import java.util.ArrayList;

public class State {
    String vocab = "FWCG";//Farmer is separate
    ArrayList<Character> left;
    boolean farmer;
    State fromState = null;

    public State(String leftStr){
        this(new ArrayList(){{for(char c : leftStr.toCharArray())if(c!='F')add(c);}}, leftStr.contains("F"));
    }

    public State(ArrayList left, boolean farmer){
        this(left, farmer, null);
    }

    public State(ArrayList left, boolean farmer, State fromState){
        this.left = left;
        this.farmer = farmer;
        this.fromState = fromState;
    }

    public boolean isValid(){
        return !((farmer && ((!left.contains('W') && !left.contains('C')) || (!left.contains('C') && !left.contains('G')))) ||
                (!farmer && ((left.contains('W') && left.contains('C')) || (left.contains('C') && left.contains('G')))));
    }

    public State move(Character c){
        State newState = new State((ArrayList)left.clone(), farmer);
        if(c != 'F') {
            if (newState.farmer) {
                if (!newState.left.contains(c)) {
                    return null;
                }
                newState.left.remove(c);
            } else {
                if (newState.left.contains(c)) {
                    return null;
                }
                newState.left.add(c);
            }
        }
        newState.farmer = !newState.farmer;

        return newState;
    }

    public ArrayList<State> getNextStates(){
        ArrayList result= new ArrayList<State>();
        State tmp;
        if(farmer){
            for(Character c : (ArrayList<Character>)left.clone()){
                tmp = move(c);
                if(tmp != null && tmp.isValid()){
                    result.add(tmp);
                }
            }
        }else{
            for(Character c : vocab.toCharArray()){
                if(left.contains(c)) continue;
                tmp = move(c);
                if(tmp != null && tmp.isValid()){
                    result.add(tmp);
                }
            }
        }
        return result;
    }

    public boolean isFinal(){
        if(left.isEmpty() && !farmer){
            return true;
        }
        return false;
    }

    public String toString(){
        String result = "";
        for(char c : vocab.toCharArray()){
            if((c=='F' && farmer) || left.contains(c)){
                result += c;
            }
        }
        result += " | ";

        for(char c : vocab.toCharArray()){
            if(!result.contains(c+"")){
                result += c;
            }
        }
        return result;
    }
}
