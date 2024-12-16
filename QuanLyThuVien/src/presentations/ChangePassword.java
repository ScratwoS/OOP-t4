package presentations;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import business.BStaff;
import entities.Staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class ChangePassword extends JDialog {
	private JPasswordField oldPass;
	private JPasswordField newPass;
	private JPasswordField retypePass;
	private LoadImage loader = new LoadImage();
	private ImageIcon t = new ImageIcon(loader.loadImage("true.png")), f = new ImageIcon(loader.loadImage("false.png"));

	public ChangePassword(int id) {
		setSize(300,174);
		setModal(true);
		setTitle("Đổi Mật Khẩu");
		getContentPane().setLayout(new MigLayout("", "[][grow][]", "[][][][]"));

		JLabel lblOld = new JLabel("Mật Khẩu Cũ");
		getContentPane().add(lblOld, "cell 0 0,alignx trailing");

		oldPass = new JPasswordField();
		getContentPane().add(oldPass, "cell 1 0,growx");

		JLabel lblNew = new JLabel("Mật Khẩu Mới");
		getContentPane().add(lblNew, "cell 0 1,alignx trailing");

		newPass = new JPasswordField();
		getContentPane().add(newPass, "cell 1 1,growx");

		JLabel lblRetype = new JLabel("Nhập Lại Mật Khẩu Mới");
		getContentPane().add(lblRetype, "cell 0 2,alignx trailing");

		JLabel valNew = new JLabel("");
		getContentPane().add(valNew, "cell 2 2");

		retypePass = new JPasswordField();

		getContentPane().add(retypePass, "cell 1 2,growx");

		JButton btnOk = new JButton("Xác Nhận");
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BStaff bstaff = new BStaff();
				String password = null;
				try {
					password = bstaff.getStaffByID(id).getPassword();
				} catch (ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if (isPasswordCorrect(oldPass.getPassword(), password)) {
					Staff staff = new Staff();
					staff.setId(id);
					staff.setPassword(newPass.getText());
					try {
						bstaff.updatePassword(staff);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Sai Mật Khẩu. Hãy kiểm tra cẩn thận!",
			                "Đã Có Lỗi Xảy Ra!",
			                JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		getContentPane().add(btnOk, "flowx,cell 1 3");

		JButton btnCancel = new JButton("Hủy");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnCancel, "cell 1 3");
		retypePass.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (newPass.getText().equals(newPass.getText())) {
					valNew.setIcon(t);
					btnOk.setEnabled(true);
				} else {
					btnOk.setEnabled(false);
					valNew.setIcon(f);
					valNew.setToolTipText("Mật Khẩu nhập lại không khớp với Mật Khẩu ở trên");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private static boolean isPasswordCorrect(char[] input, String password) {
		boolean isCorrect = true;

		char[] correctPassword = password.toCharArray();

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}

		Arrays.fill(correctPassword, '0');

		return isCorrect;
	}
}
