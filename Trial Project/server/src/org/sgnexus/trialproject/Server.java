package org.sgnexus.trialproject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
	static final private int PORT = 55555;
	static boolean VERBOSE_ENABLED = true;
	static final private int MAX_PACKET_SIZE = 100;
	static final private int PLAYER_COUNT = 2;
	static private int score[] = new int[PLAYER_COUNT];
	static private DatagramSocket socket;
	static private DatagramPacket packet;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		run();
	}

	private static void testConsole() {
		clearScreen();
		println("*");
		println("****");
		pause(1000);

		clearScreen();
		println("**");
		println("****");
		pause(1000);

		clearScreen();
		println("***");
		println("****");
		pause(1000);

		clearScreen();
		println("****");
		println("****");
		pause(1000);
	}

	private static void println(String message) {
		System.out.println(message);
	}

	private static void clearScreen() {
		System.out.print("\u001b[2J");
		System.out.flush();
	}

	private static void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void addScore(int playerId) {
		score[playerId]++;
	}

	private static void resetScore() {
		for (int i : score) {
			score[i] = 0;
		}
	}

	private static void refreshScreen() {
		// TODO
	}

	private static void listen() {
		try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		println("received packet");
	}

	private static void init() {
		// Create socket
		try {
			socket = new DatagramSocket(PORT);
			verbose("Connected to port: " + socket.getLocalPort());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Setup receive buffer
		byte[] buffer = new byte[MAX_PACKET_SIZE];
		packet = new DatagramPacket(buffer, buffer.length);
	}
	
	private static void run() {
		init();
		
		boolean game_over = false;
		
		while(!game_over) {
			listen();
			game_over = true;
		}
	}

	private static void verbose(String message) {
		if (VERBOSE_ENABLED) {
			System.out.println(message);
		}
	}
}
