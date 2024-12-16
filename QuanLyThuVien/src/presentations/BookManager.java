package presentations;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import business.BBook;
import business.BCategories;
import business.BLanguage;
import business.BTypeOfBook;
import entities.Book;
import net.miginfocom.swing.MigLayout;

public class BookManager extends JInternalFrame {
	private JButton btnSave, btnDelete, btnCancel;
	private JComboBox<String> cbLang;
	private JComboBox<String> cbType;
	private JComboBox<String> cbCategories;
	private JPanel contentPane;
	private MyTable table;
	private JTextField tfTittle;
	private JTextField tfAuthor;
	private JTextField tfPublisher;
	private JTextField tfYear;
	private JTextField tfPrice;
	private JTextField tfUrl;
	private JTextField tfQuan;
	private LoadImage loader = new LoadImage();
	private BTypeOfBook btype_book;
	private BBook bbook;
	private BCategories bcate;
	private BLanguage blang;
	private DefaultTableModel model;
	private DefaultComboBoxModel<String> lmodel;
	private DefaultComboBoxModel<String> cmodel;
	private DefaultComboBoxModel<String> tbmodel;
	private JLabel lblStatus, lblImg;
	private JTextArea desTA;
	private int choose;
	private ImageIcon on, fail, querying;
	private JTextField textSearchBook;
	private JButton btnSearchBook;

