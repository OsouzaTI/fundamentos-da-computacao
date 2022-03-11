package matchs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;

import regular_expression.State;
import regular_expression.Transition;


class URLAutomaton {
    
    static ArrayList<State> states = new ArrayList<>();    
    static Date date = new Date();
    
    public static void main(String[] args) throws IOException {
        createAutomaton();
        
        String a = "LINK AQUI";
        URLConnection connection = new URL(a).openConnection();
        connection.setRequestProperty("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();
        BufferedReader r = new BufferedReader(
            new InputStreamReader(
                connection.getInputStream(),
                Charset.forName("UTF-8")
            )
        );
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            sb.append(line);            
        }
               
        Boolean aceito = match(sb.toString());
        System.out.println(aceito);

    }

    public State findState(int state){

        State S = states.stream()
                    .filter(item -> item.getState() == state)
                    .findFirst()
                    .get();
        
        return S;

    }

    private static void createAutomaton(){

        states.add(new State(0, new Transition[]{
            new Transition(1, 'h'),            
        }));
        
        states.add(new State(1, new Transition[]{
            new Transition(2, 't'),            
        }));

        states.add(new State(2, new Transition[]{
            new Transition(3, 't'),            
        }));

        states.add(new State(3, new Transition[]{
            new Transition(4, 'p'),            
        }));

        states.add(new State(4, new Transition[]{
            new Transition(5, 's'),            
        }));

        states.add(new State(5, new Transition[]{
            new Transition(6, ':'),            
        }));

        states.add(new State(6, new Transition[]{
            new Transition(7, '/'),            
        }));

        states.add(new State(7, new Transition[]{
            new Transition(8, '/'),            
        }));

        states.add(new State(8, new ArrayList<Transition>(){{
            // Parte [a-z] - Criando os estados definidos acima no State(7)            
            int next = 0;
            for(int i = (int)'a', j = 0; i < (int)'z'; i++, j++){
                next = 9 + j;
                add(new Transition(next, (char)i));                
            };       
            
            // uniao
            add(new Transition(next+1, '.'));
        }}.toArray()));
        
        // criando os estados aceitos pelas transicoes acima
        for(int i = (int)'a', j = 0; i < (int)'z'; i++, j++){
            int next = 9 + j;
            states.add(new State(next, new Transition[]{
                new Transition(8) // transicao epslon
            })); 
        }

        int nStates = State.NUM_STATES;

        // com

        states.add(new State(nStates, new Transition[]{
            new Transition(nStates+1, 'c'),            
        }));

        states.add(new State(nStates+1, new Transition[]{
            new Transition(nStates+2, 'o'),            
        }));

        states.add(new State(nStates+2, new Transition[]{
            new Transition(nStates+5, 'm'), 
        }));

        // net
        states.add(new State(nStates+3, new Transition[]{
            new Transition(nStates+4, 'e'),            
        }));

        states.add(new State(nStates+4, new Transition[]{
            new Transition(nStates+5, 't'), 
        }));

        // estado final
        states.add(new State(nStates+5, new Transition[]{}, true));

    }

    private static void writeLog(String str){        
        BufferedWriter writer;
        try {
            String sdate = String.valueOf(date.getTime());
            writer = new BufferedWriter(new FileWriter(sdate+"_logs.txt", true));            
            writer.append(str);        
            writer.close();
        } catch (IOException e) {            
            e.printStackTrace();
        }
    }

    public static Boolean match(String test){
        String temp = "";
        int pos = 0;        
        while(pos < test.length()){
            State state = states.get(State.STATE);  
            char c = test.charAt(pos);

            Transition t = state.getTransition(c);
            if(t != null){
                State.changeState(t.getStateTransition());
                if(!t.isEpslon()){
                    pos++;
                    temp += c;
                }
            } else if(state.existsEpslonTransition()) {
                Transition epslon = state.findFirstEpslonTransition();
                State.changeState(epslon.getStateTransition());
            }  else if(state.isFinalState()) {
                // return true;
                writeLog(temp+"\n");
                temp = ""; 
                State.changeState(0);
            } else {
                // return false; 
                temp = "";             
                State.changeState(0);
                pos++;
            }

        }

        return false;

    }

}
