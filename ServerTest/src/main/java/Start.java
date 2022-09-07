import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Start {

    public static void main(String[] args) {
        System.out.println("Criando conexão...");
        try (Socket conn = new Socket("127.0.0.1", 80);) {
            System.out.println("Conectado!");
            DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
            dataOutputStream.writeUTF("modelo=pessoa;operacao=LIST");
            InputStream in = conn.getInputStream();
            byte[] dadosBrutos = new byte[1024];
            int qtdBytesLidos = in.read(dadosBrutos);
            while (qtdBytesLidos >= 0) {
                String dadosStr = new String(dadosBrutos, 0, qtdBytesLidos);
                System.out.println(dadosStr);
                qtdBytesLidos = in.read(dadosBrutos);
            }
        } catch (IOException e) {
            System.out.println("Host não encontrado");
            e.printStackTrace();
        }
    }

}
