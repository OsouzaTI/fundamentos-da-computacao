package regular_expression;

import java.util.ArrayList;
import java.util.Optional;

public class State {

    public static int NUM_STATES = 0;
    public static int STATE = 0; // estado inicial

    private int state;  
    private ArrayList<Transition> transitions = new ArrayList<>();
    private Boolean finalState;

    public State(int state, Transition[] transition){
        this.state = state;
        this.finalState = false;
        for(int T = 0; T < transition.length; T++){
            transitions.add(transition[T]);
        }
        State.NUM_STATES++;
    }

    public State(int state, Object[] transition) {
        this.state = state;
        this.finalState = false;
        for(int T = 0; T < transition.length; T++){
            transitions.add((Transition)transition[T]);
        }
        State.NUM_STATES++;
    }

    public State(int state, Transition[] transition, Boolean finalState){
        this.state = state;
        this.finalState = finalState;
        for(int T = 0; T < transition.length; T++){
            transitions.add(transition[T]);
        }
        State.NUM_STATES++;
    }

    public Transition getTransition(Character c){
        Optional<Transition> t = transitions.stream()
                            .filter(transition -> transition.getLabel() == c)
                            .findFirst();
        return !t.isPresent() ? null : t.get();
    }

    public boolean existsEpslonTransition(){
        Optional<Transition> t = transitions.stream()
                            .filter(transition -> transition.isEpslon())   
                            .findFirst();
        return !t.isPresent() ? false : true;
    }

    public Transition findFirstEpslonTransition(){
        Optional<Transition> t = transitions.stream()
                            .filter(transition -> transition.isEpslon())   
                            .findFirst();
        return !t.isPresent() ? null : t.get();
    }

    public static void changeState(int newState){
        State.STATE = newState;
    }

    public int getState(){
        return state;
    }

    public ArrayList<Transition> getTransitions(){
        return transitions;
    }

    public Boolean isFinalState(){
        return finalState;
    }

    public void setTransitions(Transition[] transition){
        transitions.clear();
        for(int T = 0; T < transition.length; T++){
            transitions.add(transition[T]);
        }
    }

}
