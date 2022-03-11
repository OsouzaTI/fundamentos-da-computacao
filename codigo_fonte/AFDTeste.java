
public class AFDTeste {
    
    public static void main(String[] args) {

        // criando o AFD
        AFD afd = new AFD();
        afd.definirAlfabeto('0', '1');

        // estados 
        Estado A = new Estado('A');        
        Estado B = new Estado('B', ESTADOS.FINAL);
        Estado C = new Estado('C', ESTADOS.FINAL);
        Estado D = new Estado('D');

        // transições
        Transicao tAC = new Transicao('1', C);
        Transicao tAB = new Transicao('0', B);
        A.adicionarTransicao(tAC);
        A.adicionarTransicao(tAB);
        Transicao tCD = new Transicao('0', D);
        Transicao tCA = new Transicao('1', A);
        C.adicionarTransicao(tCD);
        C.adicionarTransicao(tCA);
        Transicao tDB = new Transicao('1', B);
        Transicao tDC = new Transicao('0', C);
        D.adicionarTransicao(tDB);
        D.adicionarTransicao(tDC);
        Transicao tBA = new Transicao('0', A);
        Transicao tBD = new Transicao('1', D);
        B.adicionarTransicao(tBA);
        B.adicionarTransicao(tBD);
        
        // o primeiro estado adicionado no AFD e o estado
        // inicial
        afd.adicionarEstado(A);
        afd.adicionarEstado(B);
        afd.adicionarEstado(C);
        afd.adicionarEstado(D);

        afd.teste("000");
        afd.teste("10011");
        afd.teste("0101");
        afd.teste("11100");

    }

}
