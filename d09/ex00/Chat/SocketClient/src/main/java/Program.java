import java.io.*;
import java.net.Socket;

public class Program {

    public static void main(String[] args) throws IOException {
        int port = parsePort(args);
        Socket socket = new Socket("127.0.0.1", port);

        InputStreamReader reader = new InputStreamReader(socket.getInputStream());
        OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
        InputStreamReader stdinReader = new InputStreamReader(System.in);
        while (true) {
            int c;
            while ((c = reader.read()) != 1) {
                if (c == -1)
                    System.exit(0);
                System.out.print((char)c);
            }
            while ((c = stdinReader.read()) != '\n')
                writer.write(c);
            writer.write(c);
            writer.flush();
        }
    }

    private static int parsePort(String[] args) {
        String argName = "--server-port=";
        String message = "Expected 1 argument (--server-port=)";
        if (args.length != 1 || !args[0].startsWith(argName)) {
            System.err.println(message);
            System.exit(1);
        }
        return Integer.parseInt(args[0].substring(argName.length()));
    }
}
