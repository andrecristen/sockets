package models;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String host;
    private int port;
    private Socket socket;
    DataOutputStream stream;

    public Socket getSocket() {
        return socket;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Client(String host, int port, Scanner s) {
        this.host = host;
        this.port = port;
        try {
            this.socket = new Socket(host, port);
            this.stream = new DataOutputStream(
                    this.socket.getOutputStream());

            BufferedReader br
                    = new BufferedReader(
                    new InputStreamReader(
                            this.socket.getInputStream()));

            String response = "";
            while (!(response = br.readLine()).equals("exit")) {
                System.out.println(response);
                System.out.println("" +
                        "1 - Para gerir Pessoa \n" +
                        "2 - Para gerir Equipe\n" +
                        "3 - Para sair");

                int modelToHandle = s.nextInt();
                int operation = 0;
                if (modelToHandle != 3) {
                    String msg = "Por favor, escolha a operação: \n" +
                            "1 - INSERT \n" +
                            "2 - UPDATE\n" +
                            "3 - DELETE \n" +
                            "4 - GET \n" +
                            "5 - LIST";
                    if (modelToHandle == 2){
                        msg += "\n"+"6 - ADD PESSOA\n"+ "7 - REMOVE PESSOA \n";
                    }
                    System.out.println(msg);
                    operation = s.nextInt();
                }
                switch (modelToHandle) {
                    case 1: {
//                      HANDLE PESSOA
                        String strParams = "";
                        String strOperation = "";
                        switch (operation) {
//                          INSERT
                            case 1 : {
                                System.out.println("cpf");
                                String cpf = s.next();
                                System.out.println("nome");
                                String nome = s.next();
                                System.out.println("endereco");
                                String endereco = s.next();
                                strParams = "cpf=" + cpf + ";nome=" + nome + ";endereco=" + endereco;
                                strOperation = "INSERT";
                                break;
                            }
//                          UPDATE
                            case 2: {
                                System.out.println("cpf");
                                String cpf = s.next();
                                System.out.println("nome");
                                String nome = s.next();
                                System.out.println("endereco");
                                String endereco = s.next();
                                strParams = "cpf=" + cpf + ";nome=" + nome + ";endereco=" + endereco;
                                strOperation = "UPDATE";
                                break;
                            }

//                          DELETE
                            case 3: {
                                System.out.println("cpf");
                                String cpf = s.next();
                                strOperation = "DELETE";
                                strParams = "cpf=" + cpf;
                                break;
                            }
//                          GET
                            case 4: {
                                System.out.println("cpf");
                                String cpf = s.next();
                                strOperation = "GET";
                                strParams = "cpf=" + cpf;
                                break;
                            }
//                          LIST
                            case 5: {
                                strOperation = "LIST";
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                        String message = "modelo=pessoa;operacao=" + strOperation + ";" + strParams;
                        this.stream.writeUTF(message);
                        break;
                    }
//                  HANDLE EQUIPE
                    case 2: {
                        String strParams = "";
                        String strOperation = "";
                        switch (operation) {
//                          INSERT
                            case 1: {
                                System.out.println("Nome da equipe:");
                                String nomeEquipe = s.next();
                                System.out.println("cpf do líder:");
                                String cpfLider = s.next();
                                System.out.println("Setor:");
                                String setor = s.next();
                                System.out.println("Data de fundação (dd/mm/yyyy):");
                                String dataFundacao = s.next();
                                strParams = "nome=" + nomeEquipe+ ";lider="+ cpfLider+ ";setor="+setor+";dataFundacao="+dataFundacao;
                                strOperation = "INSERT";
                                break;
                            }
//                          UPDATE
                            case 2: {
                                System.out.println("Nome da equipe:");
                                String nomeEquipe = s.next();

                                System.out.println("novo nome da equipe");
                                String novoNomeEquipe = s.next();

                                strParams = "nome=" + nomeEquipe+ ";novoNome="+novoNomeEquipe;
                                strOperation = "UPDATE";
                                break;
                            }
//                          DELETE
                            case 3: {
                                System.out.println("Nome da equipe:");
                                String nomeEquipe = s.next();
                                strOperation = "DELETE";
                                strParams = "nome=" + nomeEquipe;
                                break;
                            }
//                          GET
                            case 4: {
                                System.out.println("Nome da equipe:");
                                String nomeEquipe = s.next();
                                strOperation = "GET";
                                strParams = "nome=" + nomeEquipe;
                                break;
                            }
//                          LIST
                            case 5: {
                                strOperation = "LIST";
                                break;
                            }
//                          ADICIONAR PESSSOA NA EQUIPE
                            case 6:{
                                System.out.println("Nome da equipe:");
                                String nomeEquipe = s.next();

                                System.out.println("CPF da Pessoa:");
                                String cpf = s.next();

                                strParams = "nome=" + nomeEquipe + ";cpf=" + cpf;
                                strOperation = "ADD";
                                break;

                            }
//                          REMOVER PESSOA DA EQUIPE
                            case 7:{
                                System.out.println("Nome da equipe:");
                                String nomeEquipe = s.next();

                                System.out.println("CPF da pessoa: ");
                                String cpf = s.next();

                                strParams = "nome=" + nomeEquipe + ";cpf=" + cpf;
                                strOperation = "REMOVE";
                                break;


                            }
                            default: {
                                break;
                            }


                        }

                        String message = "modelo=equipe;operacao=" + strOperation + ";" + strParams;
                        this.stream.writeUTF(message);
                        break;

                    }
                    default: {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

