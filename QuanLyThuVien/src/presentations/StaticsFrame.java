package presentations;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

import business.BStatics;
import java.awt.CardLayout;

public class StaticsFrame extends JInternalFrame {

	private JPanel contentPanel;
	private BStatics bstatics;
	private PieDataset category, type;
	/**
	 * Create the dialog.
	 */
	public StaticsFrame() {
		super("Thống Kê",true,true,true,true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPanel = new JPanel();
		bstatics = new BStatics();
		setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel selectPnl = new JPanel();
		contentPanel.add(selectPnl, BorderLayout.NORTH);
		selectPnl.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Thống kê theo thể loại");
		comboBox.addItem("Thống kê theo loại");
		selectPnl.add(comboBox, "cell 1 0,growx");
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new CardLayout(0, 0));
		
		initDataSet();
		
		JFreeChart categoryChart = ChartFactory.createPieChart("Thống Kê theo Thể Loại", category, true, true, false);
		JFreeChart typeChart = ChartFactory.createPieChart("Thống Kê theo Kiểu Sách",type, true, true, false);
		ChartPanel catePnl = new ChartPanel(categoryChart);
		panel.add(catePnl,"Thống kê theo thể loại");
		ChartPanel typePnl = new ChartPanel(typeChart);
		panel.add(typePnl,"Thống kê theo loại");
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
			    CardLayout cl = (CardLayout)(panel.getLayout());
			    cl.show(panel, (String)evt.getItem());
			}
		});
		pack();
	}
	private void initDataSet() {
		try {
			category = bstatics.createDatasetCategory();
			type = bstatics.createDatasetType();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
