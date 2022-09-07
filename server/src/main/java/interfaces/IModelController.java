package interfaces;

import controllers.DataBaseController;
import models.People;
import models.Team;

import java.io.PrintStream;
import java.util.HashMap;

public interface IModelController {

    public String insert(HashMap<String, String> params, PrintStream printStream);

    public String update(HashMap<String, String> params, PrintStream printStream);

    public String get(HashMap<String, String> params, PrintStream printStream);

    public String delete(HashMap<String, String> params, PrintStream printStream);

    public String list(HashMap<String, String> params, PrintStream printStream);

    public String add(HashMap<String, String> params, PrintStream printStream);

    public String remove(HashMap<String, String> params, PrintStream printStream);

    public static People findPeopleByParams(HashMap<String, String> params) {
        return DataBaseController.peoples.get(params.get("cpf"));
    }
    public static Team findTeamByParams(HashMap<String, String> params) {
        return DataBaseController.teams.get(params.get("nome"));
    }

}
