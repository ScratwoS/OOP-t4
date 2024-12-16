package presentations;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import business.BMember;
import business.BOrder;
import business.BTypeOfMember;
import entities.Member;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MemberFrame extends JInternalFrame {
	private JPanel contentPane;
	private JTextField tfDate;
	private JTextField tfName;
	private MyTable orderTable;
	private MyTable memberTable;
	private JComboBox<String> cbType;
	private DefaultComboBoxModel<String> typeModel;
	private DefaultTableModel orderModel, memberModel;
	private BMember bMember;
	private BTypeOfMember bType;
	private BOrder bOrder;
	private int choose;
	private Date end_date;
	private JTextArea desTA;
	private JButton btnDelete,btnCancel,btnSave;
	private JLabel label;

	/**
	 * Create the frame.
	 */
	public MemberFrame() {
		super("Quản Lý Độc Giả", true, true, true, true);
		setBounds(100, 100, 900, 500);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblName = new JLabel("Họ và Tên *");

		JLabel lblType = new JLabel("Chức Vụ *");

		JLabel lblExpiredDate = new JLabel("Ngày Hết Hạn *");

		tfDate = new JTextField("Nhập định dạng yyyy-mm-dd. (VD : 2017-05-14)");
		tfDate.setColumns(10);
		tfDate.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				tfDate.selectAll();
			}
		});

		tfName = new JTextField();
		tfName.setColumns(10);

		cbType = new JComboBox<String>();
		JLabel lblDescription = new JLabel("Mô Tả");

		desTA = new JTextArea();

		JButton btnInsert = new JButton("Nhập Mới");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfName.getText() == null || tfDate.getText() == null)
					JOptionPane.showMessageDialog(null,
							"Các trường có dấu (*) là bắt buộc, không được bỏ trống, hãy kiểm tra cẩn thận!",
							"Đã có lỗi xảy ra!", JOptionPane.ERROR_MESSAGE);
				else {
					try {
						end_date = formatter.parse(tfDate.getText());
						if (end_date.before(new Date())) {
							JOptionPane.showMessageDialog(null, "Ngày Hết Hạn phải lớn hơn Thời Điểm Hiện Tại!",
									"Đã có lỗi xảy ra!", JOptionPane.ERROR_MESSAGE);
						} else {
							int id = -1;
							int type = cbType.getSelectedIndex() + 1;
							String fullname = tfName.getText();
							String description = desTA.getText();
							Date expired_date = end_date;
							Member member = new Member(id, type, fullname, description, expired_date);
							try {
								bMember.insert(member);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							initModel();
							orderTable.clearSelection();
							memberTable.clearSelection();
							reset();
						}
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Định dạng Ngày/Tháng bị lỗi, hãy kiểm tra cẩn thận!",
								"Đã có lỗi xảy ra!", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});

		btnSave = new JButton("Lưu");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfName.getText() == null || tfDate.getText() == null)
					JOptionPane.showMessageDialog(null,
							"Các trường có dấu (*) là bắt buộc, không được bỏ trống, hãy kiểm tra cẩn thận!",
							"Đã có lỗi xảy ra!", JOptionPane.ERROR_MESSAGE);
				else{
					if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thực hiện thay đổi?", "Lưu ý",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
						int id = choose;
						int type = cbType.getSelectedIndex() + 1;
						String fullname = tfName.getText();
						String description = desTA.getText();
						Date expired_date = end_date;
						Member member = new Member(id, type, fullname, description, expired_date);
						try {
							bMember.update(member);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						orderTable.clearSelection();
						memberTable.clearSelection();
						reset();
						initModel();
					}
				}
			}
		});
		btnSave.setEnabled(false);

		btnCancel = new JButton("Hủy");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				orderTable.clearSelection();
				memberTable.clearSelection();
				reset();
			}
		});
		btnCancel.setEnabled(false);

		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thực hiện xóa?", "Lưu ý",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					try {
						bMember.deleteMemberByID(choose);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					orderTable.clearSelection();
					memberTable.clearSelection();
					reset();
					initModel();
				}
			}
		});
		btnDelete.setEnabled(false);

		JScrollPane orderPane = new JScrollPane();
		orderPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!orderTable.contains(e.getPoint())) {
					orderTable.clearSelection();
					memberTable.clearSelection();
					reset();
				}
			}
		});
		
		label = new JLabel("Người này không có phiếu mượn nào!");
		label.setForeground(Color.BLUE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblType)
								.addComponent(lblExpiredDate))
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cbType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(tfDate)
								.addComponent(tfName, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(lblDescription)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(desTA, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnInsert)
							.addGap(18)
							.addComponent(btnSave)
							.addGap(18)
							.addComponent(btnDelete)
							.addGap(18)
							.addComponent(btnCancel)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(orderPane, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
						.addComponent(label))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblDescription)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblName)
									.addGap(19)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblType)
										.addComponent(cbType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(tfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblExpiredDate)
								.addComponent(tfDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSave)
								.addComponent(btnDelete)
								.addComponent(btnCancel)
								.addComponent(btnInsert)))
						.addComponent(desTA, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addContainerGap(16, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(36, Short.MAX_VALUE)
					.addComponent(orderPane, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		orderTable = new MyTable();
		orderTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int c = orderTable.getSelectedRow();
				if (c > -1) {
					choose = Integer.parseInt(String.valueOf(orderTable.getModel().getValueAt(c, 0)));
					tfName.setText(String.valueOf(orderTable.getModel().getValueAt(c, 1)));
					tfDate.setText(String.valueOf(orderTable.getModel().getValueAt(c, 2)));
					cbType.setSelectedItem(String.valueOf(orderTable.getModel().getValueAt(c, 3)));
					desTA.setText(String.valueOf(orderTable.getModel().getValueAt(c, 4)));
					btnCancel.setEnabled(true);
					btnDelete.setEnabled(true);
					btnSave.setEnabled(true);
				}
			}
		});
		orderPane.setViewportView(orderTable);
		panel.setLayout(gl_panel);

		JLabel lblStatus = new JLabel("Status");
		contentPane.add(lblStatus, BorderLayout.SOUTH);

		JScrollPane memberPane = new JScrollPane();
		memberPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!memberTable.contains(e.getPoint())) {
					memberTable.clearSelection();
					reset();
				}
			}
		});
		contentPane.add(memberPane, BorderLayout.CENTER);

		memberTable = new MyTable();
		memberTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int c = memberTable.getSelectedRow();
				if (c > -1) {
					choose = Integer.parseInt(String.valueOf(memberTable.getModel().getValueAt(c, 0)));
					tfName.setText(String.valueOf(memberTable.getModel().getValueAt(c, 2)));
					tfDate.setText(String.valueOf(memberTable.getModel().getValueAt(c, 4)));
					cbType.setSelectedItem(String.valueOf(memberTable.getModel().getValueAt(c, 1)));
					desTA.setText(String.valueOf(memberTable.getModel().getValueAt(c, 3)));
					try {
						orderModel = bOrder.getAllOrderByMemberToModel(choose);
						orderTable.setModel(orderModel);
						if(orderTable.getRowCount()>0){
						label.setText("Người này có "+orderTable.getRowCount()+" phiếu mượn!");
						label.setForeground(Color.RED);
						}else{
							label.setText("Người này không có phiếu mượn nào!");
							label.setForeground(Color.BLUE);
						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					btnCancel.setEnabled(true);
					btnDelete.setEnabled(true);
					btnSave.setEnabled(true);
				}
			}
		});
		memberPane.setViewportView(memberTable);
		orderModel = new DefaultTableModel();
		orderModel.addColumn("ID");
		orderModel.addColumn("Nhân Viên");
		orderModel.addColumn("Tên Sách");
		orderModel.addColumn("Ngày Cho Mượn");
		orderModel.addColumn("Ngày Hết Hạn");
		orderModel.addColumn("Mô Tả");
		initModel();
	}

	protected void reset() {
		label.setText("Người này không có phiếu mượn nào!");
		label.setForeground(Color.BLUE);
		tfName.setText("");
		tfDate.setText("Nhập định dạng yyyy-mm-dd. (VD : 2017-05-14)");
		cbType.setSelectedIndex(0);
		desTA.setText("");
		btnDelete.setEnabled(false);
		btnSave.setEnabled(false);
		btnCancel.setEnabled(false);
	}

	private void initModel() {
		bMember = new BMember();
		bType = new BTypeOfMember();
		bOrder = new BOrder();
		try {
			memberModel = bMember.getAllMember();
			typeModel = bType.getAllTypesToModel();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		orderTable.setModel(orderModel);
		memberTable.setModel(memberModel);
		cbType.setModel(typeModel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	class MyTable extends JTable {
		public MyTable() {
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
}
