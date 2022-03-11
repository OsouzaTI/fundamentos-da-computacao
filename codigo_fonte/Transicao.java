public class Transicao {
    Character token; // token que sera consumido
    Estado destino; // estado de destino
    Transicao(char token, Estado destino){
        this.token = token;
        this.destino = destino;
    }
}
