package operation;

import controllers.PeopleController;
import controllers.TeamController;
import interfaces.IModelController;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerOperation {

    final String OPERATION_INSERT = "INSERT";
    final String OPERATION_UPDATE = "UPDATE";
    final String OPERATION_GET = "GET";
    final String OPERATION_DELETE = "DELETE";
    final String OPERATION_LIST = "LIST";
    final String OPERATION_ADD = "ADD";
    final String OPERATION_REMOVE = "REMOVE";

    DataInputStream dataInputStream;

    public ServerOperation(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Esperando conexões");
        serverSocket.setReuseAddress(true);
        Socket socket = serverSocket.accept();

        System.out.println("Conexão estabelecida");
        this.dataInputStream = new DataInputStream(socket.getInputStream());

        boolean inOperation = true;
        while (inOperation) {
            String message = this.dataInputStream.readUTF();
            String response = this.executeOperation(message);
            System.out.println("Resposta: \n" + response);
            socket.getOutputStream().write(response.getBytes());
        }
    }

    private String executeOperation(String message) throws Exception {
        System.out.println("Requisição recebida: " + message);
        String response;
        HashMap<String, String> params = this.getParams(message);
        IModelController controller;
        switch (params.get("modelo")) {
            case "pessoa":
                controller = new PeopleController();
                break;
            case "equipe":
                controller = new TeamController();
                break;
            default:
                throw new Exception("Modelo não reconhecido.");
        }
        switch (params.get("operacao")) {
            case OPERATION_INSERT:
                response = controller.insert(params);
                break;
            case OPERATION_UPDATE:
                response = controller.update(params);
                break;
            case OPERATION_GET:
                response = controller.get(params);
                break;
            case OPERATION_DELETE:
                response = controller.delete(params);
                break;
            case OPERATION_LIST:
                response = controller.list(params);
                break;
            case OPERATION_ADD:
                response = controller.add(params);
                break;
            case OPERATION_REMOVE:
                response = controller.remove(params);
                break;
            default:
                throw new Exception("Operação não reconhecida.");
        }
        return response;
    }

    private HashMap<String, String> getParams(String message) {
        String[] parts = message.split(";");
        HashMap<String, String> params = new HashMap<>();
        for (String param : parts) {
            String[] paramParts = param.split("=");
            params.put(paramParts[0], paramParts[1]);
        }
        return params;
    }

}
