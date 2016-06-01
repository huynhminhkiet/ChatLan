package hukiteam.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hukiteam.view.LoginFrame;

public class LoginController {
	private LoginFrame frame = new LoginFrame();
	
	public LoginController() {
		frame.addLoginActionLitener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = frame.getNameText();
				if (name == null || "".equals(name.trim())) {
					frame.setMessage("Input name!");
				} else {
					new ChatRoomController(name);
				}
			}
		});
	}
}
