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
	Socket server;
	
	public static void main(String args[]) {
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		new Client(hostname, port);
	}
	
	
	public Client(String hostname, int port) {
		
		try {
			server = new Socket(hostname, port);
			out = new PrintWriter(server.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			in.readLine();
			
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
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


	public boolean auth(String user) {
		out.println("auth|" + user);
		int returnCode;
		try {
			returnCode = Integer.parseInt(in.readLine());
			return returnCode == 200;
		}
		catch (IOException e) {
			return false;
		}
	}
}
