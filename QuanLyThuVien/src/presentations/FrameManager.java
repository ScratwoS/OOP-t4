package presentations;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;

public class FrameManager extends JFrame {
    private JPanel contentPane;
    private LoadImage loader = new LoadImage();
    private JDesktopPane desktopPane;
    private BookManager bookFrame;
    private OrderManager orderFrame;
    private ReaderFrame readFrame;
    private FrameManager frame;
    private StaticsFrame staticsFrame;
    private JMenu mnManagement, mnChucNang;
    private MyButton btnOrder, btnBook, btnLogout, btnMember, btnAccount, btnAcMng, btnStatics, btnSetup;
    private ImageIcon on, fail, querying;
    private JLabel lblStatus;
    private MemberFrame memberFrame;
    private AccountManager accountFrame;
    private SearchBookFrame searchBookFrame;

    public FrameManager() {
        frame = this;
        on = new ImageIcon(loader.loadImage("on.png"));
        fail = new ImageIcon(loader.loadImage("off.png"));
        querying = new ImageIcon(loader.loadImage("busy.png"));
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        desktopPane = new JDesktopPane();
        contentPane.add(desktopPane, BorderLayout.CENTER);

        setTitle("Quản Lý Thư Viện");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnManagement = new JMenu("Quản Lý Danh Mục");
        menuBar.add(mnManagement);

        JMenuItem mntmBook = new JMenuItem("Quản Lý Sách");
        mnManagement.add(mntmBook);

        JMenuItem mntmOrder = new JMenuItem("Quản Lý Mượn và Trả");
        mnManagement.add(mntmOrder);

        JMenuItem mntmMember = new JMenuItem("Quản Lý Độc Giả");
        mnManagement.add(mntmMember);

        mnChucNang = new JMenu("Chức Năng");
        menuBar.add(mnChucNang);

        JMenuItem mntmBaoCaoCac = new JMenuItem("Bao Cao Cac Kieu");
        mnChucNang.add(mntmBaoCaoCac);

        JMenu mnHelp = new JMenu("Trợ Giúp");
        menuBar.add(mnHelp);

        JMenuItem mntmHelp = new JMenuItem("Về Chương Trình");
        mnHelp.add(mntmHelp);

        JMenuItem mntmVeChungToi = new JMenuItem("Về Nhóm Phát Triển");
        mnHelp.add(mntmVeChungToi);

        lblStatus = new JLabel("Người đọc đang sử dụng.");
        lblStatus.setIcon(fail);
        contentPane.add(lblStatus, BorderLayout.SOUTH);

        JPanel btnPanel = new JPanel();
        contentPane.add(btnPanel, BorderLayout.NORTH);

        btnAccount = new MyButton("Tài Khoản");
        btnAccount.setIcon(new ImageIcon(loader.loadImage("account_icon.png")));
        btnAccount.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                LoginFrame dialog = new LoginFrame(frame);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
                dialog.setLocationRelativeTo(frame);
            }
        });
        btnPanel.add(btnAccount);

        searchBookFrame = new SearchBookFrame();
        searchBookFrame.setLocation(80, 80);
        desktopPane.add(searchBookFrame);
        MyButton btnSearch = new MyButton("Tìm Sách");
        btnSearch.setIcon(new ImageIcon(loader.loadImage("search.png")));
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBookFrame.setVisible(true);
                searchBookFrame.pack();
            }
        });
        btnPanel.add(btnSearch);

        btnAcMng = new MyButton("Quản Lý Tài Khoản");
        btnAcMng.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (accountFrame != null) {
                    accountFrame.dispose();
                }
                accountFrame = new AccountManager();
                accountFrame.setLocation(80, 80);
                desktopPane.add(accountFrame);
                accountFrame.setVisible(true);
                try {
                    accountFrame.setSelected(true);
                } catch (PropertyVetoException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnAcMng.setIcon(new ImageIcon(loader.loadImage("account-mng.png")));
        btnPanel.add(btnAcMng);

        btnOrder = new MyButton("Mượn/Trả Sách");
        btnOrder.setIcon(new ImageIcon(loader.loadImage("order_icon.png")));
        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (orderFrame != null) {
                    orderFrame.dispose();
                }
                orderFrame = new OrderManager();
                orderFrame.setLocation(100, 100);
                desktopPane.add(orderFrame);
                orderFrame.setVisible(true);
                try {
                    orderFrame.setSelected(true);
                } catch (PropertyVetoException e) {
                    e.printStackTrace();
                }
            }
        });
        btnPanel.add(btnOrder);

        btnBook = new MyButton("Kho Sách");
        btnBook.setIcon(new ImageIcon(loader.loadImage("lib_icon.png")));
        btnBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (bookFrame != null) {
                    bookFrame.dispose();
                }
                bookFrame = new BookManager();
                bookFrame.setLocation(40, 40);
                desktopPane.add(bookFrame);
                bookFrame.setVisible(true);
                try {
                    bookFrame.setSelected(true);
                } catch (PropertyVetoException e) {
                    e.printStackTrace();
                }
            }
        });
        btnPanel.add(btnBook);

        btnMember = new MyButton("Độc Giả");
        btnMember.setIcon(new ImageIcon(loader.loadImage("reader_icon.png")));
        btnMember.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (memberFrame != null) {
                    memberFrame.dispose();
                }
                memberFrame = new MemberFrame();
                memberFrame.setLocation(60, 60);
                desktopPane.add(memberFrame);
                memberFrame.setVisible(true);
                try {
                    memberFrame.setSelected(true);
                } catch (PropertyVetoException e) {
                    e.printStackTrace();
                }
            }
        });
        btnPanel.add(btnMember);

        btnStatics = new MyButton("Thống Kê");
        btnStatics.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (staticsFrame != null) {
                    staticsFrame.dispose();
                }
                staticsFrame = new StaticsFrame();
                staticsFrame.setLocation(140, 140);
                desktopPane.add(staticsFrame);
                staticsFrame.setVisible(true);
                try {
                    staticsFrame.setSelected(true);
                } catch (PropertyVetoException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnStatics.setIcon(new ImageIcon(loader.loadImage("statics.png")));
        btnPanel.add(btnStatics);

        btnSetup = new MyButton("Cài Đặt Hệ Thống");
        btnSetup.setIcon(new ImageIcon(loader.loadImage("setting.png")));
        btnSetup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SettingFrame setting = new SettingFrame();
                setting.setVisible(true);
                setting.setLocationRelativeTo(frame);
            }
        });
        btnPanel.add(btnSetup);

        btnLogout = new MyButton("Đăng Xuất");
        btnLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderFrame != null) {
                    orderFrame.dispose();
                }
                if (memberFrame != null) {
                    memberFrame.dispose();
                }
                if (bookFrame != null) {
                    bookFrame.dispose();
                }
                if (accountFrame != null) {
                    accountFrame.dispose();
                }
                if (staticsFrame != null) {
                    staticsFrame.dispose();
                }

                btnAccount.removeActionListener(btnAccount.getActionListeners()[0]);
                btnAccount.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        LoginFrame dialog = new LoginFrame(frame);
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.setVisible(true);
                        dialog.setLocationRelativeTo(frame);
                    }
                });
                mnManagement.setEnabled(false);
                mnChucNang.setEnabled(false);
                btnAcMng.setEnabled(false);
                btnMember.setEnabled(false);
                btnOrder.setEnabled(false);
                btnBook.setEnabled(false);
                btnSetup.setEnabled(false);
                btnStatics.setEnabled(false);
                btnLogout.setEnabled(false);
            }
        });
        btnLogout.setIcon(new ImageIcon(loader.loadImage("logout_icon.png")));
        btnPanel.add(btnLogout);

        mnManagement.setEnabled(false);
        mnChucNang.setEnabled(false);
        btnAcMng.setEnabled(false);
        btnMember.setEnabled(false);
        btnOrder.setEnabled(false);
        btnBook.setEnabled(false);
        btnSetup.setEnabled(false);
        btnStatics.setEnabled(false);
        btnLogout.setEnabled(false);
    }

    public void management(int id, String name, int activited, int role) {
        if (activited == 1) {
            bookFrame = new BookManager();
            bookFrame.setLocation(40, 40);
            desktopPane.add(bookFrame);
            memberFrame = new MemberFrame();
            memberFrame.setLocation(60, 60);
            desktopPane.add(memberFrame);
            accountFrame = new AccountManager();
            accountFrame.setLocation(80, 80);
            desktopPane.add(accountFrame);
            orderFrame = new OrderManager();
            orderFrame.setLocation(100, 100);
            desktopPane.add(orderFrame);
            readFrame = new ReaderFrame();
            readFrame.setLocation(120, 120);
            desktopPane.add(readFrame);
            staticsFrame = new StaticsFrame();
            staticsFrame.setLocation(140, 140);
            desktopPane.add(staticsFrame);

            lblStatus.setText("Xin chào " + name + "!");
            lblStatus.setIcon(on);
            if (role == 1) {
                mnManagement.setEnabled(true);
                mnChucNang.setEnabled(true);
                btnAcMng.setEnabled(true);
                btnMember.setEnabled(true);
                btnOrder.setEnabled(true);
                btnBook.setEnabled(true);
                btnSetup.setEnabled(true);
                btnStatics.setEnabled(true);
            } else if (role == 2) {
                mnManagement.setEnabled(true);
                mnChucNang.setEnabled(true);
                btnAcMng.setEnabled(true);
                btnStatics.setEnabled(true);
            } else {
                mnManagement.setEnabled(true);
                mnChucNang.setEnabled(true);
                btnMember.setEnabled(true);
                btnOrder.setEnabled(true);
                btnBook.setEnabled(true);
                btnStatics.setEnabled(true);
            }
        } else {
            lblStatus.setText("Xin chào " + name + "!. Tài khoản của bạn chưa được kích hoạt!");
            lblStatus.setIcon(querying);
        }
        btnAccount.removeActionListener(btnAccount.getActionListeners()[0]);
        btnAccount.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                FrameAccount edit = new FrameAccount(id);
                edit.setLocationRelativeTo(frame);
                edit.setVisible(true);
            }
        });
        btnLogout.setEnabled(true);
    }

    class MyButton extends JButton {
        public MyButton(String text) {
            setText(text);
            setVerticalTextPosition(SwingConstants.BOTTOM);
            setHorizontalTextPosition(SwingConstants.CENTER);
        }
    }
}
