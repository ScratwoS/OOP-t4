package dataaccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DAConnection {
    private static Connection con = null;

    private DAConnection() {
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        File file = new File("src/text/db.txt");
        String url = "";
        String user = "";
        String password = "";

        // Xử lý ngoại lệ FileNotFoundException
        try (Scanner sc = new Scanner(file)) {
            if (sc.hasNextLine()) url = sc.nextLine();
            if (sc.hasNextLine()) user = sc.nextLine();
            if (sc.hasNextLine()) password = sc.nextLine();
        } catch (FileNotFoundException e) {
            System.err.println("Lỗi: File db.txt không tìm thấy. Kiểm tra đường dẫn: " + file.getAbsolutePath());
            return null; // hoặc throw một ngoại lệ tùy chọn khác nếu cần
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Tạo kết nối tới database
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Driver mới
                con = DriverManager.getConnection(url, user, password);
                System.out.println("Kết nối thành công tới database!");
            } catch (SQLException e) {
                System.err.println("Lỗi kết nối tới database. Kiểm tra URL, user và password.");
                throw e; // Ném lại SQLException để xử lý ở Main
            } catch (ClassNotFoundException e) {
                System.err.println("Driver JDBC không tìm thấy.");
                throw e; // Ném lại ngoại lệ ClassNotFoundException
            }
        }
        return con;
    }
}
