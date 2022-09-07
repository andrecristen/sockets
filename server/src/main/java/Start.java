import models.ServerOperation;

import java.io.IOException;
import java.util.Scanner;

public class Start {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual a porta que você deseja iniciar o servidor?");
        new ServerOperation(scanner.nextInt());
    }

}
