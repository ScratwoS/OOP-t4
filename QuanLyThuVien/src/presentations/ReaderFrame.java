package presentations;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class ReaderFrame extends JInternalFrame {
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ReaderFrame() {
		super("Welcome", true,true,true,true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][]", "[][][][]"));
		
		JLabel lblHotBook = new JLabel("Hot Book");
		contentPane.add(lblHotBook, "cell 0 0");
		
		JLabel lblBook = new JLabel("Book 1");
		contentPane.add(lblBook, "cell 0 1");
		
		JLabel lblBook_1 = new JLabel("Book 2");
		contentPane.add(lblBook_1, "cell 1 1");
		
		JLabel lblBook_2 = new JLabel("Book 3");
		contentPane.add(lblBook_2, "cell 2 1");
		
		JLabel lblBook_3 = new JLabel("Book 4");
		contentPane.add(lblBook_3, "cell 3 1");
		
		JLabel lblBook_4 = new JLabel("Book 5");
		contentPane.add(lblBook_4, "cell 4 1");
		
		JLabel lblNewBook = new JLabel("New Book");
		contentPane.add(lblNewBook, "cell 0 2");
		
		JLabel lblBook_5 = new JLabel("Book 1");
		contentPane.add(lblBook_5, "cell 0 3");
		
		JLabel lblBook_6 = new JLabel("Book 2");
		contentPane.add(lblBook_6, "cell 1 3");
		
		JLabel lblBook_7 = new JLabel("Book 3");
		contentPane.add(lblBook_7, "cell 2 3");
		
		JLabel lblBook_8 = new JLabel("Book 4");
		contentPane.add(lblBook_8, "cell 3 3");
		
		JLabel lblBook_9 = new JLabel("Book 5");
		contentPane.add(lblBook_9, "cell 4 3");
	}
}
