
import models.Client;
import models.ClientOperation;

import java.util.Scanner;

public class Start {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.println("Insira o ip do host:");
        String host = s.next();
        System.out.println("Insira a porta do host:");
        int port = s.nextInt();
        new ClientOperation(host, port);
    }
    
}