package controllers;

import interfaces.IModelController;
import models.People;
import models.Team;
import utils.DateUtil;

import java.util.Date;
import java.util.HashMap;

public class TeamController implements IModelController {

    public String insert(HashMap<String, String> params) {
        People lider = DataBaseController.peoples.get(params.get("lider"));
        if (lider != null) {
            Date dataFundacao = DateUtil.stringToDateTime(params.get("dataFundacao"), DateUtil.FORMAT_DATE_BRAZILIAN);
            Team team = new Team(params.get("nome"), params.get("setor"), dataFundacao, lider);
            team.addIntegrante(lider);
            DataBaseController.teams.put(team.getNome(), team);
            return "Equipe cadastrada comm sucesso";
        }
        return "Erro na criação de equipe";
    }

    public String update(HashMap<String, String> params) {
        Team team = IModelController.findTeamByParams(params);
        if (team != null) {
            People lider = DataBaseController.peoples.get(params.get("lider"));
            if (lider != null) {
                Date dataFundacao = DateUtil.stringToDateTime(params.get("dataFundacao"), DateUtil.FORMAT_DATE_BRAZILIAN);
                team.setNome(params.get("novoNome"));
                team.setSetor(params.get("setor"));
                team.setDataFundacao(dataFundacao);
                team.setLider(lider);
                return "Equipe atualizada com sucesso";
            } else {
                return "Lider informado não encontrado";
            }
        } else {
            return "Equipe não encontrada";
        }
    }

    public String get(HashMap<String, String> params) {
        if (DataBaseController.teams.isEmpty()) {
            return "Sem equipes cadastradas";
        } else {
            Team team = IModelController.findTeamByParams(params);
            if (team != null) {
                return team.toString();
            } else {
                return "Equipe não encontrada";
            }
        }
    }

    public String delete(HashMap<String, String> params) {
        if (DataBaseController.teams.isEmpty()) {
            return "Sem equipes cadastradas";
        } else {
            Team team = IModelController.findTeamByParams(params);
            if (team != null) {
                DataBaseController.teams.remove(team.getNome());
                return "Equipe removida com sucesso";
            } else {
                return "Equipe não encontrada";
            }
        }
    }

    public String list(HashMap<String, String> params) {
        if (DataBaseController.teams.isEmpty()) {
            return "0";
        } else {
            StringBuilder reponse = new StringBuilder();
            if (DataBaseController.teams.size() < 10) {
                reponse.append("0");
            }
            reponse.append(DataBaseController.teams.size());
            for (Team team : DataBaseController.teams.values()) {
                reponse.append("\n").append(team.toString());
            }
            return reponse.toString();
        }
    }

    public String add(HashMap<String, String> params) {
        if (DataBaseController.teams.isEmpty()) {
            return  "Sem equipes cadastradas";
        }
        if (DataBaseController.peoples.isEmpty()) {
            return "Sem pessoas cadastradas";
        }
        People people = IModelController.findPeopleByParams(params);
        if (people != null) {
            Team team = IModelController.findTeamByParams(params);
            if (team != null) {
                team.addIntegrante(people);
                return "Integrante adicionado a equipe";
            } else {
                return "Equipe não encontrada";
            }
        } else {
            return "Pessoa não encontrada";
        }
    }
    public String remove(HashMap<String, String> params) {
        if (DataBaseController.teams.isEmpty()) {
            return  "Sem equipes cadastradas";
        }
        if (DataBaseController.peoples.isEmpty()) {
            return "Sem pessoas cadastradas";
        }
        People people = IModelController.findPeopleByParams(params);
        if (people != null) {
            Team team = IModelController.findTeamByParams(params);
            if (team != null) {
                team.removeIntegrante(people);
                return "Integrante removido da equipe";
            } else {
                return "Equipe não encontrada";
            }
        } else {
            return "Pessoa não encontrada";
        }
    }

}
