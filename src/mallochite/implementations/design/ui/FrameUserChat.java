/*
 * Joseph Escober
 * FrameUserChat.java
 */


package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;

public class FrameUserChat extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void newUserChatScreen(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameUserChat frame = new FrameUserChat();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameUserChat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 100, 0));
		contentPane.setBorder(new LineBorder(new Color(0, 128, 0), 4));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBackground(new Color(60, 179, 113));
		panel.setBounds(207, 43, 583, 557);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea txtChatArea = new JTextArea();
		txtChatArea.setBorder(new LineBorder(new Color(0, 100, 0), 3));
		txtChatArea.setBounds(10, 479, 445, 58);
		panel.add(txtChatArea);
		
		JButton btnSendMsg = new JButton("SEND");
		btnSendMsg.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btnSendMsg.setForeground(Color.WHITE);
		btnSendMsg.setBackground(new Color(0, 100, 0));
		btnSendMsg.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSendMsg.setBounds(453, 479, 106, 58);
		panel.add(btnSendMsg);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBackground(new Color(60, 179, 113));
		textArea_1.setBounds(10, 10, 563, 459);
		panel.add(textArea_1);
		
		JButton btnAddNew = new JButton("+");
		btnAddNew.setToolTipText("Add New Member");
		btnAddNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
			       FrameAddMember.newAddMemberScreen(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnAddNew.setForeground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnAddNew.setForeground(Color.BLACK);
			}
		});
		btnAddNew.setBackground(new Color(60, 179, 113));
		btnAddNew.setBorder(new TitledBorder(new CompoundBorder(new LineBorder(new Color(171, 173, 179)), new EmptyBorder(2, 2, 2, 2)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAddNew.setBounds(10, 559, 38, 31);
		contentPane.add(btnAddNew);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		textArea.setBackground(new Color(60, 179, 113));
		textArea.setForeground(Color.YELLOW);
		textArea.setBounds(10, 43, 187, 427);
		contentPane.add(textArea);
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "confirmation", JOptionPane.YES_NO_OPTION) == 0)
					 FrameUserChat.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblClose.setForeground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblClose.setForeground(Color.WHITE);
			}
		});
		lblClose.setForeground(Color.WHITE);
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblClose.setBounds(776, 0, 24, 35);
		contentPane.add(lblClose);
		
		JLabel lblFriendName = new JLabel("Talking with a Friend");
		lblFriendName.setBounds(210, 3, 556, 31);
		contentPane.add(lblFriendName);
		lblFriendName.setForeground(Color.WHITE);
		lblFriendName.setFont(new Font("Tahoma", Font.BOLD, 16));
	}
}
