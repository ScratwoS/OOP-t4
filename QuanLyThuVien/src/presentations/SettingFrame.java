package presentations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dataaccess.DAConnection;
import net.miginfocom.swing.MigLayout;

public class SettingFrame extends JDialog {

	private JPanel contentPane;
	private JTextField tfUrl;
	private JTextField tfUsername;
	private JPasswordField tfPassword;

	private Scanner sc;
	/**
	 * Create the dialog.
	 */
	public SettingFrame() {
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		File file = new File("src/text/db.txt");
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		JLabel lblDatabaseUrl = new JLabel("Database URL");
		contentPane.add(lblDatabaseUrl, "cell 0 0,alignx trailing");
		tfUrl = new JTextField();
		if (sc.hasNextLine()) {
			tfUrl.setText(sc.nextLine());
		}
		contentPane.add(tfUrl, "cell 1 0,growx");
		tfUrl.setColumns(10);

		JLabel lblDatabaseUsername = new JLabel("Database Username");
		contentPane.add(lblDatabaseUsername, "cell 0 1,alignx trailing");

		tfUsername = new JTextField();
		if (sc.hasNextLine()) {
			tfUsername.setText(sc.nextLine());
		}

		contentPane.add(tfUsername, "cell 1 1,growx");
		tfUsername.setColumns(10);

		JLabel lblDatabasePassword = new JLabel("Database Password");
		contentPane.add(lblDatabasePassword, "cell 0 2,alignx trailing");

		tfPassword = new JPasswordField();
		if (sc.hasNextLine()) {
			tfPassword.setText(sc.nextLine());
		}
		sc.close();
		contentPane.add(tfPassword, "cell 1 2,growx,aligny top");
		tfPassword.setColumns(10);

		JButton btnOk = new JButton("Xác Nhận");
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Writer writer = new BufferedWriter(new FileWriter(file));
					writer.write(tfUrl.getText() + '\n' + tfUsername.getText() + '\n' + tfPassword.getText());
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				try {
					DAConnection.getConnection();
				} catch (ClassNotFoundException | SQLException e3) {
					JOptionPane.showMessageDialog(null, "Hệ thống đã cố gắng kết nối tới database của bạn nhưng không thành công, hãy kiểm tra để chắc chắn những thông tin bạn nhập vào là đúng hoặc database đang hoạt động!","Đã có lỗi xảy ra!",JOptionPane.ERROR_MESSAGE);
					TroubleshootFrame frame = new TroubleshootFrame();
					frame.setLocationRelativeTo(null);
					frame.setTitle("Cài đặt kết nối cơ sở dữ liệu");
					frame.pack();
					frame.setVisible(true);
				}
			}
		});
		contentPane.add(btnOk, "flowx,cell 1 4");

		JButton btnCancel = new JButton("Thoát");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 4");
		setTitle("Cài đặt kết nối cơ sở dữ liệu");
		pack();
	}

}
