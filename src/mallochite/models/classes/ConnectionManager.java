package mallochite.models.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class ConnectionManager extends Thread {
	private Socket metaSocket; // responsible for listening for incoming connections
	private MallochiteMessageManager mallochiteMessageManager = new MallochiteMessageManager();
	private User thisUser;
	private boolean metaSocketAvailable;

	private boolean debugging = false;

	public ConnectionManager() {
		this.metaSocketAvailable = false; // some method require the metaSocket so throw an exception if
											// metaSocketAvailable is false
	}

	public ConnectionManager(Socket socket) throws IOException, NoSuchAlgorithmException {
		this.metaSocket = socket;
		metaSocketAvailable = true;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		String localIpAddress = thisUser.getIP();
		String thisUserUuid = String.valueOf(thisUser.getUUID());
		boolean listening = true;

		try {
			if (debugging) {
				System.out.println("Received a message");
			}

			// must initialize in and out within a try catch in case of IOException
			in = new BufferedReader(new InputStreamReader(this.metaSocket.getInputStream()));
			out = new PrintWriter(this.metaSocket.getOutputStream());
			String messageIn = "";
			String messageOut = "";
			String terminatingString = "RECEIVED";

			while (listening) {

				// move into method to clean up code?
				if (messageIn != null && messageIn != "") {
					// validate message

					HashMap<String, String> parsedData = mallochiteMessageManager.parseDataFromHeader(messageIn);
					thisUser.addMessageToConversation(parsedData.get("UUID"), messageIn);

					messageOut = mallochiteMessageManager.messageRecievedReply(thisUserUuid, localIpAddress);

					out.println(messageOut);
					out.flush();
				}

				if (messageOut != null && messageOut.contains(terminatingString)) {
					listening = false;
				}

				messageIn = in.readLine();

				if (messageIn != null) {
					if (debugging) {
						System.out.println(messageIn);
					}
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				in.close();
				this.metaSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			out.close();
			this.interrupt();

			if (debugging) {
				System.out.println("Connection closed");
			}
		}
	}

	/*
	 * Creates a socket with the intent of sending a "GREET" header with public key
	 * Waits for response from socket and reacts accordingly
	 */
	public void sendMessage(User userToContact, String messageToSend) throws IOException {
		
		if ( this.debugging )
		{
			System.out.println("opening socket to send message");
		}
		
		Socket socket = new Socket(userToContact.getIP(), userToContact.getPort());
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		String messageIn = "";

		try {

			String messageToSendFormated = mallochiteMessageManager.formatMessageToSend(this.thisUser.getIP(),
					this.thisUser.getUUID(), messageToSend);

			out.println(messageToSendFormated);
			out.flush();
			System.out.println("message sent");

			while (true) {
				if (messageIn != null && messageIn != "") {
					if (debugging) {
						System.out.println(messageIn);
					}

					if (messageIn.contains("RECEIVED")) {
						break;
					}
				}

				messageIn = in.readLine();

			}

		} catch (Exception ex) {
			throw ex;
		}

		finally {
			if ( this.debugging )
			{
				System.out.println("closing socket for sending messages");
			}
			socket.close();
			in.close();
			out.close();
		}
	}

	public Socket getMetaSocket() {
		return metaSocket;
	}

	public void setMetaSocket(Socket metaSocket) throws IOException {
		this.metaSocket = metaSocket;
		this.metaSocketAvailable = true;
	}

	public User getThisUser() {
		return thisUser;
	}

	public void setThisUser(User thisUser) {
		this.thisUser = thisUser;
	}

}
