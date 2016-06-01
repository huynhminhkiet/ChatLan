package hukiteam.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


import hukiteam.view.ChatRoomFrame;

public class ChatRoomController {
	private ChatRoomFrame frame;
	
	private Socket soc;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public ChatRoomController(String name) {
		frame = new ChatRoomFrame(name);
		
		try {
			soc = new Socket("localhost", 5000);
			this.dis = new DataInputStream(soc.getInputStream());
			this.dos = new DataOutputStream(soc.getOutputStream());
			new ClientThreadedHandler(frame).start();
			this.dos.writeUTF("Join," + frame.getNickName());
			
		} catch (IOException e) {
			this.frame.dispose();
		}
	}
	
	
	public class ClientThreadedHandler extends Thread {
		ChatRoomFrame cr;
		public ClientThreadedHandler(ChatRoomFrame cr) {
			this.cr = cr;
		}
		
		@Override
		public void run() {
			super.run();

			String ch = "";
			try {

				while (true) {
					ch = dis.readUTF();
					String cmd = ch.substring(0, ch.indexOf(","));
					String msg = ch.substring(ch.indexOf(",") + 1);
					if (cmd.equals("Msg"))
						this.cr.setChatAreaText(msg + "\n" + cr.getChatAreaText());
					
				}
			} catch (IOException e) {
				cr.dispose();
				new LoginController();
				}
		}
	}

}
