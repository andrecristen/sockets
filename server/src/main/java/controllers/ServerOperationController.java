package controllers;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerOperationController {

    final String OPERATION_INSERT = "INSERT";
    final String OPERATION_UPDATE = "UPDATE";
    final String OPERATION_GET = "GET";
    final String OPERATION_DELETE = "DELETE";
    final String OPERATION_LIST = "LIST";

    PrintStream printStream;
    DataInputStream dataInputStream;
    public ServerOperationController(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();

        this.printStream = new PrintStream(socket.getOutputStream());
        this.dataInputStream = new DataInputStream(socket.getInputStream());

        boolean inOperation = true;
        while(inOperation) {
            String message = this.dataInputStream.readUTF();
            this.printStream.println(this.executeOperation(message));
        }
    }

    private String executeOperation(String message) throws Exception {
        String response = null;
        HashMap<String, String> params = this.getParams(message);
        IModelController controller = switch (params.get("modelo")) {
            case "pessoa" -> new PeopleController();
            case "equipe" -> new TeamController();
            default -> throw new Exception("Modelo não reconhecido.");
        };
        switch (params.get("operacao")) {
            case OPERATION_INSERT -> response = controller.insert(params, this.printStream);
            case OPERATION_UPDATE -> response = controller.update(params, this.printStream);
            case OPERATION_GET -> response = controller.get(params, this.printStream);
            case OPERATION_DELETE -> response = controller.delete(params, this.printStream);
            case OPERATION_LIST -> response = controller.list(params, this.printStream);
            default -> throw new Exception("Operação não reconhecida.");
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
