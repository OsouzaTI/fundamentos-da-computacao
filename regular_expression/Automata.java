package regular_expression;

import java.util.ArrayList;
import java.util.LinkedList;


public class Automata {
    
    private ArrayList<State> states = new ArrayList<>(); 

    private ExpressionTree removeLast(LinkedList<ExpressionTree> let){
        if(let.size() > 1)
            return let.removeLast();
        return null;
    }

    public ExpressionTree makeExpressionTree(String regex){
        LinkedList<ExpressionTree> stack = new LinkedList<>();
        for(char c : regex.toCharArray()){
            
            if(c == Operator.UNION){
                ExpressionTree z = new ExpressionTree(CHAR_TYPE.UNION, Operator.UNION);
                z.r = removeLast(stack);
                z.l = removeLast(stack);
                stack.add(z);
            } else if(c == Operator.CONCAT){
                ExpressionTree z = new ExpressionTree(CHAR_TYPE.CONCAT, Operator.CONCAT);
                z.r = removeLast(stack);
                z.l = removeLast(stack);
                stack.add(z);
            } else if(c == Operator.KLEENE){
                ExpressionTree z = new ExpressionTree(CHAR_TYPE.KLEENE, Operator.KLEENE);
                z.l = removeLast(stack);
                stack.add(z);
            } else if(c == '(' || c == ')'){
                continue;
            } else {
                stack.add(new ExpressionTree(CHAR_TYPE.SYMBOL, c));
            }

        }

        // retornando estado inicial
        return stack.get(0);
    }

    public void compile(String regex){



    }

    public void match(String word){



    }


}


class AutomataTest {

    public static void main(String[] args) {
        
        Automata automata = new Automata();
        ExpressionTree t = automata.makeExpressionTree("ab+");
        ExpressionTree.infoBinaryTree(t);
    }

}