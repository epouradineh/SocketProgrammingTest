import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Scanner;

public class ClientApp implements Serializable{
	
	class InnterTest{
		String name = "Ebrahim";
		int age = 36;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
		
	}

	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost",8090);DataInputStream socketIn = new DataInputStream(socket.getInputStream());
				DataOutputStream socketOut = new DataOutputStream(socket.getOutputStream());Scanner systemScannerIn = new Scanner(System.in);
				ObjectOutputStream obout = new ObjectOutputStream(socketOut);){
			InnterTest inertest = null;
			String inputString = null;
			String outString = null;
			do {
				outString = systemScannerIn.nextLine();
				socketOut.writeUTF(outString);
				socketOut.flush();
				obout.writeObject(inertest);
				if(outString.equals("exit")) {
					break;
				}
				inputString = socketIn.readUTF();
				System.out.println("Server : "+inputString);
				if(inputString.equals("exit"))
					break;
								
			}while(true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}