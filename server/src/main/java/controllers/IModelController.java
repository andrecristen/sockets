package controllers;

import java.io.PrintStream;
import java.util.HashMap;

public interface IModelController {

    public String insert(HashMap<String, String> params, PrintStream printStream);
    public String update(HashMap<String, String> params, PrintStream printStream);
    public String get(HashMap<String, String> params, PrintStream printStream);
    public String delete(HashMap<String, String> params, PrintStream printStream);
    public String list(HashMap<String, String> params, PrintStream printStream);

}
