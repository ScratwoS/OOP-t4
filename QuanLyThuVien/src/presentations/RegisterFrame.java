package presentations;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import business.BRole;
import business.BStaff;
import entities.Staff;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class RegisterFrame extends JDialog {
	private JPanel contentPane;
	private JTextField tfUser;
	private JPasswordField passwordField;
	private JPasswordField retypeField;
	private JTextField tfFullname;
	private JTextField tfEmail;
	private JComboBox<String> comboBox;
	private JLabel valUser, valPass;
	private JLabel lblDes;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private LoadImage loader = new LoadImage();
	private ImageIcon t = new ImageIcon(loader.loadImage("true.png")), f = new ImageIcon(loader.loadImage("false.png"));
	private BStaff bStaff = new BStaff();
	private JButton btnReg;
	private BRole bRole = new BRole();
	private DefaultComboBoxModel<String> roleModel;
	public RegisterFrame() {
		
		contentPane = new JPanel(new BorderLayout());
		JLabel lbLogo = new JLabel("");
		lbLogo.setIcon(new ImageIcon(loader.loadImage("lib_logo.png")));
		lbLogo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbLogo, BorderLayout.NORTH);
		setContentPane(contentPane);

		JPanel regPanel = new JPanel();
		contentPane.add(regPanel, BorderLayout.CENTER);
		regPanel.setLayout(new MigLayout("", "[][grow][]", "[][][][][][][grow][][][]"));

		JLabel lblUser = new JLabel("Tài Khoản");
		regPanel.add(lblUser, "cell 0 0,alignx trailing");

		tfUser = new JTextField();
		tfUser.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					if(bStaff.hasUser(tfUser.getText())){
						valUser.setIcon(f);
						valUser.setToolTipText("Tài Khoản đã tồn tại!");
						btnReg.setEnabled(false);
					}else{
						valUser.setIcon(t);
						btnReg.setEnabled(true);
						valUser.setToolTipText("Bạn có thể dùng Username này!");
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				
			}
		});
		regPanel.add(tfUser, "cell 1 0,growx");
		tfUser.setColumns(10);

		valUser = new JLabel("");
		regPanel.add(valUser, "cell 2 0");

		JLabel lblPass = new JLabel("Mật Khẩu");
		regPanel.add(lblPass, "cell 0 1,alignx trailing");

		passwordField = new JPasswordField();
		regPanel.add(passwordField, "cell 1 1,growx");

		JLabel lblRetype = new JLabel("Nhập lại Mật Khẩu");
		regPanel.add(lblRetype, "cell 0 2,alignx trailing");

		retypeField = new JPasswordField();
		retypeField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if(passwordField.getText().equals(retypeField.getText())){
					valPass.setIcon(t);
					btnReg.setEnabled(true);
				}else{
					btnReg.setEnabled(false);
					valPass.setIcon(f);
					valPass.setToolTipText("Mật Khẩu nhập lại không khớp với Mật Khẩu ở trên");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		regPanel.add(retypeField, "cell 1 2,growx");

		valPass = new JLabel("");
		regPanel.add(valPass, "cell 2 2");

		JLabel lblFullname = new JLabel("Họ và Tên");
		regPanel.add(lblFullname, "cell 0 3,alignx trailing");

		tfFullname = new JTextField();
		regPanel.add(tfFullname, "cell 1 3,growx");
		tfFullname.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		regPanel.add(lblEmail, "cell 0 4,alignx trailing");

		tfEmail = new JTextField();
		regPanel.add(tfEmail, "cell 1 4,growx");
		tfEmail.setColumns(10);

		JLabel lblRole = new JLabel("Chức Vụ");
		regPanel.add(lblRole, "cell 0 5,alignx trailing");

		comboBox = new JComboBox<String>();
		regPanel.add(comboBox, "cell 1 5,growx");

		btnReg = new JButton("Đăng Ký");
		btnReg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfUser.getText().equals("")|| tfEmail.getText().equals("")|| tfFullname.getText().equals("")
						|| passwordField.getPassword().equals("")|| retypeField.getPassword().equals(""))
					JOptionPane.showMessageDialog(null,
							"Bạn phải điền hết thông tin trong form. Hãy kiểm tra cẩn thận!", "Đã Có Lỗi Xảy Ra!",
							JOptionPane.ERROR_MESSAGE);
				else {
					
					Staff staff = new Staff();
					staff.setRole(comboBox.getSelectedIndex()+1);
					staff.setUsername(tfUser.getText());
					staff.setPassword(passwordField.getText());
					staff.setEmail(tfEmail.getText());
					staff.setFullname(tfFullname.getText());
					staff.setDescription(textArea.getText());
					try {
						bStaff.insertUser(staff);
						JOptionPane.showMessageDialog(null, "Đăng Ký thành công! Giờ bạn có thể đăng nhập, liên hệ với admin để có thể kích hoạt tài khoản và sử dụng","Successful!",JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});

		lblDes = new JLabel("Mô Tả");
		regPanel.add(lblDes, "cell 0 6");

		scrollPane = new JScrollPane();
		regPanel.add(scrollPane, "cell 1 6,grow");

		textArea = new JTextArea();
		textArea.setRows(3);
		scrollPane.setViewportView(textArea);
		regPanel.add(btnReg, "flowx,cell 1 7");

		JButton btnCancel = new JButton("Hủy");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		regPanel.add(btnCancel, "cell 1 7");
		try {
			roleModel = bRole.getAllRolesToModel();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox.setModel(roleModel);
		setSize(320,450);
	}

}
