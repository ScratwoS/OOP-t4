package presentations;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import business.BStaff;
import entities.Staff;

public class AccountManager extends JInternalFrame {
	private JPanel contentPane;
	private MyTable unactiveTable;
	private MyTable activedTable;
	private DefaultTableModel unactiveModel, activedModel;
	private LoadImage loader = new LoadImage();
	private ImageIcon on, fail, querying;
	private BStaff bstaff;
	private JButton btnActive, btnBlock;
	private int bc, ac;

	public AccountManager() {
		super("Quản lý tài khoản", true, true, true, true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		bstaff = new BStaff();
		on = new ImageIcon(loader.loadImage("on.png"));
		fail = new ImageIcon(loader.loadImage("off.png"));
		querying = new ImageIcon(loader.loadImage("busy.png"));
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[434px,grow]", "[][][grow,fill][grow,bottom][grow,fill][grow,bottom]"));

		JLabel label = new JLabel("New label");
		panel.add(label, "cell 0 1,growx,aligny top");

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 2,grow");

		unactiveTable = new MyTable();
		unactiveTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				ac = unactiveTable.getSelectedRow();
				if (ac > -1) {
					btnActive.setEnabled(true);
				}
			}
		});
		scrollPane.setViewportView(unactiveTable);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!unactiveTable.contains(e.getPoint())) {
					unactiveTable.clearSelection();
					btnActive.setEnabled(false);
				}
			}
		});

		btnActive = new JButton("Kích Hoạt");
		btnActive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Staff staff = new Staff();
				staff.setActivated(1);
				staff.setId(Integer.parseInt(String.valueOf(unactiveTable.getValueAt(ac, 0))));
				try {
					bstaff.updateActive(staff);
					btnActive.setEnabled(false);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				initTable();
			}
		});
		panel.add(btnActive, "cell 0 3,growx,aligny bottom");

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!activedTable.contains(e.getPoint())) {
					activedTable.clearSelection();
					btnBlock.setEnabled(false);
				}
			}
		});
		panel.add(scrollPane_1, "cell 0 4,grow");

		activedTable = new MyTable();
		activedTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				bc = activedTable.getSelectedRow();
				if (bc > -1) {
					btnBlock.setEnabled(true);
				}
			}
		});
		scrollPane_1.setViewportView(activedTable);

		btnBlock = new JButton("Khóa");
		btnBlock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Staff staff = new Staff();
				staff.setActivated(0);
				staff.setId(Integer.parseInt(String.valueOf(activedTable.getValueAt(bc, 0))));
				try {
					bstaff.updateActive(staff);
					btnBlock.setEnabled(false);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				initTable();
			}
		});
		panel.add(btnBlock, "cell 0 5,growx,aligny bottom");

		JLabel status = new JLabel("Sẵn sàng");
		contentPane.add(status, BorderLayout.SOUTH);
		status.setIcon(on);
		initTable();
		btnActive.setEnabled(false);
		btnBlock.setEnabled(false);
	}

	private void initTable() {
		try {
			unactiveModel = bstaff.getAllUAUserToModel();
			activedModel = bstaff.getAllAUserToModel();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		unactiveTable.setModel(unactiveModel);
		activedTable.setModel(activedModel);
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
