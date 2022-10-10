import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 8989;
    private static Map<String, String> categories = new HashMap<>();
    private static CountingLogic countingLogic = new CountingLogic(categories);

    public void startServer() throws IOException {
        FileReader fileReader = new FileReader("categories.tsv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            String[] strings = line.split("\t");
            categories.put(strings[0], strings[1]);
        }
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    Purchase purchase = gson.fromJson(in.readLine(), Purchase.class);
                    String answer = countingLogic.counting(purchase);
                    out.println(answer);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
