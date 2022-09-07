package controllers;

import interfaces.IModelController;
import models.People;

import java.io.PrintStream;
import java.util.HashMap;

public class PeopleController implements IModelController {

    public String insert(HashMap<String, String> params, PrintStream printStream) {
        People people = new People(params.get("cpf"), params.get("nome"), params.get("endereco"));
        DataBaseController.peoples.put(people.getCpf(), people);
        return null;
    }

    public String update(HashMap<String, String> params, PrintStream printStream) {
        People people = IModelController.findPeopleByParams(params);
        if (people != null) {
            people.setNome(params.get("nome"));
            people.setEndereco(params.get("endereco"));
            return "Pessoa atualizada com sucesso";
        } else {
            return "Pessoa não encontrada";
        }
    }

    public String get(HashMap<String, String> params, PrintStream printStream) {
        if (DataBaseController.peoples.isEmpty()) {
            return "Sem pessoas cadastradas";
        } else {
            People people = IModelController.findPeopleByParams(params);
            if (people != null) {
                return people.toString();
            } else {
                return "Pessoa não encontrada";
            }
        }
    }

    public String delete(HashMap<String, String> params, PrintStream printStream) {
        if (DataBaseController.peoples.isEmpty()) {
            return "Sem pessoas cadastradas";
        } else {
            People people = IModelController.findPeopleByParams(params);
            if (people != null) {
                DataBaseController.peoples.remove(people.getCpf());
                return "Pessoa removida com sucesso";
            } else {
                return "Pessoa não encontrada";
            }
        }
    }

    public String list(HashMap<String, String> params, PrintStream printStream) {
        if (DataBaseController.peoples.isEmpty()) {
            return "0";
        } else {
            StringBuilder reponse = new StringBuilder();
            if (DataBaseController.peoples.size() < 10) {
                reponse.append("0");
            } 
            reponse.append(DataBaseController.peoples.size());
            for (People people : DataBaseController.peoples.values()) {
                reponse.append("\n").append(people.toString());
            }
            return reponse.toString();
        }
    }

    public String add(HashMap<String, String> params, PrintStream printStream) {
        return null;
    }

    @Override
    public String remove(HashMap<String, String> params, PrintStream printStream) {
        return null;
    }

}
