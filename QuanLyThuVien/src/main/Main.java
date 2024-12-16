package main;

import java.sql.SQLException;

import javax.swing.UIManager;

import dataaccess.DAConnection;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import presentations.FrameManager;
import presentations.TroubleshootFrame;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			DAConnection.getConnection();
			FrameManager frame = new FrameManager();
			frame.setVisible(true);
		} catch (ClassNotFoundException | SQLException e) {
			TroubleshootFrame frame = new TroubleshootFrame();
			frame.setLocationRelativeTo(null);
			frame.setTitle("Cài đặt kết nối tới cơ sở dữ liệu");
			frame.pack();
			frame.setVisible(true);
		}
		
	}

}
