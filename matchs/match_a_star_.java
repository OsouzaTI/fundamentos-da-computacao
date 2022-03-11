package matchs;

enum EState {Q0, Q1, Q2, Q3};

class AStar {
    static EState state = EState.Q0;

    public static void main(String[] args) {
        Boolean resultado = match("aaab");
        System.out.println("Resultado: " + resultado);
    }

    public static void info(String test, int i){
        String firstPart = test.substring(0, i);
        String finalPart = test.substring(i);
        System.out.println(firstPart +"["+state.toString()+"]"+finalPart);
    }

    public static Boolean match(String test){      
        String find = "";

        int i = 0;
        while(i < test.length()){
            char c = test.charAt(i);    
            if(state == EState.Q0){
                state = EState.Q1;
            } else if(state == EState.Q1 && c == 'a') {
                state = EState.Q2;   
                find += c;
                i++;             
            } else if(state == EState.Q2) {
                state = EState.Q3;
            } else if(state == EState.Q3) {
                state = EState.Q1;
            } else {
                break;
            }
            info(test, i);
        }
        info(test, i);
        System.out.println("match: " + find);
        return state == EState.Q3 || state == EState.Q1;
    }

}