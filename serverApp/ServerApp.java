import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class ServerApp {

	public static void main(String[] args) {
		
		try(ServerSocket serverSocket = new ServerSocket(8090);Socket socket = serverSocket.accept();
				DataInputStream socketIn = new DataInputStream(socket.getInputStream());
				DataOutputStream socketOut = new DataOutputStream(socket.getOutputStream());
				ObjectInputStream obIn = new ObjectInputStream(socketIn);
						Scanner serverScanner = new Scanner(System.in);){
			String inputString = null;
			String outString = null;
			do {
				inputString = socketIn.readUTF();
				System.out.println("Client :"+inputString);
				
				if(inputString.equals("exit"))
					break;
				outString = serverScanner.nextLine();
				socketOut.writeUTF(outString);
				socketOut.flush();
				if(outString.equals("exit"))
					break;
				
			}while(true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}