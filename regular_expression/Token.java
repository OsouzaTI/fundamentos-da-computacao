package regular_expression;

enum CHAR_TYPE {
    KLEENE,
    CONCAT,
    UNION,
    SYMBOL,
    EMPTY
};

class Operator {
    final static char UNION  = '+';
    final static char CONCAT = '.';
    final static char KLEENE = '*';
}


class ExpressionTree {

    public ExpressionTree l = null;
    public ExpressionTree r = null;
    public CHAR_TYPE cType  = CHAR_TYPE.EMPTY;
    public Character label  = ' ';

    public ExpressionTree(CHAR_TYPE cType){
        this.cType = cType;
    }

    public ExpressionTree(CHAR_TYPE cType, Character label){
        this.cType = cType;
        this.label = label;
    }

    public static void infoBinaryTree(ExpressionTree et){

        if(et == null)
            return;

        infoBinaryTree(et.l);
        System.out.println(et.label);
        infoBinaryTree(et.r);

    }

}
