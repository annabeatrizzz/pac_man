package pt.isec.pa.tinypac.model.data;

import java.io.Serializable;

public class Players implements Serializable {


    /**
     * Classe suporte ao TOP 5
     * Visa manter quais foram os melhores jogadores
     */


    //ATTRIBUTES
    private String nomeJogador; /**Nome do jogador**/
    private int pontuacao;      /**Pontuacao do jogador**/
    private static final long serialVersionUID = 1L;

    //CONSTRUCTOR
    public Players(String nomeJogador, int pontuacao){
        /**
         * Construtor do Jogador
         * Visa criar um objeto com informações do seu nome e a sua pontuação
         */
        this.nomeJogador = nomeJogador;
        this.pontuacao = pontuacao;
    }


    //GETTERS AND SETTERS
    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}