	/**
	 * Create the frame.
	 */
	public BookManager() {
		super("Quản Lý Sách", true, true, true, true);

		on = new ImageIcon(loader.loadImage("on.png"));
		fail = new ImageIcon(loader.loadImage("off.png"));
		querying = new ImageIcon(loader.loadImage("busy.png"));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new MyTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				int c = table.getSelectedRow();
				if (c > -1) {
					choose = Integer.parseInt(String.valueOf(table.getModel().getValueAt(c, 0)));
					tfTittle.setText(String.valueOf(table.getModel().getValueAt(c, 1)));
					tfAuthor.setText(String.valueOf(table.getModel().getValueAt(c, 2)));
					tfPublisher.setText(String.valueOf(table.getModel().getValueAt(c, 3)));
					tfYear.setText(String.valueOf(table.getModel().getValueAt(c, 4)));
					tfPrice.setText(String.valueOf(table.getModel().getValueAt(c, 5)));
					cbCategories.setSelectedItem(table.getModel().getValueAt(c, 6));
					cbLang.setSelectedItem(String.valueOf(table.getModel().getValueAt(c, 9)));
					cbType.setSelectedItem(String.valueOf(table.getModel().getValueAt(c, 7)));
					tfUrl.setText(String.valueOf(table.getModel().getValueAt(c, 8)));
					lblImg.setSize(150, 150);
					try {
						lblImg.setIcon(loader.getIcon(lblImg,
								loader.loadImage(new URL(String.valueOf(table.getModel().getValueAt(c, 8))))));
					} catch (MalformedURLException e) {
						lblImg.setIcon(loader.getIcon(lblImg, loader.loadImage("not_found_image.jpg")));
					}
					tfQuan.setText(String.valueOf(table.getModel().getValueAt(c, 10)));
					desTA.setText(String.valueOf(table.getModel().getValueAt(c, 11)));
					btnSave.setEnabled(true);
					btnDelete.setEnabled(true);
					btnCancel.setEnabled(true);
				}
			}
		});
		scrollPane.setViewportView(table);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!table.contains(e.getPoint())) {
					table.clearSelection();
					reset();
				}
			}
		});
		lblStatus = new JLabel("Done!");
		contentPane.add(lblStatus, BorderLayout.SOUTH);
		lblStatus.setIcon(on);

		JPanel insertPnl = new JPanel();
		contentPane.add(insertPnl, BorderLayout.NORTH);
		insertPnl.setLayout(new MigLayout("", "[][grow][][grow][][grow][][]", "[][][grow][][]"));

		JLabel lblTittle = new JLabel("Tên Sách *");
		insertPnl.add(lblTittle, "cell 0 0,alignx leading");

		tfTittle = new JTextField();
		insertPnl.add(tfTittle, "cell 1 0,growx");
		tfTittle.setColumns(5);

		JLabel lblGia = new JLabel("Giá *");
		insertPnl.add(lblGia, "cell 2 0,alignx leading");

		tfPrice = new JTextField();
		insertPnl.add(tfPrice, "cell 3 0,growx");
		tfPrice.setColumns(5);

		JLabel lblUrl = new JLabel("Đường Dẫn Ảnh *");
		insertPnl.add(lblUrl, "cell 4 0,alignx leading");

		tfUrl = new JTextField();
		insertPnl.add(tfUrl, "cell 5 0,growx");
		tfUrl.setColumns(5);

		lblImg = new JLabel();
		insertPnl.add(lblImg, "cell 6 0 1 5");
		lblImg.setSize(150, 150);
		lblImg.setIcon(loader.getIcon(lblImg, loader.loadImage("not_found_image.jpg")));

		JLabel lblAuthor = new JLabel("Tác Giả *");
		insertPnl.add(lblAuthor, "cell 0 1,alignx leading");

		tfAuthor = new JTextField();
		insertPnl.add(tfAuthor, "cell 1 1,growx");
		tfAuthor.setColumns(5);

		JLabel lblTheLoai = new JLabel("Thể Loại *");
		insertPnl.add(lblTheLoai, "cell 2 1,alignx leading");

		cbCategories = new JComboBox<String>();
		insertPnl.add(cbCategories, "cell 3 1,growx");

		JLabel lblDes = new JLabel("Mô Tả");
		insertPnl.add(lblDes, "cell 4 1,alignx leading");

		JLabel lblPublisher = new JLabel("Nhà Xuất Bản *");
		insertPnl.add(lblPublisher, "cell 0 2,alignx leading");

		tfPublisher = new JTextField();
		insertPnl.add(tfPublisher, "cell 1 2,growx");
		tfPublisher.setColumns(5);
		cbType = new JComboBox<String>();
		insertPnl.add(cbType, "cell 3 2,growx");

		JScrollPane scrollPaneDes = new JScrollPane();
		insertPnl.add(scrollPaneDes, "cell 5 1 1 3,grow");

		desTA = new JTextArea();
		desTA.setLineWrap(true);
		desTA.setColumns(5);
		scrollPaneDes.setViewportView(desTA);

		JLabel lblYear = new JLabel("Năm Xuất Bản *");
		insertPnl.add(lblYear, "cell 0 3,alignx leading");

		tfYear = new JTextField();
		insertPnl.add(tfYear, "cell 1 3,growx");
		tfYear.setColumns(5);

		JLabel lblQuan = new JLabel("Số Lượng *");
		insertPnl.add(lblQuan, "cell 2 3,alignx leading");

		tfQuan = new JTextField();
		insertPnl.add(tfQuan, "cell 3 3,growx");
		tfQuan.setColumns(5);

		JLabel lblLang = new JLabel("Ngôn Ngữ *");
		insertPnl.add(lblLang, "cell 0 4,alignx leading");

		cbLang = new JComboBox<String>();
		insertPnl.add(cbLang, "cell 1 4,growx");

		JLabel lblType = new JLabel("Kiểu *");
		insertPnl.add(lblType, "cell 2 2,alignx leading");

		JButton btnNew = new JButton("Nhập Mới");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblStatus.setIcon(querying);
				lblStatus.setText("Querying...");
				if (tfAuthor.getText().equals("") || tfPrice.getText().equals("") || tfPublisher.getText().equals("")
						|| tfQuan.getText().equals("") || tfTittle.getText().equals("") || tfUrl.getText().equals("")
						|| tfYear.getText().equals(""))
					JOptionPane.showMessageDialog(null,
							"Các trường có dấu (*) là bắt buộc, không được phép bỏ trống, vui lòng kiểm tra lại!",
							"Đã Có Lỗi Xảy Ra!", JOptionPane.ERROR_MESSAGE);
				else {

					try {
						lblImg.setIcon(loader.getIcon(lblImg, loader.loadImage(new URL(tfUrl.getText()))));
					} catch (MalformedURLException e) {
						lblImg.setIcon(loader.getIcon(lblImg, loader.loadImage("not_found_image.jpg")));
					}
					Book book = new Book();
					book.setTitle(tfTittle.getText());
					book.setAuthor(tfAuthor.getText());
					book.setPublisher(tfPublisher.getText());
					book.setPublished_year(Integer.parseInt(tfYear.getText()));
					book.setQuantity(Integer.parseInt(tfQuan.getText()));
					book.setPrice(Double.parseDouble(tfPrice.getText()));
					book.setCategory(cbCategories.getSelectedIndex() + 1);
					book.setType(cbType.getSelectedIndex() + 1);
					book.setLanguage(cbLang.getSelectedIndex() + 1);
					book.setImage(tfUrl.getText());
					book.setDescription(desTA.getText());
					try {
						bbook.insert(book);
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					initTable();

				}
				lblStatus.setIcon(on);
				lblStatus.setText("Done!");
			}
		});
		insertPnl.add(btnNew, "flowx,cell 5 4");

		btnSave = new JButton("Lưu");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lblStatus.setIcon(querying);
				lblStatus.setText("Querying...");
				if (tfAuthor.getText().equals("") || tfPrice.getText().equals("") || tfPublisher.getText().equals("")
						|| tfQuan.getText().equals("") || tfTittle.getText().equals("") || tfUrl.getText().equals("")
						|| tfYear.getText().equals(""))
					JOptionPane.showMessageDialog(null,
							"Các trường có dấu (*) là bắt buộc, không được phép bỏ trống, vui lòng kiểm tra lại!",
							"Đã Có Lỗi Xảy Ra!", JOptionPane.ERROR_MESSAGE);
				else {
					if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thực hiện thay đổi?", "Lưu ý",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
						try {
							lblImg.setIcon(loader.getIcon(lblImg, loader.loadImage(new URL(tfUrl.getText()))));
						} catch (MalformedURLException e1) {
							lblImg.setIcon(loader.getIcon(lblImg, loader.loadImage("not_found_image.jpg")));
						}
						Book book = new Book();
						book.setId(choose);
						book.setTitle(tfTittle.getText());
						book.setAuthor(tfAuthor.getText());
						book.setPublisher(tfPublisher.getText());
						book.setPublished_year(Integer.parseInt(tfYear.getText()));
						book.setQuantity(Integer.parseInt(tfQuan.getText()));
						book.setPrice(Double.parseDouble(tfPrice.getText()));
						book.setCategory(cbCategories.getSelectedIndex() + 1);
						book.setType(cbType.getSelectedIndex() + 1);
						book.setLanguage(cbLang.getSelectedIndex() + 1);
						book.setImage(tfUrl.getText());
						book.setDescription(desTA.getText());
						try {
							bbook.update(book);
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
						initTable();
						table.clearSelection();
						reset();
						lblStatus.setIcon(on);
						lblStatus.setText("Done!");
					} else {
						lblStatus.setIcon(on);
						lblStatus.setText("Done!");
						return;
					}
				}
			}
		});
		insertPnl.add(btnSave, "cell 5 4");

		btnDelete = new JButton("Xoá");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				lblStatus.setIcon(querying);
				lblStatus.setText("Querying...");
				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thực hiện xóa?", "Lưu ý",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					try {
						bbook.deleteBookByID(choose);
						initTable();
						reset();
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					lblStatus.setIcon(on);
					lblStatus.setText("Done!");
				} else {
					lblStatus.setIcon(on);
					lblStatus.setText("Done!");
					return;
				}
			}
		});
		insertPnl.add(btnDelete, "cell 5 4");

		btnCancel = new JButton("Hủy");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				table.clearSelection();
				reset();
			}
		});
		btnCancel.setEnabled(false);
		insertPnl.add(btnCancel, "cell 5 4");
		/*hunglv*/
		textSearchBook = new JTextField();
		insertPnl.add(textSearchBook, "cell 0 5 3 1,growx");
		textSearchBook.setColumns(10);
		
		btnSearchBook = new JButton("Search");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					table.setModel(bbook.searchOrderManager(textSearchBook.getText()));
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		insertPnl.add(btnSearchBook, "cell 3 5");
		/*end*/

		initTable();
		pack();
	}

	private void initTable() {
		bbook = new BBook();
		blang = new BLanguage();
		bcate = new BCategories();
		btype_book = new BTypeOfBook();
		try {
			tbmodel = btype_book.getAllTypesToModel();
			model = bbook.getAllBookToModel();
			lmodel = blang.getAllLangsToModel();
			cmodel = bcate.getAllCategoriesToModel();
		} catch (ClassNotFoundException | SQLException e) {
		}
		table.setModel(model);
		cbLang.setModel(lmodel);
		cbCategories.setModel(cmodel);
		cbType.setModel(tbmodel);
	}

	class MyTable extends JTable {
		public MyTable() {
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	private void reset() {
		tfTittle.setText("");
		tfAuthor.setText("");
		tfPublisher.setText("");
		tfYear.setText("");
		tfPrice.setText("");
		cbCategories.setSelectedIndex(0);
		cbLang.setSelectedItem(0);
		cbType.setSelectedItem(0);
		tfUrl.setText("");
		lblImg.setSize(150, 150);
		lblImg.setIcon(loader.getIcon(lblImg, loader.loadImage("not_found_image.jpg")));
		tfQuan.setText("");
		desTA.setText("");
		btnSave.setEnabled(false);
		btnDelete.setEnabled(false);
		btnCancel.setEnabled(false);
	}
}
