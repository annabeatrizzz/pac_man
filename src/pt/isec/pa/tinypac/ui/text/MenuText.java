package pt.isec.pa.tinypac.ui.text;

import org.w3c.dom.Text;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.data.ReadMaze;
import pt.isec.pa.tinypac.model.fsm.Context;

import java.awt.event.KeyEvent;
import java.util.Scanner;

public class MenuText {

    //ATTRIBUTES
    static boolean sair = false;


    //CONSTRUCTOR
    public MenuText() {
    }

    //METHODS
    public static boolean confirmar_saida() {
        int opcao;

        Scanner input = new Scanner(System.in);
        System.out.println("Deseja realmente sair?\n" +
                "1-SIM\n2-NAO");
        do {
            opcao = input.nextInt();
        } while (opcao < 1 || opcao > 2);
        switch (opcao) {
            case 1 -> {
                return true;
            }
            case 2 -> {
                return false;
            }
        }
        return false;
    }

    public static void menu() {
        int opcao;
        Scanner input = new Scanner(System.in);
        do {
            do {
                System.out.println("\nJOGO PAC-MAN");
                System.out.println("\n\n1 - Iniciar Jogo");
                System.out.println("2- Consultar TOP 5");
                System.out.println("3- Sair do Jogo");
                System.out.println("\nDEIS-ISEC-IPC\tLEI-CE\tPROGRAMACAO AVANCADA\n" +
                        "     Trabalho Academico  2022-2023\n" +
                        "     ANNA BEATRIZ YABE   2021132515");
                opcao = input.nextInt();
            } while (opcao < 1 || opcao > 3);


            switch (opcao) {
                case 1 -> {
                    System.out.println("Jogo iniciado");
                    //alteracao 2 - gameEngine p/ gameManager
                    //GameEngine gameEngine = new GameEngine();
                    //alteracao gameEngine
                    Context fsm = new Context();
                    //Context fsm = new Context(gameEngine);
                    TextUI ui = new TextUI(fsm);
                    ui.start();
                }
                case 2 -> System.out.println("TOP 5");
                case 3 -> {
                    System.out.println("Sair do jogo");
                    if ((confirmar_saida() == true)) {
                        sair = true;
                    } else {
                        sair = false;
                    }
                }
            }
        } while (!sair);
    }
}