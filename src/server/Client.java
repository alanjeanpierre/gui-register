package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	PrintWriter out;
	BufferedReader in;
	
	public static void main(String args[]) {
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		new Client(hostname, port);
	}
	
	
	public Client(String hostname, int port) {
		
		try {
			Socket server = new Socket(hostname, port);
			out = new PrintWriter(server.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			
			String fromServer;
			String fromUser;
			Scanner input = new Scanner(System.in);
			
			
			while ((fromServer = in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				if (fromServer.equals("Bye.")) break;

				fromUser = input.nextLine();
				if (fromUser != null) {
					System.out.println("User: " + fromUser);
					out.println(fromUser);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int write(String str) {
		out.println(str);
		try {
			return Integer.parseInt(in.readLine());
		}
		catch (IOException e) {
			return 503;
		}
	}
	
	public String getinv() {
		out.println("getinv");
		try {
			return in.readLine();
		}
		catch (IOException e) {
			return null;
		}
	}
}
