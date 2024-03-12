package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.mazeElements.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadMaze {

    private static int numNivel = 1;
    private static String level;
    private static List<Integer> niveisPassados = new ArrayList();

    public static void selectLevel(Game game){
        //int nivel = game.getNivel();
        int nivel = numNivel;
        /*if(niveisPassados.size() > 1) {
            int posicao = niveisPassados.size() - 1;
            nivel = niveisPassados.get(posicao);
        }*/
        //int i = game.getNivel();

        do{
            //if(game.getNivel() < 9){
                if(nivel < 9){
                    level = "Level0" + nivel + ".txt";
            }
            else{
                level = "Level" + nivel + ".txt";
            }

            niveisPassados.add(nivel);

            File f = new File(level);

            if(f.exists()) {
                game.setNivel(nivel);
                System.out.println("Tentando ficheiro " + level);
                numNivel++;

                return;
            }
            else{
                numNivel++;
                System.out.println("Nivel " + nivel + " nao existe, passando ao proximo");
                //game.setNivel(game.getNivel() + 1);
            }
        }while(numNivel<20);
    }

    public static void sizeOfMaze(Game game){
        try {
            FileReader fr = new FileReader("Level01.txt");
            BufferedReader ficheiro = new BufferedReader(fr);
            String line;
            int width = 0, height = 0;

            while ((line = ficheiro.readLine()) != null) {
                height++;
                width = line.length();
            }

            game.setHeight(height);
            game.setWidth(width);
            ficheiro.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fillMaze(Game game) {
        int contadorBolas = 0;
        try {
            FileReader fr = new FileReader("Level01.txt");
            BufferedReader ficheiro = new BufferedReader(fr);
            String line;
            //é necessario abrir novamente o ficheiro senao tudo fica como null
            fr = new FileReader("Level01.txt");
            ficheiro = new BufferedReader(fr);
            int x, y = 0, contador = 0;
            while ((line = ficheiro.readLine()) != null) {
                IMazeElement simbolo = null;
                char[] array_linha = line.toCharArray();
                for (x = 0; x < array_linha.length; x++) {
                    if (array_linha[x] == 'x') {
                        simbolo = new MazeElementParede();
                    } else if (array_linha[x] == 'W') {
                        simbolo = new MazeElementZonaWrap();
                    } else if (array_linha[x] == 'o') {
                        simbolo = new MazeElementBola();
                        contadorBolas++;
                    } else if (array_linha[x] == 'F') {
                        simbolo = new MazeElementFruta();
                    } else if (array_linha[x] == 'M') {
                        simbolo = new PacMan(game);
                    } else if (array_linha[x] == 'O') {
                        simbolo = new MazeElementBolaComPoderes();
                    } else if (array_linha[x] == 'y') {
                        if(contador == 0){
                            simbolo = new Inky(game);
                        }
                        else if(contador == 1){
                            simbolo = new Blinky(game);
                        }
                        else if(contador == 2){
                            simbolo = new Pinky(game);
                        }
                        else if(contador == 3){
                            simbolo = new Clyde(game);
                        }
                        else{
                            simbolo = new MazeElementCaverna();
                        }
                        contador++;
                    } else if (array_linha[x] == 'Y') {
                        simbolo = new MazeElementPosicaoInicialFantasmas();
                    }
                    game.maze.set(y, x, simbolo);
                }
                y++;
            }

            game.setQuantidadeBolas(contadorBolas);
            ficheiro.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}