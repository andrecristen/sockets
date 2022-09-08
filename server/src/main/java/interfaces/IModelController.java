package interfaces;

import controllers.DataBaseController;
import models.People;
import models.Team;

import java.util.HashMap;

public interface IModelController {

    String insert(HashMap<String, String> params);

    String update(HashMap<String, String> params);

    String get(HashMap<String, String> params);

    String delete(HashMap<String, String> params);

    String list(HashMap<String, String> params);

    String add(HashMap<String, String> params);

    String remove(HashMap<String, String> params);

    static People findPeopleByParams(HashMap<String, String> params) {
        return DataBaseController.peoples.get(params.get("cpf"));
    }

    static Team findTeamByParams(HashMap<String, String> params) {
        return DataBaseController.teams.get(params.get("nome"));
    }

}
