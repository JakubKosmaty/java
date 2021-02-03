import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PasswordCracker implements PasswordCrackerInterface {
    private final StringBuilder password = new StringBuilder();
    private int saveIndex = 48;

    private boolean connectToServer(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            in.readLine();
            out.println("Program");
            out.flush();

            in.readLine();
            in.readLine();
            in.readLine();

            String temp = String.format("%s%c", password.toString(), (char) saveIndex);
            out.println(temp);
            out.flush();

            String line = in.readLine();
            if ("+OK".equals(line)) {
                password.append((char) saveIndex);
                saveIndex = 48;

                socket.setSoTimeout(0);
                line = in.readLine();
                return "Access granted".equals(line);
            } else {
                saveIndex += 1;
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getPassword(String host, int port) {
        while (!connectToServer(host, port)) {}
        return password.toString();
    }
}
