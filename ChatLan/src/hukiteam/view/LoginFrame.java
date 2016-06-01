package hukiteam.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame {
	private JFrame frame;
	private final int WIDTH = 300, HEIGHT = 300;
	
	private JLabel lname, lMsg;
	private JLabel msg;
	private JTextField txtName, txtPort, txtIP;
	private JButton btnLogin;
	
	public LoginFrame() {
		frame = new JFrame("Login");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		JPanel pTemp = new JPanel();
		pTemp.setPreferredSize(new Dimension(WIDTH, 40));
		frame.add(pTemp);
		
		lMsg = new JLabel("");
		lMsg.setPreferredSize(new Dimension(WIDTH, 50));
		lMsg.setHorizontalAlignment(JLabel.CENTER);
		
		txtIP = new JTextField(7);
		txtPort = new JTextField(6);
		
		lname = new JLabel("Name");
		
		txtName = new JTextField(20);
		
		msg = new JLabel("");
		frame.add(msg);
		
		btnLogin = new JButton("Login");
		
		frame.add(lMsg);
		frame.add(new JLabel("Ip Address"));
		frame.add(txtIP);
		frame.add(new JLabel("Port"));
		frame.add(txtPort);
		frame.add(lname);
		frame.add(txtName);
		frame.add(btnLogin);
		frame.setVisible(true);
	}
	
	// =============================
	
	public void addLoginActionLitener(ActionListener actionListener) {
		this.btnLogin.addActionListener(actionListener);
	}
	
	public String getNameText() {
		return this.txtName.getText();
	}
	
	public void setMessage(String msg) {
		this.lMsg.setText(msg);
	}
	
	public void dispose() {
		this.frame.dispose();
	}
}
