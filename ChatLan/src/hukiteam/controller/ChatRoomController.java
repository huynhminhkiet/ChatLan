package hukiteam.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

		frame.addSendActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String msg = frame.getMessageText();
				
				if (msg != null && !"".equals(msg.trim())) {
					frame.setChatAreaText(frame.getChatAreaText() + "\n► " 
							+ frame.getNickName() + ": " + msg);

					try {
						dos.writeUTF("Msg," + msg);
					} catch (IOException e1) {
						frame.dispose();
						new LoginController();
					}
					frame.setMessageInput("");
				}
			}
		});
		
		frame.addUpdateOLActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					dos.writeUTF("Update,");
				} catch (IOException e1) {
					frame.dispose();
					new LoginController();
				}
			}
		});
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
						this.cr.setChatAreaText(cr.getChatAreaText() + "\n" + msg);
					if (cmd.equals("Joiner")) {
						if (!msg.equals(null)) {
							String [] onlineList = msg.split(";");
							String onlineListStr = "<html>";
							for (int i = 0; i < onlineList.length; i++) {
								onlineListStr += onlineList[i] + "<br>";
								
							}
							frame.setOnlineList(onlineListStr);
						}
					}

				}
			} catch (IOException e) {
				cr.dispose();
				new LoginController();
			}
		}
	}

}
