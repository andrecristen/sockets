
import models.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Insira o ip do host");
        String host = s.next();
        System.out.println("Insira a porta do host");
        int port = s.nextInt();
        new Client(host, port,s);
    }
}