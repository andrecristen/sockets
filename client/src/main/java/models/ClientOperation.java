package models;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientOperation {

    final String OPERATION_INSERT = "INSERT";
    final String OPERATION_UPDATE = "UPDATE";
    final String OPERATION_GET = "GET";
    final String OPERATION_DELETE = "DELETE";
    final String OPERATION_LIST = "LIST";
    final String OPERATION_ADD = "ADD";
    final String OPERATION_REMOVE = "REMOVE";

    private Socket socket;
    private DataOutputStream stream;

    public ClientOperation(String host, int port) throws Exception {
        this.socket = new Socket(host, port);
        this.stream = new DataOutputStream(this.socket.getOutputStream());
        this.executeOperation();
    }

    private void executeOperation() throws Exception {
        InputStream in = this.getSocket().getInputStream();
        byte[] dadosBrutos = new byte[1024];
        int qtdBytesLidos = 0;
        while (qtdBytesLidos >= 0) {
            qtdBytesLidos = in.read(dadosBrutos);
            String response = new String(dadosBrutos, 0, qtdBytesLidos);
            System.out.println(response);
            selectModelOperation();
        }
    }

    private void selectModelOperation() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione o modelo que deseja gerir:\n 1 - Pessoa\n 2 - Equipe\n 3 - Sair");
        int modelToHandle = scanner.nextInt();
        switch (modelToHandle) {
            case 1:
                selectDefaultOptionsOperation();
                break;
            case 2:
                selectDefaultOptionsOperation();
                selectEquipeOptionsOperation();
                break;
            case 3:
                throw new Exception("Fim da execução");
            default:
                System.out.println("Opção inválida pressione enter.");
                break;
        }
        int operation = scanner.nextInt();
        switch (modelToHandle) {
            case 1:
                handlePessoa(operation);
                break;
            case 2:
                handleEquipe(operation);
                break;
        }
    }

    private void handlePessoa(int option) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String params = "";
        String operation = "";
        switch (option) {
            case 1 : {
                System.out.println("cpf");
                String cpf = scanner.next();
                System.out.println("nome");
                String nome = scanner.next();
                System.out.println("endereco");
                String endereco = scanner.next();
                params = "cpf=" + cpf + ";nome=" + nome + ";endereco=" + endereco;
                operation = OPERATION_INSERT;
                break;
            }
            case 2: {
                System.out.println("cpf");
                String cpf = scanner.next();
                System.out.println("nome");
                String nome = scanner.next();
                System.out.println("endereco");
                String endereco = scanner.next();
                params = "cpf=" + cpf + ";nome=" + nome + ";endereco=" + endereco;
                operation = OPERATION_UPDATE;
                break;
            }
            case 3: {
                System.out.println("cpf");
                String cpf = scanner.next();
                operation = OPERATION_DELETE;
                params = "cpf=" + cpf;
                break;
            }
            case 4: {
                System.out.println("cpf");
                String cpf = scanner.next();
                operation = OPERATION_GET;
                params = "cpf=" + cpf;
                break;
            }
            case 5: {
                operation = OPERATION_LIST;
                break;
            }
        }
        String message = "modelo=pessoa;operacao=" + operation + ";" + params;
        this.getStream().writeUTF(message);
    }

    private void handleEquipe(int option) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String params = "";
        String operation = "";
        switch (option) {
            case 1: {
                System.out.println("Nome da equipe:");
                String nomeEquipe = scanner.next();
                System.out.println("cpf do líder:");
                String cpfLider = scanner.next();
                System.out.println("Setor:");
                String setor = scanner.next();
                System.out.println("Data de fundação (dd/mm/yyyy):");
                String dataFundacao = scanner.next();
                params = "nome=" + nomeEquipe+ ";lider="+ cpfLider+ ";setor="+setor+";dataFundacao="+dataFundacao;
                operation = OPERATION_INSERT;
                break;
            }
            case 2: {
                System.out.println("Nome da equipe:");
                String nomeEquipe = scanner.next();
                System.out.println("Novo nome da equipe");
                String novoNomeEquipe = scanner.next();
                params = "nome=" + nomeEquipe+ ";novoNome="+novoNomeEquipe;
                operation = OPERATION_UPDATE;
                break;
            }
            case 3: {
                System.out.println("Nome da equipe:");
                String nomeEquipe = scanner.next();
                operation = OPERATION_DELETE;
                params = "nome=" + nomeEquipe;
                break;
            }
            case 4: {
                System.out.println("Nome da equipe:");
                String nomeEquipe = scanner.next();
                operation = OPERATION_GET;
                params = "nome=" + nomeEquipe;
                break;
            }
            case 5: {
                operation = OPERATION_LIST;
                break;
            }
            case 6:{
                System.out.println("Nome da equipe:");
                String nomeEquipe = scanner.next();
                System.out.println("CPF da Pessoa:");
                String cpf = scanner.next();
                params = "nome=" + nomeEquipe + ";cpf=" + cpf;
                operation = OPERATION_ADD;
                break;

            }
            case 7:{
                System.out.println("Nome da equipe:");
                String nomeEquipe = scanner.next();
                System.out.println("CPF da pessoa: ");
                String cpf = scanner.next();
                params = "nome=" + nomeEquipe + ";cpf=" + cpf;
                operation = OPERATION_REMOVE;
                break;
            }
        }
        String message = "modelo=equipe;operacao=" + operation + ";" + params;
        this.getStream().writeUTF(message);
    }

    private void selectDefaultOptionsOperation() {
        System.out.println("Por favor, escolha a operação: \n 1 - INSERT \n 2 - UPDATE\n 3 - DELETE \n 4 - GET \n 5 - LIST");
    }

    private void selectEquipeOptionsOperation() {
        System.out.println(" 6 - ADD PESSOA\n 7 - REMOVE PESSOA \n");
    }

    public Socket getSocket() {
        return socket;
    }

    public DataOutputStream getStream() {
        return stream;
    }

}
