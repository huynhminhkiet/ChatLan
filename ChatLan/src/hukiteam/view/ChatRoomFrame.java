package hukiteam.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class ChatRoomFrame {
	private JFrame frame;
	private final int WIDTH = 500, HEIGHT = 420;
	
	private JPanel pJoiners, pInput;
	private JTextArea taRoom;
	private JTextField txtMessage;
	private JButton btnSend, btnUpdateOnlList;
	private JLabel lNickName, lOnlineList;
	
	private String nickName;

	public ChatRoomFrame(String nickName) {
		this.nickName = nickName;
		
		this.frame = new JFrame("Chat Room!");
		this.frame.setSize(WIDTH, HEIGHT);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new FlowLayout());
		
		// chat area panel
		taRoom = new JTextArea();
		taRoom.setPreferredSize(new Dimension(WIDTH - 180,  HEIGHT - 100));
		
		// joners panel
		pJoiners = new JPanel(new FlowLayout());
		pJoiners.setBackground(Color.LIGHT_GRAY);
		pJoiners.setPreferredSize(new Dimension(130, HEIGHT - 100));
		
		lNickName = new JLabel(this.nickName);
		lNickName.setPreferredSize(new Dimension(130, 30));
		lNickName.setHorizontalAlignment(JLabel.CENTER);
		
		lOnlineList = new JLabel("Nobody");
		
		
		JLabel lOnline = new JLabel("Online:");
		lOnline.setPreferredSize(new Dimension(130, 30));
		lOnline.setHorizontalAlignment(JLabel.CENTER);
		
		btnUpdateOnlList = new JButton("Refresh");
		btnUpdateOnlList.setPreferredSize(new Dimension(130, 20));
		
		pJoiners.add(new JLabel("Me:"));
		pJoiners.add(lNickName);
		pJoiners.add(lOnline);
		pJoiners.add(lOnlineList);
		pJoiners.add(btnUpdateOnlList);
		
		// input message panel
		pInput = new JPanel(new FlowLayout());
		pInput.setBackground(Color.LIGHT_GRAY);
		pInput.setPreferredSize(new Dimension(WIDTH - 44, 40));
		
		txtMessage = new JTextField();
		txtMessage.setPreferredSize(new Dimension(300, 25));
		btnSend = new JButton("Send");
		
		pInput.add(txtMessage);
		pInput.add(btnSend);
		
		// show
		frame.add(taRoom);
		frame.add(pJoiners);
		frame.add(pInput);
		frame.setVisible(true);	
	}
	
	// =======================================
	
	public void addSendActionListener(ActionListener actionListener) {
		this.btnSend.addActionListener(actionListener);
	}
	
	public void addUpdateOLActionListener(ActionListener actionListener) {
		this.btnUpdateOnlList.addActionListener(actionListener);
	}
	
	public String getNickName() {
		return this.nickName;
	}
	
	public void setChatAreaText(String value) {
		this.taRoom.setText(value);
	}
	
	public String getChatAreaText() {
		return this.taRoom.getText();
	}
	
	public void dispose() {
		this.frame.dispose();
	}
	
	public String getMessageText() {
		return this.txtMessage.getText();
	}
	
	public void setMessageInput(String value) {
		this.txtMessage.setText(value);
	}
	
	public void setOnlineList(String value) {
		this.lOnlineList.setText(value);
	} 
}
