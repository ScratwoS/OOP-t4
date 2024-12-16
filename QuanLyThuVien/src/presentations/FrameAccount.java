package presentations;

import javax.swing.JDialog;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import business.BStaff;
import entities.Staff;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FrameAccount extends JDialog {
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfEmail;
	private FrameAccount frame;
	public FrameAccount(int id){
		frame = this;
		setModal(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][109.00,grow,baseline][]"));
		
		JLabel lblName = new JLabel("Họ và tên *");
		contentPane.add(lblName, "cell 0 0,alignx trailing");
		
		tfName = new JTextField();
		contentPane.add(tfName, "cell 1 0,growx");
		tfName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email *");
		contentPane.add(lblEmail, "cell 0 1,alignx trailing");
		
		tfEmail = new JTextField();
		contentPane.add(tfEmail, "cell 1 1,growx");
		tfEmail.setColumns(10);
		
		JLabel lblDes = new JLabel("Mô tả cá nhân");
		contentPane.add(lblDes, "cell 0 2,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 2,grow");
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnSave = new JButton("Xác Nhận");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tfName.getText().equals("")||!tfEmail.getText().equals("")){
				BStaff bstaff = new BStaff();
				Staff staff = new Staff();
				staff.setId(id);
				staff.setFullname(tfName.getText());
				staff.setEmail(tfEmail.getText());
				staff.setDescription(textArea.getText());
				try {
					bstaff.updateInfo(staff);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Các trường có dấu (*) là bắt buộc, không được bỏ trống, hãy kiểm tra cẩn thận","Đã có lỗi xảy ra!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnSave, "flowx,cell 1 3");
		
		JButton btnCancel = new JButton("Hủy");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 3");
		
		JButton btnChangePass = new JButton("Đổi Mật Khẩu");
		btnChangePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChangePassword dialog = new ChangePassword(id);
				dialog.setLocationRelativeTo(frame);
				dialog.setVisible(true);
			}
		});
		contentPane.add(btnChangePass, "cell 1 3");
		setTitle("Thay đổi thông tin");
		pack();
	}
}
