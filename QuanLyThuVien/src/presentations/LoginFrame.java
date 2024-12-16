package presentations;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import business.BStaff;
import entities.Staff;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class LoginFrame extends JDialog {

	private BStaff bStaff;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the dialog.
	 */
	public LoginFrame(FrameManager frame) {
		bStaff = new BStaff();
		LoadImage loader = new LoadImage();
		contentPane = new JPanel(new BorderLayout());
		JLabel lbLogo = new JLabel("");
		lbLogo.setIcon(new ImageIcon(loader.loadImage("lib_logo.png")));
		lbLogo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbLogo, BorderLayout.NORTH);

		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(BorderFactory.createTitledBorder("Đăng nhập"));
		contentPane.add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));

		JLabel lblUsername = new JLabel("Tài Khoản");
		loginPanel.add(lblUsername, "cell 0 0,alignx center,growy");

		/* Hung lv */
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String user = textField.getText();
					Staff temp = null;
					try {
						temp = bStaff.getStaffByUsername(user);
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Driver bị lỗi!");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Lỗi kết nối SQL!");
						e1.printStackTrace();
					}
					if (temp != null) {
						char[] input = passwordField.getPassword();

						if (isPasswordCorrect(input, temp)) {
							dispose();
							frame.management(temp.getId(), temp.getFullname(), temp.getActivated(), temp.getRole());
						} else {
							JOptionPane.showMessageDialog(null, "Sai Tài Khoản hoặc Mật Khẩu. Hãy kiểm tra cẩn thận!",
									"Đã Có Lỗi Xảy Ra!", JOptionPane.ERROR_MESSAGE);
						}

						// Zero out the possible password, for security.
						Arrays.fill(input, '0');

						passwordField.selectAll();
					} else {
						JOptionPane.showMessageDialog(null, "Sai Tài Khoản hoặc Mật Khẩu. Hãy kiểm tra cẩn thận!",
								"Đã Có Lỗi Xảy Ra!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		loginPanel.add(textField, "cell 1 0,growx");
		textField.setColumns(10);

		JLabel lblPassword = new JLabel("Mật Khẩu");
		loginPanel.add(lblPassword, "cell 0 1,alignx center,growy");

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String user = textField.getText();
					Staff temp = null;
					try {
						temp = bStaff.getStaffByUsername(user);
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Driver bị lỗi!");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Lỗi kết nối SQL!");
						e1.printStackTrace();
					}
					if (temp != null) {
						char[] input = passwordField.getPassword();

						if (isPasswordCorrect(input, temp)) {
							dispose();
							frame.management(temp.getId(), temp.getFullname(), temp.getActivated(), temp.getRole());
						} else {
							JOptionPane.showMessageDialog(null, "Sai Tài Khoản hoặc Mật Khẩu. Hãy kiểm tra cẩn thận!",
									"Đã Có Lỗi Xảy Ra!", JOptionPane.ERROR_MESSAGE);
						}

						// Zero out the possible password, for security.
						Arrays.fill(input, '0');

						passwordField.selectAll();
					} else {
						JOptionPane.showMessageDialog(null, "Sai Tài Khoản hoặc Mật Khẩu. Hãy kiểm tra cẩn thận!",
								"Đã Có Lỗi Xảy Ra!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		loginPanel.add(passwordField, "cell 1 1,growx");

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String user = textField.getText();
				Staff temp = null;
				try {
					temp = bStaff.getStaffByUsername(user);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Driver bị lỗi!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Lỗi kết nối SQL!");
					e1.printStackTrace();
				}
				if (temp != null) {
					char[] input = passwordField.getPassword();

					if (isPasswordCorrect(input, temp)) {
						dispose();
						frame.management(temp.getId(), temp.getFullname(), temp.getActivated(), temp.getRole());
					} else {
						JOptionPane.showMessageDialog(null, "Sai Tài Khoản hoặc Mật Khẩu. Hãy kiểm tra cẩn thận!",
								"Đã Có Lỗi Xảy Ra!", JOptionPane.ERROR_MESSAGE);
					}

					// Zero out the possible password, for security.
					Arrays.fill(input, '0');

					passwordField.selectAll();
				} else {
					JOptionPane.showMessageDialog(null, "Sai Tài Khoản hoặc Mật Khẩu. Hãy kiểm tra cẩn thận!",
							"Đã Có Lỗi Xảy Ra!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginPanel.add(btnLogin, "flowx,cell 1 2");

		/* end */

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		loginPanel.add(btnCancel, "cell 1 2");

		JLabel lblRegister = new JLabel("Đăng ký tài khoản (chỉ dành cho Nhân Viên Thư Viện)");
		loginPanel.add(lblRegister, "cell 0 4,alignx trailing,aligny center");

		JButton btnReg = new JButton("Đăng Ký");
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				RegisterFrame reg = new RegisterFrame();
				reg.setLocationRelativeTo(null);
				reg.setVisible(true);
			}
		});
		loginPanel.add(btnReg, "cell 1 4,growx");

		JLabel label = new JLabel("Phần mềm được phát triển bởi nhóm");
		contentPane.add(label, BorderLayout.SOUTH);
		setContentPane(contentPane);
		pack();
	}

	private static boolean isPasswordCorrect(char[] input, Staff temp) {
		boolean isCorrect = true;

		char[] correctPassword = temp.getPassword().toCharArray();

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}

		Arrays.fill(correctPassword, '0');

		return isCorrect;
	}

}
