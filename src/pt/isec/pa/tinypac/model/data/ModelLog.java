package pt.isec.pa.tinypac.model.data;

import java.util.ArrayList;
import java.util.List;

// singleton
//Essa classe usa do padrao singleton: apenas existe 1 instancia nela
//para isso, sera necessario:
//1- construtor privado
//2- variavel _instance (inicializada a null)
//3- metodo getInstance que permite a primeira e unica criacao da classe

// classe singleton sao utilizadas para aqueles "objetos" que precisam de uma e apenas uma criacao
public class ModelLog {
    private static ModelLog _instance=null;

    public static ModelLog getInstance() {
        if (_instance == null)
            _instance = new ModelLog();
        return _instance;
    }

    protected ArrayList<String> log;

    private ModelLog() {
        log = new ArrayList<>();
    }

    public void reset() {
        log.clear();
    }

    public void add(String msg) {
        log.add(msg);
    }

    public List<String> getLog() {
        return new ArrayList<>(log);
    }
}
