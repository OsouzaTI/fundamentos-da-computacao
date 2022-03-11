import java.util.ArrayList;

enum ESTADOS {
    INICIAL,
    FINAL,
    INICIAL_FINAL
};

public class Estado {    
    final char ID; // identificado desse estado (meramente visual)    
    ArrayList<Transicao> transicoes; // lista de transicoes desse estado
    boolean estadoInicial;
    boolean estadoFinal;
    
    Estado(char ID){
        this.ID = ID;
        this.estadoInicial = false;
        this.estadoFinal = false;
        this.transicoes = new ArrayList<>();
    }

    Estado(char ID, ESTADOS estado){
        this.ID = ID;
        this.transicoes = new ArrayList<>();
        definirEstado(estado);         
    }

    // definindo se e estado inicial, final ou os dois
    private void definirEstado(ESTADOS estado){
        if(estado == ESTADOS.INICIAL || estado == ESTADOS.INICIAL_FINAL)
            this.estadoInicial = true;
        if(estado == ESTADOS.FINAL   || estado == ESTADOS.INICIAL_FINAL)
            this.estadoFinal = true; 
    }

    public void adicionarTransicao(Transicao transicao){
        transicoes.add(transicao);
    }

    public boolean acharTransicao(char token){

        for(Transicao transicao : transicoes){
            if(transicao.token == token)
                return true;
        }

        return false;
    } 

    // faz uma transicao a partir de um token lido, e retorna
    // o novo estado, caso contrario retorna nulo
    public Estado fazerTransicao(char token){
        for(Transicao transicao : transicoes){
            if(transicao.token == token){
                Estado _destino = transicao.destino;
                System.out.println(ID + " -> " + _destino.ID + " lido " + token);
                return _destino;
            }
        }
        return null;
    }

}