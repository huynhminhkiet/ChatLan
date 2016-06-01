package hukiteam.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	public final static int PORT = 5000;

	public Vector<ServerThreadedHandler> cls = new Vector<ServerThreadedHandler>();

	public Server() {
		ServerSocket theServer;
		Socket theConnection;

		try {
			theServer = new ServerSocket(PORT);
			System.out.println("Server openned!");
			while (true) {
				theConnection = theServer.accept();
				System.out.print("Have new joiner:");
				new ServerThreadedHandler(this, theConnection).start();
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public static void main(String[] args) {
		new Server();
	}

	class ServerThreadedHandler extends Thread {
		Server crsv;
		public Socket incoming;
		public DataInputStream dis;
		public DataOutputStream dos;

		public String name;

		public ServerThreadedHandler(Server crsv, Socket i) {
			this.crsv = crsv;
			this.incoming = i;
			try {
				this.dis = new DataInputStream(incoming.getInputStream());
				this.dos = new DataOutputStream(incoming.getOutputStream());
			} catch (IOException e) {
			}
		}

		public String getOnlineList() {
			String joiner = "";

			for (int i = 0; i < this.crsv.cls.size(); i++) {
				ServerThreadedHandler temp = this.crsv.cls.get(i);

				joiner += "► " + temp.name + ";";
			}

			return joiner;
		}

		@Override
		public void run() {
			super.run();

			String ch = "";
			try {
				ch = dis.readUTF();

				String cmd = ch.substring(0, ch.indexOf(","));
				String msg = ch.substring(ch.indexOf(",") + 1);

				if (!cmd.equals("Join"))
					incoming.close();
				System.out.println(msg);
				this.name = msg;
				this.crsv.cls.add(this);

				// danh sach nguoi online
				for (int i = 0; i < this.crsv.cls.size(); i++) {
					ServerThreadedHandler temp = this.crsv.cls.get(i);
					temp.dos.writeUTF("Joiner," + getOnlineList());
				}

				// Nhận và gửi thông điệp
				while (true) {
					ch = dis.readUTF();
					cmd = ch.substring(0, ch.indexOf(","));
					msg = ch.substring(ch.indexOf(",") + 1);
					if (cmd.equals("Msg")) {
						for (int i = 0; i < this.crsv.cls.size(); i++) {
							ServerThreadedHandler temp = this.crsv.cls.get(i);
							if (temp != this) {
								temp.dos.writeUTF("Msg,► " + this.name + ": "
										+ msg);
							}
						}
					} else if (cmd.equals("Update")) {
						for (int i = 0; i < this.crsv.cls.size(); i++) {
							ServerThreadedHandler temp = this.crsv.cls.get(i);
							temp.dos.writeUTF("Joiner," + getOnlineList());
						}
					} else {
						incoming.close();
						this.crsv.cls.remove(this);

					}
				}

			} catch (IOException e) {
				crsv.cls.remove(this);
			}
		}

	}
}
