package pt.isec.pa.tinypac.model.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Top5Players implements Serializable {

    //ATTRIBUTES
    private static final long serialVersionUID = 1L;
    public List<Players> playersList = new ArrayList<>();


    //CONSTRUCTOR
    public Top5Players() {
        load(new File("Top5.bin"));
    }

    public boolean addPlayerToTop5(Players player, int position){
        if(position == 4)
            playersList.set(position, player);
        else{
            for(int i=4; i>position; i--){
                playersList.set(i, playersList.get(i-1));
            }
            playersList.set(position, player);
        }
        save(new File("Top5.bin"));
        return true;
    }

    public Players getTop(int number){
        return playersList.get(number);
    }

    public boolean save(File file) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(playersList);
        }catch(Exception e){
            System.err.println("Erro a escrever o TOP 5");
            return false;
        }
        return true;
    }

    public boolean load(File file) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            playersList = (ArrayList) ois.readObject();

            if(playersList.isEmpty() || playersList.size() < 5){
                /**
                 * Se por acaso o ficheiro não tiver os 5 jogadores do top 5 serão carregados esses outros
                 * **/
                playersList.add(new Players("primeiro", 0));
                playersList.add(new Players("segundo", 0));
                playersList.add(new Players("terceiro", 0));
                playersList.add(new Players("quarto", 0));
                playersList.add(new Players("quinto", 0));
            }
        }catch(Exception e){
            System.err.println("Erro em carregar o TOP 5");
            return false;
        }
        return true;
    }
}