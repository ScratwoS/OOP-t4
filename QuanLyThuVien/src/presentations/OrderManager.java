package presentations;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import business.BBook;
import business.BMember;
import business.BOrder;
import business.BStaff;
import entities.Order;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class OrderManager extends JInternalFrame {
	private DefaultTableModel expiredModel, orderModel;
	private BOrder bOrder;
	private BMember bMember;
	private BStaff bStaff;
	private BBook bBook;
	private DefaultComboBoxModel<String> memberModel, staffModel, bookModel;
	private MyTable expiredTable, orderTable;
	private JComboBox<String> cbMember, cbStaff, cbBook;
	private JTextArea desTA;
	private JPanel contentPane;
	private int exp;
	private JLabel lblExpired;
	private int choose;
	private JButton btnInsert, btnSave, btnCancel, btnDelete;
	private JTextField startDate;
	private JTextField endDate;
	private Date start_date = null, end_date = null;
	private JTextField textSearchOrderManager;
	private JButton btnSearchOrderManager;

	/**
	 * Create the frame.
	 */
	public OrderManager() {
		super("Quản Lý Mượn và Trả", true, true, true, true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel insertPanel = new JPanel();
		contentPane.add(insertPanel, BorderLayout.NORTH);

		JLabel lblMember = new JLabel("Độc Giả *");

		JLabel lblStaff = new JLabel("Nhân Viên *");

		JLabel lblBook = new JLabel("Sách *");

		cbMember = new JComboBox<String>();

		cbStaff = new JComboBox<String>();

		cbBook = new JComboBox<String>();

		JLabel lblStartDate = new JLabel("Ngày Cho Mượn *");

		JLabel lblEndDate = new JLabel("Ngày Hết Hạn *");

		JLabel lblDes = new JLabel("Mô Tả");

		desTA = new JTextArea();

		JScrollPane scrollPane = new JScrollPane();

		lblExpired = new JLabel();

		btnInsert = new JButton("Nhập Mới");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (startDate.getText() == null || startDate.getText() == null)
					JOptionPane.showMessageDialog(null,
							"Các trường có dấu (*) là bắt buộc, không được bỏ trống, hãy kiểm tra cẩn thận!",
							"Đã có lỗi xảy ra!", JOptionPane.ERROR_MESSAGE);
				else {
					try {
						end_date = formatter.parse(endDate.getText());
						start_date = formatter.parse(startDate.getText());
						if (start_date.before(end_date)) {
							int id = -1;
							int member = 0,staff = 0,book = 0;
							try {
								member = bMember.getMemberByName(cbMember.getSelectedItem().toString()).getId();
								staff = bStaff.getStaffByName(cbStaff.getSelectedItem().toString()).getId();
								book = bBook.getBookByName(cbBook.getSelectedItem().toString()).getId();
							} catch (ClassNotFoundException | SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							String description = desTA.getText();
							Order order = new Order(id, member, staff, book, start_date, end_date, description);
							try {
								bOrder.insert(order);

							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							initModel();
						} else
							JOptionPane.showMessageDialog(null,
									"Ngày Cho Mượn lớn hơn hoặc bằng Ngày Hết Hạn, hãy kiểm tra cẩn thận!",
									"Đã có lỗi xảy ra!", JOptionPane.ERROR_MESSAGE);
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Định dạng Ngày/Tháng bị lỗi, hãy kiểm tra cẩn thận!",
								"Đã có lỗi xảy ra!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnSave = new JButton("Lưu");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (startDate.getText() == null || startDate.getText() == null)
					JOptionPane.showMessageDialog(null,
							"Các trường có dấu (*) là bắt buộc, không được bỏ trống, hãy kiểm tra cẩn thận!",
							"Đã có lỗi xảy ra!", JOptionPane.ERROR_MESSAGE);
				else {
					try {
						end_date = formatter.parse(endDate.getText());
						start_date = formatter.parse(startDate.getText());
						if (start_date.before(end_date)) {
							if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thực hiện thay đổi?", "Lưu ý",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
								int id = choose;
								int member = 0,staff = 0,book = 0;
								try {
									member = bMember.getMemberByName(cbMember.getSelectedItem().toString()).getId();
									staff = bStaff.getStaffByName(cbStaff.getSelectedItem().toString()).getId();
									book = bBook.getBookByName(cbBook.getSelectedItem().toString()).getId();
								} catch (ClassNotFoundException | SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								String description = desTA.getText();
								Order order = new Order(id, member, staff, book, start_date, end_date, description);
								try {
									bOrder.update(order);
								} catch (ClassNotFoundException | SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								initModel();
								reset();
								expiredTable.clearSelection();
								orderTable.clearSelection();
							} else {
								return;
							}
						} else
							JOptionPane.showMessageDialog(null,
									"Ngày Cho Mượn lớn hơn hoặc bằng Ngày Hết Hạn, hãy kiểm tra cẩn thận!",
									"Đã có lỗi xảy ra!", JOptionPane.ERROR_MESSAGE);
					} catch (ParseException e2) {
						JOptionPane.showMessageDialog(null, "Định dạng Ngày/Tháng bị lỗi, hãy kiểm tra cẩn thận!",
								"Đã có lỗi xảy ra!", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});

		btnCancel = new JButton("Hủy");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expiredTable.clearSelection();
				orderTable.clearSelection();
				reset();
			}
		});

		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thực hiện xóa?", "Lưu ý",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					try {
						bOrder.deleteOrderByID(choose);
						initModel();
						reset();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					return;
				}
			}
		});

		startDate = new JTextField("Nhập định dạng yyyy-mm-dd. (VD : 2017-05-14)");
		startDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (startDate == e.getSource()
						&& startDate.getText().compareTo("Nh?p d?nh d?ng yyyy-mm-dd. (VD : 2017-05-14)") == 0) {
					startDate.setText("");
				}
			}
		});

		startDate.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				startDate.selectAll();
			}
		});

		startDate.setColumns(10);

		endDate = new JTextField("Nhập định dạng yyyy-mm-dd. (VD : 2017-05-14)");
		endDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (endDate == e.getSource()
						&& endDate.getText().compareTo("Nhập định dạng yyyy-mm-dd. (VD : 2017-05-14)") == 0) {
					endDate.setText("");
				}
			}
		});

		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() != endDate && endDate.getText().compareTo("") == 0) {
					endDate.setText("Nhập định dạng yyyy-mm-dd. (VD : 2017-05-14)");
				}
				if (e.getSource() != startDate && startDate.getText().compareTo("") == 0) {
					startDate.setText("Nhập định dạng yyyy-mm-dd. (VD : 2017-05-14)");
				}
			}
		});
		endDate.setColumns(10);

		textSearchOrderManager = new JTextField();
		textSearchOrderManager.setColumns(10);

		btnSearchOrderManager = new JButton("Search");
		btnSearchOrderManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					orderTable.setModel(bOrder.searchOrderManager(textSearchOrderManager.getText()));
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_insertPanel = new GroupLayout(insertPanel);
		gl_insertPanel
				.setHorizontalGroup(
						gl_insertPanel
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(gl_insertPanel.createSequentialGroup().addContainerGap()
										.addGroup(gl_insertPanel
												.createParallelGroup(Alignment.LEADING, false).addGroup(gl_insertPanel
														.createSequentialGroup()
														.addGroup(gl_insertPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(lblStaff).addComponent(lblMember)
																.addComponent(lblBook))
														.addGap(18)
														.addGroup(gl_insertPanel
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(cbMember, 0, GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(cbStaff, 0, GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(cbBook, 0, 165, Short.MAX_VALUE)))
												.addComponent(textSearchOrderManager))
										.addGap(15)
										.addGroup(gl_insertPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblStartDate).addComponent(lblDes)
												.addGroup(gl_insertPanel.createParallelGroup(Alignment.TRAILING)
														.addComponent(btnSearchOrderManager).addComponent(lblEndDate)))
										.addGap(18)
										.addGroup(gl_insertPanel.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_insertPanel.createSequentialGroup().addComponent(btnInsert)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnSave)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnDelete).addGap(8).addComponent(btnCancel))
												.addComponent(desTA, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
												.addComponent(startDate).addComponent(endDate))
										.addGap(18)
										.addGroup(gl_insertPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_insertPanel.createSequentialGroup()
														.addComponent(lblExpired, GroupLayout.DEFAULT_SIZE, 178,
																Short.MAX_VALUE)
														.addGap(70))
												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 248,
														Short.MAX_VALUE))
										.addContainerGap()));
		gl_insertPanel
				.setVerticalGroup(
						gl_insertPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_insertPanel.createSequentialGroup().addContainerGap()
												.addGroup(
														gl_insertPanel
																.createParallelGroup(
																		Alignment.LEADING, false)
																.addGroup(gl_insertPanel
																		.createParallelGroup(Alignment.BASELINE)
																		.addComponent(lblMember)
																		.addComponent(cbMember,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(lblStartDate).addComponent(
																				startDate, GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
																.addComponent(lblExpired))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_insertPanel.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_insertPanel.createSequentialGroup()
																.addGroup(gl_insertPanel
																		.createParallelGroup(Alignment.LEADING, false)
																		.addGroup(gl_insertPanel.createSequentialGroup()
																				.addGap(31).addComponent(desTA,
																						GroupLayout.PREFERRED_SIZE, 130,
																						GroupLayout.PREFERRED_SIZE))
																		.addGroup(gl_insertPanel.createSequentialGroup()
																				.addGroup(gl_insertPanel
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(lblStaff)
																						.addComponent(cbStaff,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(lblEndDate)
																						.addComponent(endDate,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																				.addPreferredGap(
																						ComponentPlacement.UNRELATED)
																				.addGroup(gl_insertPanel
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(lblBook)
																						.addComponent(cbBook,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(lblDes))))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(gl_insertPanel
																		.createParallelGroup(Alignment.BASELINE)
																		.addComponent(btnSave).addComponent(btnDelete)
																		.addComponent(btnInsert).addComponent(btnCancel)
																		.addComponent(textSearchOrderManager,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(btnSearchOrderManager)))
														.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 190,
																Short.MAX_VALUE))
												.addGap(11)));

		expiredTable = new MyTable();
		expiredTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int c = expiredTable.getSelectedRow();
				selected(c, expiredTable);
			}

		});
		scrollPane.setViewportView(expiredTable);
		scrollPane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!expiredTable.contains(e.getPoint())) {
					reset();
					expiredTable.clearSelection();
				}
			}
		});
		insertPanel.setLayout(gl_insertPanel);

		JScrollPane orderPnl = new JScrollPane();
		contentPane.add(orderPnl, BorderLayout.CENTER);
		orderTable = new MyTable();
		orderPnl.setViewportView(orderTable);
		orderPnl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!orderTable.contains(e.getPoint())) {
					reset();
					orderTable.clearSelection();
				}
			}
		});
		orderTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int c = orderTable.getSelectedRow();
				selected(c, orderTable);
			}

		});

		JLabel lblStatus = new JLabel("Status");
		contentPane.add(lblStatus, BorderLayout.SOUTH);
		initModel();
		pack();
		reset();
	}

	protected void reset() {
		desTA.setText("");
		cbBook.setSelectedIndex(0);
		cbMember.setSelectedIndex(0);
		cbStaff.setSelectedIndex(0);
		btnSave.setEnabled(false);
		btnDelete.setEnabled(false);
		btnCancel.setEnabled(false);
	}

	private void initModel() {
		bOrder = new BOrder();
		bStaff = new BStaff();
		bBook = new BBook();
		bMember = new BMember();
		try {
			memberModel = bMember.getAllMemberToModel();
			staffModel = bStaff.getAllStaffToModel();
			bookModel = bBook.getAllBookToCBModel();
			expiredModel = bOrder.getAllExpiredToModel();
			orderModel = bOrder.getAllOrderToModel();
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		orderTable.setModel(orderModel);
		expiredTable.setModel(expiredModel);
		cbMember.setModel(memberModel);
		cbBook.setModel(bookModel);
		cbStaff.setModel(staffModel);
		exp = expiredTable.getRowCount();
		if (exp > 0) {
			lblExpired.setText("Hiện có " + exp + " số phiếu mượn đã quá hạn!");

			lblExpired.setForeground(Color.RED);
		} else {
			lblExpired.setText("Không có phiếu mượn nào quá hạn!");

			lblExpired.setForeground(Color.BLUE);
		}
	}

	class MyTable extends JTable {
		public MyTable() {
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	protected void selected(int c, MyTable table) {

		if (c > -1) {
			choose = Integer.parseInt(String.valueOf(table.getValueAt(c, 0)));
			cbMember.setSelectedItem(String.valueOf(table.getValueAt(c, 1)));
			cbStaff.setSelectedItem(String.valueOf(table.getValueAt(c, 2)));
			cbBook.setSelectedItem(String.valueOf(table.getValueAt(c, 3)));
			startDate.setText(table.getValueAt(c, 4).toString());
			endDate.setText(table.getValueAt(c, 5).toString());
			desTA.setText(String.valueOf(table.getValueAt(c, 6)));
			btnSave.setEnabled(true);
			btnDelete.setEnabled(true);
			btnCancel.setEnabled(true);
		}
	}
}
