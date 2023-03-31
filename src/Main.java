import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Socket socket = null;  // Declare a Socket object
        InputStreamReader inputStreamReader = null;  // Declare an InputStreamReader object
        OutputStreamWriter outputStreamWriter = null;  // Declare an OutputStreamWriter object
        BufferedReader bufferedReader = null;  // Declare a BufferedReader object
        BufferedWriter bufferedWriter = null;  // Declare a BufferedWriter object

        try {
            // Create a new Socket object that connects to the server on the local machine on port 4321
            socket = new Socket("localhost", 4321);
            // Create an InputStreamReader object that reads from the socket's input stream
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            // Create an OutputStreamWriter object that writes to the socket's output stream
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            // Create a BufferedReader object that reads from the InputStreamReader object
            bufferedReader = new BufferedReader(inputStreamReader);
            // Create a BufferedWriter object that writes to the OutputStreamWriter object
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            // Create a Scanner object to read input from the user
            Scanner scanner = new Scanner(System.in);

            // Loop indefinitely
            while (true) {
                // ask the user to enter a message
                System.out.println("Vill du hämta ut information?");
                String message = scanner.nextLine();

                // If the user enters "ja" sout next question
                if (message.equals("ja")) {
                    System.out.println("Vilken spelare vill du hämta info om?");
                    System.out.println("Drogba , Zidane , Iniesta, ");
                    String player = scanner.nextLine();

                    // If the player's name matches one of the predefined names
                    if (player.equalsIgnoreCase("Drogba") || player.equalsIgnoreCase("Zidane") || player.equalsIgnoreCase("Iniesta")) {
                        // Write the player's name to the socket's output stream
                        bufferedWriter.write(player);
                        // Add a new line to the output stream
                        bufferedWriter.newLine();
                        // Flush the output stream to send the message to the server
                        bufferedWriter.flush();
                        // Read the server's response from the input stream and print it
                        System.out.println(bufferedReader.readLine());
                    }
                }
            }
        } catch (Exception e) {
            // If an exception occurs, print the error message
            System.out.println(e);
        } finally {
            // Close all the resources in the reverse order of their creation
            try {
                if (socket != null) {
                    socket.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (Exception e) {
                // If an exception occurs, print the error message
                e.printStackTrace();
            }
        }
    }
}