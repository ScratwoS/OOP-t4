package presentations;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import business.BBook;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchBookFrame extends JInternalFrame {
	private JPanel contendPane;
	private JTextField textSearch;
	private JScrollPane scrollPane;
	private JLabel lblQueryTime;
	private JTable table;
	private BBook bbook;
	private DefaultTableModel model;

	public SearchBookFrame() {
		super("Tìm kiếm sách", true, true, true, true);
		contendPane = new JPanel();
		setContentPane(contendPane);
		contendPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		contendPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initTable();
			}
		});
		panel.add(btnSearch, BorderLayout.EAST);

		textSearch = new JTextField();
		panel.add(textSearch, BorderLayout.CENTER);
		textSearch.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createTitledBorder("Kết quả tìm kiếm"));
		contendPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		lblQueryTime = new JLabel("Query time");
		contendPane.add(lblQueryTime, BorderLayout.SOUTH);
	}

	private void initTable() {
		bbook = new BBook();
		try {
			table.setModel(bbook.searchOrderManager(textSearch.getText()));
		} catch (ClassNotFoundException | SQLException e) {
		}

	}

}
