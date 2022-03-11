package regular_expression;

public class Transition {

    private int transition;
    private Character label;
    private Boolean epslon;

    public Transition(int transition, Character label){
        this.transition = transition;
        this.label = label;
        this.epslon = false;
    }

    public Transition(int transition){
        this.transition = transition;
        this.label = '$';
        this.epslon = true;
    }

    public int getStateTransition(){
        return transition;
    }
    
    public Character getLabel(){
        return label;
    }

    public Boolean isEpslon(){
        return epslon;
    }

}
