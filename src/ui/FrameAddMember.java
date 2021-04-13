/*
 * Joseph Escober
 */

package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class FrameAddMember extends JFrame {
	private JPanel contentPane;
	private JTextField txtUniqueId;
	/**
	 * Launch the application.
	 */
	public static void newAddMemberScreen (String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAddMember frame = new FrameAddMember();
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
	public FrameAddMember() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 400, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 100, 0));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(50, 205, 50), new Color(50, 205, 50), null, null));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 3));
		panel.setBounds(65, 164, 264, 53);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtUniqueId = new JTextField();
		txtUniqueId.setBorder(null);
		txtUniqueId.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtUniqueId.setText("unique id");
		txtUniqueId.setBounds(10, 10, 244, 33);
		panel.add(txtUniqueId);
		txtUniqueId.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBounds(65, 250, 264, 115);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea txtMessageToNew = new JTextArea();
		txtMessageToNew.setBorder(null);
		txtMessageToNew.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtMessageToNew.setText("message to new member...");
		txtMessageToNew.setBounds(10, 10, 244, 95);
		panel_1.add(txtMessageToNew);
		
		JPanel pnlBtnAddNew = new JPanel();
		pnlBtnAddNew.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		pnlBtnAddNew.setBackground(new Color(60, 179, 113));
		pnlBtnAddNew.setBounds(65, 409, 264, 63);
		contentPane.add(pnlBtnAddNew);
		pnlBtnAddNew.setLayout(null);
		
		JLabel lblConnect = new JLabel("Connect");
		lblConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrameAddMember.this.dispose();			
			       FrameUserChat.newUserChatScreen(null);			
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblConnect.setForeground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblConnect.setForeground(Color.BLACK);
			}
		});
		lblConnect.setForeground(new Color(0, 0, 0));
		lblConnect.setFont(new Font("Arial", Font.BOLD, 24));
		lblConnect.setBounds(81, 10, 152, 43);
		pnlBtnAddNew.add(lblConnect);
		
		JLabel lblAddNewMember = new JLabel("Add New Member");
		lblAddNewMember.setForeground(Color.WHITE);
		lblAddNewMember.setFont(new Font("Arial", Font.BOLD, 30));
		lblAddNewMember.setBounds(64, 21, 287, 53);
		contentPane.add(lblAddNewMember);
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "confirmation", JOptionPane.YES_NO_OPTION) == 0)
					 FrameAddMember.this.dispose();
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
		lblClose.setBounds(376, 0, 24, 35);
		contentPane.add(lblClose);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(60, 179, 113));
		panel_2.setBounds(0, 84, 400, 20);
		contentPane.add(panel_2);
	}
	
	public String getTxtUniqueId() {
		return txtUniqueId.getText();
	}
}
