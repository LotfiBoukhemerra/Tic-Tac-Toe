/**
 * CopyRight © 2018 LOTFI BOUKHEMERRA - All Rights Reserved - developed by LOTFI BOUKHEMERRA.
 */
package run;
/**
 *
 * @author BOUKHEMERRA Lotfi.
 * repository https://github.com/LotfiBoukhemerra
 */
import java.awt.Font;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GamePanel extends javax.swing.JFrame {

    private static byte Game_Mode = 0;
    /**
     * Game_Mode = 1 for one player.
     * Game_Mode = 2 for tow players in the same Panel.
     * Game_Mode = 3 for tow players in different Panel and this is the server.
     * Game_Mode = 4 for tow players in different Panel and this is the client.
     */
    static byte oIsPlay =0;
    static byte wait =0;
    private static byte cpt = 0;// هذا المتغير يقوم بحساب عدد مرات اللعب
    private static final Font TOHOMA = new Font("Tahoma", Font.BOLD, 22);
    private final ImageIcon PLAYER_X = new ImageIcon(getClass().getResource("/tc/ex.png"));
    private final ImageIcon PLAYER_O = new ImageIcon(getClass().getResource("/tc/oh.png"));
    private final ImageIcon MENU_MIN = new ImageIcon(getClass().getResource("/tc/2x2.png"));
    private final ImageIcon MENU_MAX = new ImageIcon(getClass().getResource("/tc/menu.png"));
    private final ImageIcon PLAYER_X_TURN = new ImageIcon(getClass().getResource("/tc/xp.png"));
    private final ImageIcon PLAYER_O_TURN = new ImageIcon(getClass().getResource("/tc/op.png"));
    private final ImageIcon NUMBER_0 = new ImageIcon(getClass().getResource("/tc/co0.png"));
    private final ImageIcon NUMBER_1 = new ImageIcon(getClass().getResource("/tc/co1.png"));
    private final ImageIcon NUMBER_2 = new ImageIcon(getClass().getResource("/tc/co2.png"));
    private final ImageIcon NUMBER_3 = new ImageIcon(getClass().getResource("/tc/co3.png"));
    private final ImageIcon NUMBER_4 = new ImageIcon(getClass().getResource("/tc/co4.png"));
    private final ImageIcon NUMBER_5 = new ImageIcon(getClass().getResource("/tc/co5.png"));
    private final ImageIcon NUMBER_6 = new ImageIcon(getClass().getResource("/tc/co6.png"));
    private final ImageIcon NUMBER_7 = new ImageIcon(getClass().getResource("/tc/co7.png"));
    private final ImageIcon NUMBER_8 = new ImageIcon(getClass().getResource("/tc/co8.png"));
    private final ImageIcon NUMBER_9 = new ImageIcon(getClass().getResource("/tc/co9.png"));
    private final ImageIcon CROSS_BENT_RIGHT = new ImageIcon(getClass().getResource("/tc/cross_4x.png"));
    private final ImageIcon CROSS_BENT_LEFT = new ImageIcon(getClass().getResource("/tc/cross_3x.png"));
    private final ImageIcon CROSS_HORIZONTAL = new ImageIcon(getClass().getResource("/tc/cross_1x.png"));
    private final ImageIcon CROSS_VERTICAL = new ImageIcon(getClass().getResource("/tc/cross_2x.png"));
    private final ImageIcon X_TOAST = new ImageIcon(getClass().getResource("/tc/ex_toast.png"));
    private final ImageIcon O_TOAST = new ImageIcon(getClass().getResource("/tc/oh_toast.png"));
    private static int cpt_4_change_gamer;
    private short xwin, owin, draw;
    private short spalsh_Screen_cpt_logo = 0, spalsh_Screen_logo_part = 0;// if spalsh_Screen_cpt_logo = 18 spalash Screen will be ended.
    private final String IPV4PATTERN = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
    private final Pattern PATT = Pattern.compile(IPV4PATTERN);
    private static String Gamer, readData;
    private boolean play = true;//هذا المتغير قمنا بإنشائه حتى لا يستطيع أحد إكمال اللعب في حال فوز الآخر 
    private static boolean serverIsOn = false;
    static Algorithms NewGame = new Algorithms();
    private static ServerSocket svr;
    private static Socket con;
    private static DataInputStream input;
    private static DataOutputStream output;
    private static byte shwp=0; // if shwp = 0 the the waiting panel will be hide.(shwp: show hide waiting panel.
    private BufferedReader reader = null;
    Waiting jfx = new Waiting();
    // begin of constractor
    public GamePanel() {
        initComponents();       
        setTitle("TIC TAC TOE");
        setIconImage(new ImageIcon(getClass().getResource("/tc/icon.png")).getImage());        
        ////////// score Data bases //////////////////
        // هنا يتم إستراد سجل النتائج من قاعدة البيانات
        xwin = Short.parseShort(set_DB("xwin"));
        owin = Short.parseShort(set_DB("owin"));
        draw = Short.parseShort(set_DB("draw"));
        //////////////////////////////////////////////
        // start Splash Screen code.
        new Thread() {
            // This Thread is for to make splash Screen. 
            @Override
            public void run() {
                while (true) {
                    spalsh_Screen_cpt_logo++;
                    spalsh_Screen_logo_part++;
                    spalsh_Screen_logo_part = (spalsh_Screen_logo_part == 7 ?  1 : spalsh_Screen_logo_part );
                    jLabel_loading.setIcon(new ImageIcon(getClass().getResource("/tc/connectindot" + String.valueOf(spalsh_Screen_logo_part) + ".png"))); 
                    if (spalsh_Screen_cpt_logo == 18) {
                        splashScreen.setVisible(false);
                        remove(splashScreen);
                        menu.setVisible(true);
                        break;
                    }
                    makeSleep(60);                   
                }// end while
            }
        }.start(); 
        getContentPane().add(jfx, "card4");
        jfx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jfxKeyPressed(evt);
            }
        });
    }// end constractor

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splashScreen = new javax.swing.JPanel();
        jLabel_logoText = new javax.swing.JLabel();
        jLabel_loading = new javax.swing.JLabel();
        jLabel_logoIcon = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();
        samePanel_btn = new javax.swing.JButton();
        host_btn = new javax.swing.JButton();
        play2x2_btn = new javax.swing.JButton();
        jTextField_Get_Ip = new javax.swing.JTextField();
        backToMenu_btn1 = new javax.swing.JButton();
        onePlayer_btn = new javax.swing.JButton();
        towPlayer_btn = new javax.swing.JButton();
        setting_btn = new javax.swing.JButton();
        about_btn = new javax.swing.JButton();
        exit_btn = new javax.swing.JButton();
        jLabelMenu = new javax.swing.JLabel();
        jLabelDevloped = new javax.swing.JLabel();
        jLabelMenuBackground = new javax.swing.JLabel();
        Gamepanel = new javax.swing.JPanel();
        back = new javax.swing.JButton();
        jLabel_Cross_win = new javax.swing.JLabel();
        jLabel_Owin = new javax.swing.JLabel();
        jLabel_Xwin = new javax.swing.JLabel();
        jLabel_Draw = new javax.swing.JLabel();
        jLabelDraw_1 = new javax.swing.JLabel();
        jLabelDraw_10 = new javax.swing.JLabel();
        jLabelDraw_100 = new javax.swing.JLabel();
        jLabelDraw_1000 = new javax.swing.JLabel();
        jLabelXwin_1 = new javax.swing.JLabel();
        jLabelXwin_10 = new javax.swing.JLabel();
        jLabelXwin_100 = new javax.swing.JLabel();
        jLabelXwin_1000 = new javax.swing.JLabel();
        jLabelOwin_1 = new javax.swing.JLabel();
        jLabelOwin_10 = new javax.swing.JLabel();
        jLabelOwin_100 = new javax.swing.JLabel();
        jLabelOwin_1000 = new javax.swing.JLabel();
        jLabel_turn_of = new javax.swing.JLabel();
        turn_Text = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();
        jButtonPlas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(345, 693));
        setMinimumSize(new java.awt.Dimension(345, 693));
        setResizable(false);
        setSize(new java.awt.Dimension(345, 693));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        splashScreen.setPreferredSize(new java.awt.Dimension(345, 613));
        splashScreen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                splashScreenKeyPressed(evt);
            }
        });
        splashScreen.setLayout(null);

        jLabel_logoText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_logoText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/logo.png"))); // NOI18N
        splashScreen.add(jLabel_logoText);
        jLabel_logoText.setBounds(0, 340, 362, 120);

        jLabel_loading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/connectindot1.png"))); // NOI18N
        splashScreen.add(jLabel_loading);
        jLabel_loading.setBounds(0, 530, 362, 60);

        jLabel_logoIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_logoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/icon.png"))); // NOI18N
        splashScreen.add(jLabel_logoIcon);
        jLabel_logoIcon.setBounds(0, 65, 362, 210);

        getContentPane().add(splashScreen, "card1");
        splashScreen.getAccessibleContext().setAccessibleDescription("");

        menu.setMaximumSize(new java.awt.Dimension(345, 613));
        menu.setMinimumSize(new java.awt.Dimension(345, 613));
        menu.setPreferredSize(new java.awt.Dimension(345, 613));
        menu.setLayout(null);

        samePanel_btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        samePanel_btn.setText("Same Panel");
        samePanel_btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        samePanel_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                samePanel_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                samePanel_btnMouseExited(evt);
            }
        });
        samePanel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                samePanel_btnActionPerformed(evt);
            }
        });
        menu.add(samePanel_btn);
        samePanel_btn.setBounds(375, 190, 190, 40);

        host_btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        host_btn.setLabel("Host");
        host_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                host_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                host_btnMouseExited(evt);
            }
        });
        host_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                host_btnActionPerformed(evt);
            }
        });
        menu.add(host_btn);
        host_btn.setBounds(375, 280, 190, 40);

        play2x2_btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        play2x2_btn.setText("Play");
        play2x2_btn.setRolloverEnabled(false);
        play2x2_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play2x2_btnActionPerformed(evt);
            }
        });
        menu.add(play2x2_btn);
        play2x2_btn.setBounds(580, 380, 60, 40);

        jTextField_Get_Ip.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField_Get_Ip.setToolTipText("");
        jTextField_Get_Ip.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ip Adress", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        jTextField_Get_Ip.setName(""); // NOI18N
        menu.add(jTextField_Get_Ip);
        jTextField_Get_Ip.setBounds(375, 380, 190, 40);

        backToMenu_btn1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        backToMenu_btn1.setText("Back");
        backToMenu_btn1.setBorder(null);
        backToMenu_btn1.setFocusPainted(false);
        backToMenu_btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backToMenu_btn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backToMenu_btn1MouseExited(evt);
            }
        });
        backToMenu_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMenu_btn1ActionPerformed(evt);
            }
        });
        menu.add(backToMenu_btn1);
        backToMenu_btn1.setBounds(375, 480, 190, 40);

        onePlayer_btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        onePlayer_btn.setText("ONE PLAYER");
        onePlayer_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                onePlayer_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                onePlayer_btnMouseExited(evt);
            }
        });
        onePlayer_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onePlayer_btnActionPerformed(evt);
            }
        });
        menu.add(onePlayer_btn);
        onePlayer_btn.setBounds(85, 190, 190, 40);

        towPlayer_btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        towPlayer_btn.setText("TWO PLAYER");
        towPlayer_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                towPlayer_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                towPlayer_btnMouseExited(evt);
            }
        });
        towPlayer_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                towPlayer_btnActionPerformed(evt);
            }
        });
        menu.add(towPlayer_btn);
        towPlayer_btn.setBounds(85, 280, 190, 40);

        setting_btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        setting_btn.setText("SETTING");
        setting_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setting_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setting_btnMouseExited(evt);
            }
        });
        setting_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setting_btnActionPerformed(evt);
            }
        });
        menu.add(setting_btn);
        setting_btn.setBounds(85, 380, 190, 40);

        about_btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        about_btn.setText("ABOUT");
        about_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                about_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                about_btnMouseExited(evt);
            }
        });
        about_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                about_btnActionPerformed(evt);
            }
        });
        menu.add(about_btn);
        about_btn.setBounds(85, 480, 190, 40);

        exit_btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        exit_btn.setText("Exit");
        exit_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exit_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exit_btnMouseExited(evt);
            }
        });
        exit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_btnActionPerformed(evt);
            }
        });
        menu.add(exit_btn);
        exit_btn.setBounds(85, 570, 190, 40);

        jLabelMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/menu.png"))); // NOI18N
        jLabelMenu.setToolTipText("");
        menu.add(jLabelMenu);
        jLabelMenu.setBounds(0, 33, 360, 90);

        jLabelDevloped.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDevloped.setForeground(new java.awt.Color(54, 60, 63));
        jLabelDevloped.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDevloped.setText("Developed by LOTFI BOUKHEMERRA");
        jLabelDevloped.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelDevlopedMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelDevlopedMouseExited(evt);
            }
        });
        menu.add(jLabelDevloped);
        jLabelDevloped.setBounds(0, 655, 360, 17);

        jLabelMenuBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/bgx.PNG"))); // NOI18N
        jLabelMenuBackground.setMaximumSize(new java.awt.Dimension(345, 629));
        jLabelMenuBackground.setMinimumSize(new java.awt.Dimension(345, 629));
        jLabelMenuBackground.setPreferredSize(new java.awt.Dimension(345, 629));
        menu.add(jLabelMenuBackground);
        jLabelMenuBackground.setBounds(0, 0, 373, 748);

        getContentPane().add(menu, "card2");

        Gamepanel.setAlignmentX(0.0F);
        Gamepanel.setAlignmentY(0.0F);
        Gamepanel.setPreferredSize(new java.awt.Dimension(345, 693));
        Gamepanel.setLayout(null);

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/back.png"))); // NOI18N
        back.setToolTipText("back to menu");
        back.setAlignmentY(0.0F);
        back.setContentAreaFilled(false);
        back.setRequestFocusEnabled(false);
        back.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/back_rolaver.png"))); // NOI18N
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        Gamepanel.add(back);
        back.setBounds(10, 630, 60, 50);
        Gamepanel.add(jLabel_Cross_win);
        jLabel_Cross_win.setBounds(0, 20, 340, 450);

        jLabel_Owin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/w1.png"))); // NOI18N
        Gamepanel.add(jLabel_Owin);
        jLabel_Owin.setBounds(30, 500, 90, 30);

        jLabel_Xwin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/w2.png"))); // NOI18N
        Gamepanel.add(jLabel_Xwin);
        jLabel_Xwin.setBounds(140, 500, 80, 30);

        jLabel_Draw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/w3.png"))); // NOI18N
        Gamepanel.add(jLabel_Draw);
        jLabel_Draw.setBounds(257, 500, 80, 30);

        jLabelDraw_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/co9.png"))); // NOI18N
        Gamepanel.add(jLabelDraw_1);
        jLabelDraw_1.setBounds(310, 530, 18, 70);

        jLabelDraw_10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/co9.png"))); // NOI18N
        Gamepanel.add(jLabelDraw_10);
        jLabelDraw_10.setBounds(290, 530, 18, 70);

        jLabelDraw_100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/co9.png"))); // NOI18N
        Gamepanel.add(jLabelDraw_100);
        jLabelDraw_100.setBounds(270, 530, 18, 70);

        jLabelDraw_1000.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/co9.png"))); // NOI18N
        Gamepanel.add(jLabelDraw_1000);
        jLabelDraw_1000.setBounds(250, 530, 18, 70);

        jLabelXwin_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/co9.png"))); // NOI18N
        Gamepanel.add(jLabelXwin_1);
        jLabelXwin_1.setBounds(200, 530, 18, 70);

        jLabelXwin_10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/co9.png"))); // NOI18N
        Gamepanel.add(jLabelXwin_10);
        jLabelXwin_10.setBounds(180, 530, 18, 70);

        jLabelXwin_100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/co9.png"))); // NOI18N
        Gamepanel.add(jLabelXwin_100);
        jLabelXwin_100.setBounds(160, 530, 18, 70);

        jLabelXwin_1000.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/co9.png"))); // NOI18N
        Gamepanel.add(jLabelXwin_1000);
        jLabelXwin_1000.setBounds(140, 530, 18, 70);

        jLabelOwin_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/co9.png"))); // NOI18N
        Gamepanel.add(jLabelOwin_1);
        jLabelOwin_1.setBounds(90, 530, 18, 70);

        jLabelOwin_10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/co9.png"))); // NOI18N
        Gamepanel.add(jLabelOwin_10);
        jLabelOwin_10.setBounds(70, 530, 18, 70);

        jLabelOwin_100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/co9.png"))); // NOI18N
        Gamepanel.add(jLabelOwin_100);
        jLabelOwin_100.setBounds(50, 530, 18, 70);

        jLabelOwin_1000.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/co9.png"))); // NOI18N
        Gamepanel.add(jLabelOwin_1000);
        jLabelOwin_1000.setBounds(30, 530, 18, 70);

        jLabel_turn_of.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/w2.png"))); // NOI18N
        Gamepanel.add(jLabel_turn_of);
        jLabel_turn_of.setBounds(180, 20, 122, 50);

        turn_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/turn.png"))); // NOI18N
        Gamepanel.add(turn_Text);
        turn_Text.setBounds(80, 20, 70, 50);

        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Gamepanel.add(jButton2);
        jButton2.setBounds(130, 90, 90, 95);

        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        Gamepanel.add(jButton3);
        jButton3.setBounds(240, 90, 90, 95);

        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Gamepanel.add(jButton1);
        jButton1.setBounds(30, 90, 90, 95);

        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        Gamepanel.add(jButton4);
        jButton4.setBounds(30, 210, 90, 95);

        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        Gamepanel.add(jButton5);
        jButton5.setBounds(130, 210, 90, 95);

        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        Gamepanel.add(jButton6);
        jButton6.setBounds(240, 210, 90, 95);

        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        Gamepanel.add(jButton7);
        jButton7.setBounds(30, 330, 90, 95);

        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setFocusPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        Gamepanel.add(jButton8);
        jButton8.setBounds(130, 330, 90, 95);

        jButton9.setBorder(null);
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setFocusPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        Gamepanel.add(jButton9);
        jButton9.setBounds(240, 330, 90, 95);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/a.png"))); // NOI18N
        Gamepanel.add(jLabel1);
        jLabel1.setBounds(115, 90, 15, 340);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/a.png"))); // NOI18N
        Gamepanel.add(jLabel4);
        jLabel4.setBounds(230, 90, 15, 340);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/b.png"))); // NOI18N
        Gamepanel.add(jLabel7);
        jLabel7.setBounds(20, 190, 320, 15);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/b.png"))); // NOI18N
        Gamepanel.add(jLabel10);
        jLabel10.setBounds(20, 310, 320, 15);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/bgx.PNG"))); // NOI18N
        Gamepanel.add(Background);
        Background.setBounds(0, 0, 373, 748);

        jButtonPlas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/oh.png"))); // NOI18N
        jButtonPlas.setBorder(null);
        jButtonPlas.setBorderPainted(false);
        jButtonPlas.setContentAreaFilled(false);
        jButtonPlas.setFocusPainted(false);
        jButtonPlas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/xp.png"))); // NOI18N
        jButtonPlas.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/tc/op.png"))); // NOI18N
        Gamepanel.add(jButtonPlas);
        jButtonPlas.setBounds(375, 90, 90, 95);

        getContentPane().add(Gamepanel, "card3");

        getAccessibleContext().setAccessibleDescription("");

        setSize(new java.awt.Dimension(378, 730));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        play(2, 2, jButton9);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        play(2, 1, jButton8);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        play(2, 0, jButton7);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        play(1, 2, jButton6);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        play(1, 1, jButton5);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        play(1, 0, jButton4);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        play(0, 2, jButton3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        play(0, 1, jButton2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        play(0, 0, jButton1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // كل هذه الأحداث تنفذ مباشرة بعد فتح شاشة البرنامج
        jLabelOwin_1000.setIcon(null);
        jLabelOwin_100.setIcon(null);
        jLabelOwin_10.setIcon(null);
        jLabelOwin_1.setIcon(null);
        jLabelXwin_1000.setIcon(null);
        jLabelXwin_100.setIcon(null);
        jLabelXwin_10.setIcon(null);
        jLabelXwin_1.setIcon(null);
        jLabelDraw_1000.setIcon(null);
        jLabelDraw_100.setIcon(null);
        jLabelDraw_10.setIcon(null);
        jLabelDraw_1.setIcon(null);
        Show_Results(jLabelOwin_10, jLabelOwin_100, owin);
        Show_Results(jLabelXwin_10, jLabelXwin_100, xwin);
        Show_Results(jLabelDraw_10, jLabelDraw_100, draw);
    }//GEN-LAST:event_formWindowOpened

    private void onePlayer_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onePlayer_btnActionPerformed
        // One player Button.
        Sounds.play("click");
        cpt_4_change_gamer = new Random().nextInt(70);
        turn_of_Gamer();
        Game_Mode = 1;
        menu.setVisible(false);
        jLabel_turn_of.setVisible(false);
        turn_Text.setVisible(false);
        Gamepanel.setVisible(true);
        Toast.makeText(this, "You Are The Player X", null, Toast.Style.NORMAL).display();
        makeSleep(900);
        /////// VS machin //////////////////////////////
        if (Gamer.equals("o")) {
            change_Gamer();
            turn_of_Gamer();
            playVsMachine();
        }/////////////////////////////////////////////////
    }//GEN-LAST:event_onePlayer_btnActionPerformed

    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_btnActionPerformed
        // Exit Button
        Sounds.play("click");
        makeSleep(400);
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_exit_btnActionPerformed

    private void about_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_about_btnActionPerformed
        // About Button   
        Sounds.play("click");
        String about
                = "<html>"
                + "<center><i><h1><font color=#df0025>TIC TAC TOE</font></h1></i></center><br>"
                + "<b><font color=#000000>Version:</font></b> &nbsp <b> 2.0 </b><br>"
                + "<p>Developed by <font size=+1><b>lotfi boukhemerra</b></font></p><br>"                
                + "<b><font color=#007aed>Connect with me:</font></b><br>"
                + "<b><font color=#f0b800>E-mail:</font></b> &nbsp lotfimln@gmail.com<br>"
                + "<b><font color=#f0b800>Facebook:</font></b> &nbsp LotfiBoukhemerraOfficialPage<br>"
                + "<b><font color=#f0b800>Twitter:</font></b> &nbsp @lotfi_bkmr <br>"
                + "<b><font color=#f0b800>Github:</font></b> &nbsp <a href= https://github.com/LotfiBoukhemerra TARGET=\"page\">github.com/LotfiBoukhemerra</a><br><br>"
                + "<p><i>© CopyRight 2018 lotfi boukhmerra - All Rights Reserved  </i></p>"
                + "<html>";
        JOptionPane.showMessageDialog(this, about, "About Tic Tac Toe", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_about_btnActionPerformed

    private void towPlayer_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_towPlayer_btnActionPerformed
        // choose two player.
        // إختيار اللعب الثناني
        Sounds.play("click");
        new Thread() {
            @Override
            public void run() {
                boolean goIn = false;
                for (int i = 374; i > 84; i--) {
                    samePanel_btn.setLocation(i, 190);
                    host_btn.setLocation(i, 280);
                    jTextField_Get_Ip.setLocation(i, 380);
                    play2x2_btn.setLocation(i + 205, 380);
                    backToMenu_btn1.setLocation(i, 480);
                    if ((i - 342) > -91 && goIn == false) {
                        jLabelMenu.setLocation(0, i - 342);
                    }
                    if ((i - 290) > -192 && (i - 290) < 85) {
                        onePlayer_btn.setLocation((i - 290), 190);
                        towPlayer_btn.setLocation((i - 290), 280);
                        setting_btn.setLocation((i - 290), 380);
                        about_btn.setLocation((i - 290), 480);
                    }
                    makeSleep(0001);
                    if ((i - 342) == -90) {
                        goIn = true;
                        jLabelMenu.setIcon(MENU_MIN);
                    }
                    if ((163 - i) < 34 && goIn == true) {
                        jLabelMenu.setLocation(0, 163 - i);
                    }
                }// end for
            }
        }.start();
    }//GEN-LAST:event_towPlayer_btnActionPerformed

    private void setting_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setting_btnActionPerformed
        Sounds.play("click");
        Toast.makeText(this, "   Coming soon . . .   ", null, Toast.Style.NORMAL).display();
    }//GEN-LAST:event_setting_btnActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // Back Button
        Sounds.play("back");
        Gamepanel.setVisible(false);
        menu.setVisible(true);
        Game_Mode = 0;
        restForNewGameMin(false);
        if (Game_Mode == 4 || Game_Mode == 3) {
            try {
                svr.close();
                con.close();
                input.close();
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_backActionPerformed

    private void onePlayer_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onePlayer_btnMouseEntered
        onePlayer_btn.setFont(TOHOMA);
    }//GEN-LAST:event_onePlayer_btnMouseEntered

    private void onePlayer_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onePlayer_btnMouseExited
        onePlayer_btn.setFont(exit_btn.getFont());
    }//GEN-LAST:event_onePlayer_btnMouseExited

    private void towPlayer_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_towPlayer_btnMouseEntered
        towPlayer_btn.setFont(TOHOMA);
    }//GEN-LAST:event_towPlayer_btnMouseEntered

    private void towPlayer_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_towPlayer_btnMouseExited
        towPlayer_btn.setFont(onePlayer_btn.getFont());
    }//GEN-LAST:event_towPlayer_btnMouseExited

    private void setting_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setting_btnMouseEntered
        setting_btn.setFont(TOHOMA);
    }//GEN-LAST:event_setting_btnMouseEntered

    private void setting_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setting_btnMouseExited
        setting_btn.setFont(towPlayer_btn.getFont());
    }//GEN-LAST:event_setting_btnMouseExited

    private void about_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_about_btnMouseEntered
        about_btn.setFont(TOHOMA);
    }//GEN-LAST:event_about_btnMouseEntered

    private void about_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_about_btnMouseExited
        about_btn.setFont(setting_btn.getFont());
    }//GEN-LAST:event_about_btnMouseExited

    private void exit_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_btnMouseEntered
        exit_btn.setFont(TOHOMA);
    }//GEN-LAST:event_exit_btnMouseEntered

    private void exit_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_btnMouseExited
        exit_btn.setFont(about_btn.getFont());
    }//GEN-LAST:event_exit_btnMouseExited

    private void samePanel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_samePanel_btnActionPerformed
        // Button for choose 2 player.
        // إختيار اللعب الثناني
        Sounds.play("click");
        cpt_4_change_gamer = new Random().nextInt(70);
        turn_of_Gamer();
        Game_Mode = 2;
        menu.setVisible(false);
        jLabel_turn_of.setVisible(true);
        turn_Text.setVisible(true);
        Gamepanel.setVisible(true);
    }//GEN-LAST:event_samePanel_btnActionPerformed

    private void samePanel_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_samePanel_btnMouseEntered
        samePanel_btn.setFont(TOHOMA);
    }//GEN-LAST:event_samePanel_btnMouseEntered

    private void samePanel_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_samePanel_btnMouseExited
        samePanel_btn.setFont(about_btn.getFont());
    }//GEN-LAST:event_samePanel_btnMouseExited

    private void host_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_host_btnMouseEntered
        host_btn.setFont(TOHOMA);
    }//GEN-LAST:event_host_btnMouseEntered

    private void host_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_host_btnMouseExited
        host_btn.setFont(about_btn.getFont());
    }//GEN-LAST:event_host_btnMouseExited

    private void play2x2_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play2x2_btnActionPerformed
        Sounds.play("click");
        Game_Mode = 4;
        Matcher matcher = PATT.matcher(jTextField_Get_Ip.getText());
        if ((jTextField_Get_Ip.getText().equalsIgnoreCase("localhost") || matcher.matches()) && !(jTextField_Get_Ip.getText().equals(""))) {
            try {
                //con = new Socket("127.0.0.1", 5000);// localHost
                con = new Socket(jTextField_Get_Ip.getText(), 5000);
                input = new DataInputStream(con.getInputStream());
                output = new DataOutputStream(con.getOutputStream());
                cpt_4_change_gamer = input.readInt();
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (cpt_4_change_gamer % 2 == 0) {
                jLabel_turn_of.setIcon(PLAYER_O_TURN);
                Gamer = "o";
            } else {
                jLabel_turn_of.setIcon(PLAYER_X_TURN);
                Gamer = "x";
            }
            menu.setVisible(false);
            Gamepanel.setVisible(true);
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            readData = input.readUTF();
                            if (readData.equals("xisplayed")) {
                                readData = input.readUTF();
                                get_Button(Integer.parseInt(readData.substring(0, 1)), Integer.parseInt(readData.substring(1, 2))).setIcon(PLAYER_X);
                                NewGame.Matrix[Integer.parseInt(readData.substring(0, 1))][Integer.parseInt(readData.substring(1, 2))] = "x";
                                Sounds.play("soundc1");
                                cpt_4_change_gamer++;
                                cpt++;
                                readData = null;
                                if (cpt_4_change_gamer % 2 == 0) {
                                    jLabel_turn_of.setIcon(PLAYER_O_TURN);
                                    Gamer = "o";
                                } else {
                                    jLabel_turn_of.setIcon(PLAYER_X_TURN);
                                    Gamer = "x";
                                }
                                checkIfSomoanIsWin();
                            }// end if.
                        } catch (IOException ex) {
                            try {
                                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                                svr.close();
                                con.close();
                                input.close();
                                output.close();
                            } catch (IOException ex1) {
                                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                        }
                    }// end while 2.
                }
            }.start();
        } else {
            Toast.makeText(this, "   wrong pattern   ", null, Toast.Style.NORMAL).display();
        }
    }//GEN-LAST:event_play2x2_btnActionPerformed

    private void backToMenu_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMenu_btn1ActionPerformed
        // back
        Sounds.play("click");
        new Thread() {
            @Override
            public void run() {
                boolean goIn = false;
                for (int i = 86; i < 376; i++) {
                    samePanel_btn.setLocation(i, 190);
                    host_btn.setLocation(i, 280);
                    jTextField_Get_Ip.setLocation(i, 380);
                    play2x2_btn.setLocation(i + 205, 380);
                    backToMenu_btn1.setLocation(i, 480);
                    if ((118 - i) > -91 && goIn == false) {
                        jLabelMenu.setLocation(0, (118 - i));
                    }
                    if ((i - 276) > -192 && (i - 276) < 86) {
                        onePlayer_btn.setLocation(i - 276, 190);
                        towPlayer_btn.setLocation(i - 276, 280);
                        setting_btn.setLocation(i - 276, 380);
                        about_btn.setLocation(i - 276, 480);
                    }
                    makeSleep(0001);
                    if ((118 - i) == -90) {
                        goIn = true;
                        //System.out.println(i);// i = 207
                        jLabelMenu.setIcon(MENU_MAX);
                        continue;
                    }
                    if ((i - 297) < 34 && goIn == true) {
                        jLabelMenu.setLocation(0, i - 297);
                    }
                }// end for
            }
        }.start();
    }//GEN-LAST:event_backToMenu_btn1ActionPerformed

    private void backToMenu_btn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backToMenu_btn1MouseEntered
        backToMenu_btn1.setFont(TOHOMA);
    }//GEN-LAST:event_backToMenu_btn1MouseEntered

    private void backToMenu_btn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backToMenu_btn1MouseExited
        backToMenu_btn1.setFont(about_btn.getFont());
    }//GEN-LAST:event_backToMenu_btn1MouseExited

    private void host_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_host_btnActionPerformed
        // host Button        
        Sounds.play("click");
        cpt_4_change_gamer = new Random().nextInt(70);
        if (cpt_4_change_gamer % 2 == 0) {
            jLabel_turn_of.setIcon(PLAYER_O_TURN);
            Gamer = "o";
        } else {
            jLabel_turn_of.setIcon(PLAYER_X_TURN);
            Gamer = "x";
        }
        Game_Mode = 3;
        menu.setVisible(false);
        jLabel_turn_of.setVisible(true);
        turn_Text.setVisible(true);               
        jfx.setVisible(true);       
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    //System.out.print("");
                    makeSleep(1);
                    if(shwp == 1){
                      Gamepanel.setVisible(true);
                    }
                    if (oIsPlay==1) {
                        checkIfSomoanIsWin();
                        oIsPlay=0;
                    }
                    if (Game_Mode == 0) {
                        break;                       
                    }
                }// end while                   
            }
        }.start();
    }//GEN-LAST:event_host_btnActionPerformed

    private void jLabelDevlopedMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDevlopedMouseEntered
        jLabelDevloped.setForeground(new java.awt.Color(240, 184, 0));
    }//GEN-LAST:event_jLabelDevlopedMouseEntered

    private void jLabelDevlopedMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDevlopedMouseExited
        jLabelDevloped.setForeground(new java.awt.Color(54, 60, 63));
    }//GEN-LAST:event_jLabelDevlopedMouseExited

    private void splashScreenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_splashScreenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_splashScreenKeyPressed
////////////////////////////////////////////////////////////////////    
    private void jfxKeyPressed(java.awt.event.KeyEvent evt) {                                
        if (evt.getKeyCode() == evt.VK_SPACE) {
            if (Game_Mode == 3) {
                jfx.setVisible(true);
                menu.setVisible(true);
            }
        }
    }      
//////////////// restForNewGameMin //////////////////////////////
    public void restForNewGameMin(boolean b) {
        cpt = 0;
        new Thread() {
            @Override
            public void run() {
                makeSleep((b ? 1600 : 0));
                jLabel_Cross_win.setIcon(null);
                jButton1.setIcon(null);
                jButton2.setIcon(null);
                jButton3.setIcon(null);
                jButton4.setIcon(null);
                jButton5.setIcon(null);
                jButton6.setIcon(null);
                jButton7.setIcon(null);
                jButton8.setIcon(null);
                jButton9.setIcon(null);
                play = true;
            }
        }.start();
        turn_of_Gamer();
        if (Game_Mode == 3 || Game_Mode == 4) {
            cpt_4_change_gamer--;
            
        }
        //////////// emptying the Matrix /////////////////////
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                NewGame.Matrix[i][j] = "";
            }
        }//////////////////////////////////////////////////////
        NewGame.win = 0; // it means: there is no winner.
        NewGame.drawCross = null;// for hiding the yallow cross.
    }// end restForNewGameMin.

//////////////// change_Gamer ///////////////////////////////      
    public void change_Gamer() {
        // هذه الميثود تقوم بتغيير اللاعب
        if (cpt_4_change_gamer % 2 == 0) {
            Gamer = "o";
        } else {
            Gamer = "x";
        }
        cpt++;
    }// end change_Gamer.

    public void turn_of_Gamer() {
        // هذه الميثود تظهر لنا دور اللاعب الذي سيلعب
        if (cpt_4_change_gamer++ % 2 == 0) {
            jLabel_turn_of.setIcon(PLAYER_O_TURN);
            Gamer = "o";
        } else {
            jLabel_turn_of.setIcon(PLAYER_X_TURN);
            Gamer = "x";
        }
    }// end turn_of_Gamer.
/////////////////////// restForNewGameMax ////////////////////
    /**
     * this method is for make a new Game
     */
    public void restForNewGameMax() {
        // هذه الميثود تقوم بإنشاء لعة جديدة
        // This method is For Make New Game
        Show_Results(jLabelOwin_10, jLabelOwin_100, owin);
        Show_Results(jLabelXwin_10, jLabelXwin_100, xwin);
        Show_Results(jLabelDraw_10, jLabelDraw_100, draw);
        switch (NewGame.win) {
            case 1:
                Toast.makeText(this, (Game_Mode == 1 ? "   you win this round.  " : "player won this round."), (Game_Mode == 1 ? null : X_TOAST), Toast.Style.NORMAL).display();
                cpt_4_change_gamer = 1;
                break;
            case 2:
                Toast.makeText(this, (Game_Mode == 1 ? "      you lose.     " : "player won this round."), (Game_Mode == 1 ? null : O_TOAST), Toast.Style.NORMAL).display();
                cpt_4_change_gamer = 2;
                break;
            default:
                Toast.makeText(this, "   this game is draw   ", null, Toast.Style.NORMAL).display();
                cpt_4_change_gamer++;
                break;
        }
        restForNewGameMin(true);
        Sounds.play("sound_of_win");// sound of winning.
        /////// VS machin //////////////////////////////
        if (Game_Mode == 1 && Gamer.equals("o")) {
            new Thread() {
                @Override
                public void run() {
                    makeSleep(1800);
                    change_Gamer();
                    turn_of_Gamer();
                    playVsMachine();
                }
            }.start();
        }////////////////////////////////////////////////
    }// end restForNewGame
//////////////////////////// show result ///////////////////////////////

    /**
     * @param j1 is The Part 1 from 1000
     * @param j2 is The part 2 From 1000
     * @param val is The Value that show it.
     */
    public void Show_Results(JLabel j1, JLabel j2, short val) {
        // هذه الميثود تقوم بإظهار نتائج الجوالات السايقة
        // This method is showing The Score.
        byte s;
        if (val < 100) {
            s = (byte) (val % 10);
            getIconNumber(j1, s);
            if (val >= 10) {
                s = (byte) (val / 10);
                getIconNumber(j2, s);
            }// end if 2
        }// end if 1
    }// end ShowResult
    /**
     *
     * @param j is the label that we want to put a number icon in it.
     * @param s is the Number that we want to get an icon of it.
     */
    public void getIconNumber(JLabel j, byte s) {
        switch (s) {
            case 1:
                j.setIcon(NUMBER_1);
                break;
            case 2:
                j.setIcon(NUMBER_2);
                break;
            case 3:
                j.setIcon(NUMBER_3);
                break;
            case 4:
                j.setIcon(NUMBER_4);
                break;
            case 5:
                j.setIcon(NUMBER_5);
                break;
            case 6:
                j.setIcon(NUMBER_6);
                break;
            case 7:
                j.setIcon(NUMBER_7);
                break;
            case 8:
                j.setIcon(NUMBER_8);
                break;
            case 9:
                j.setIcon(NUMBER_9);
                break;
            default:
                j.setIcon(NUMBER_0);
                break;
        }// end switch         
    }// end getIconNumber
///////////////////////// play /////////////////////////////////
    /**
     * **********************
     * @param a is the position X of Player.
     * @param b is the position Y of Player.
     * @param bu is the Name of Button that click it.
     ***********************
     */
    public void play(int a, int b, JButton bu) {

        if ("".equals(NewGame.Matrix[a][b]) && play) {
            if (Game_Mode != 4 && Game_Mode != 3) {
                turn_of_Gamer();
                change_Gamer();
            }
            ////////////////// O Player 'con' /////////////////////////
            if (Gamer.equals("o") && NewGame.Matrix[a][b].equals("") && Game_Mode != 1 && Game_Mode != 3) {
                bu.setIcon(PLAYER_O);
                Sounds.play("soundc2");
                NewGame.Matrix[a][b] = "o";
                if (Game_Mode == 4) {
                    gamerOisDrawThis(String.valueOf(a) + String.valueOf(b));
                    cpt_4_change_gamer++;
                    cpt++;
                    if (cpt_4_change_gamer % 2 == 0) {
                        jLabel_turn_of.setIcon(PLAYER_O_TURN);
                        Gamer = "o";
                    } else {
                        jLabel_turn_of.setIcon(PLAYER_X_TURN);
                        Gamer = "x";
                    }
                }
            }
            ////////////////// X Player 'svr' /////////////////////////
            if (Gamer.equals("x") && NewGame.Matrix[a][b].equals("") && Game_Mode != 4) {
                bu.setIcon(PLAYER_X);
                Sounds.play("soundc1");
                NewGame.Matrix[a][b] = "x";
                if (Game_Mode == 3) {
                    gamerXisDrawThis(String.valueOf(a) + String.valueOf(b));
                    cpt_4_change_gamer++;
                    cpt++;
                    if (cpt_4_change_gamer % 2 == 0) {
                        jLabel_turn_of.setIcon(PLAYER_O_TURN);
                        Gamer = "o";
                    } else {
                        jLabel_turn_of.setIcon(PLAYER_X_TURN);
                        Gamer = "x";
                    }
                }
            }
            checkIfSomoanIsWin();
            ////////// part for Gaming VS Machine ///////////////
            if (Game_Mode == 1 && cpt != 9 && Gamer.equals("x") && cpt != 0) {
                turn_of_Gamer();
                change_Gamer();
                playVsMachine();
                checkIfSomoanIsWin();
            }/////////////////////////////////////////////////////
        }// end if        
    }//end play

    public void checkIfSomoanIsWin() {
        if (cpt >= 5) {
            NewGame.WinnerLook();
            if (NewGame.win == 1) {
                add_New_DB(++xwin, "xwin");
                drawCrossWin(NewGame.WinnerLook());
                play = false;// دور هذا المتغير المنطقي هو منع اللعب في الأوقات التي نريد
                restForNewGameMax();
            }
            if (NewGame.win == 2) {
                add_New_DB(++owin, "owin");
                drawCrossWin(NewGame.WinnerLook());
                play = false;
                restForNewGameMax();
            }
        }
        if (cpt == 9) {
            add_New_DB(++draw, "draw");
            play = false;
            restForNewGameMax();
        }
    }// end checkIfSomoanIsWin  
///////////////////////// drawCrossWin /////////////////////////////////
    /**
     * @param d is the position of colum or row that wining happing in.
     */
    public void drawCrossWin(String d) {

        if (d.equals("001122")) {
            jLabel_Cross_win.setBounds(0, 20, 340, 450);
            jLabel_Cross_win.setIcon(CROSS_BENT_LEFT);
        }
        if (d.equals("021120")) {
            jLabel_Cross_win.setBounds(0, 20, 340, 450);
            jLabel_Cross_win.setIcon(CROSS_BENT_RIGHT);
        }
        if (d.equals("101112")) {
            jLabel_Cross_win.setBounds(0, 20, 340, 470);
            jLabel_Cross_win.setIcon(CROSS_HORIZONTAL);
        }
        if (d.equals("000102")) {
            jLabel_Cross_win.setBounds(0, 20, 340, 240);
            jLabel_Cross_win.setIcon(CROSS_HORIZONTAL);
        }
        if (d.equals("202122")) {
            jLabel_Cross_win.setBounds(0, 310, 340, 130);
            jLabel_Cross_win.setIcon(CROSS_HORIZONTAL);
        }
        /////////////////////////////////////////////////
        if (d.equals("001020")) {
            jLabel_Cross_win.setBounds(63, 70, 280, 400);//x was 60
            jLabel_Cross_win.setIcon(CROSS_VERTICAL);
        }
        if (d.equals("011121")) {
            jLabel_Cross_win.setBounds(168, 70, 170, 400);// x was 170
            jLabel_Cross_win.setIcon(CROSS_VERTICAL);
        }
        if (d.equals("021222")) {
            jLabel_Cross_win.setBounds(273, 70, 70, 400);// x was 270
            jLabel_Cross_win.setIcon(CROSS_VERTICAL);
        }
    }// end drawCrossWin
///////////////////////// set_DB /////////////////////////////////    
    /**
     * @param s is the name of file who to save in.
     * @return
     */
    private String set_DB(String s) {
        String line = null;
        try {
            reader = new BufferedReader(new FileReader(s + ".txt"));
            line = reader.readLine();
        } catch (IOException ex) {
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                //System.out.println("Closing stream failed.");
            }
        }
        return (line == null ? "0" : line);
    }// end set_DB
///////////////////////// add_New_DB /////////////////////////////////
    /**
     *
     * @param x is the values which went to save it.
     * @param s is the name of file who to save in.
     */
    public void add_New_DB(short x, String s) {
        FileWriter br;
        try {
            br = new FileWriter(s + ".txt");
            br.write(String.valueOf(x));
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
///////////////////////// get_Button /////////////////////////////////

    public static JButton get_Button(int xm, int ym) {
        JButton j = null;
        if (xm == 0 && ym == 0) {
            j = jButton1;
        }
        if (xm == 0 && ym == 1) {
            j = jButton2;
        }
        if (xm == 0 && ym == 2) {
            j = jButton3;
        }
        if (xm == 1 && ym == 0) {
            j = jButton4;
        }
        if (xm == 1 && ym == 1) {
            j = jButton5;
        }
        if (xm == 1 && ym == 2) {
            j = jButton6;
        }
        if (xm == 2 && ym == 0) {
            j = jButton7;
        }
        if (xm == 2 && ym == 1) {
            j = jButton8;
        }
        if (xm == 2 && ym == 2) {
            j = jButton9;
        }
        return j;
    }// end get_Button.

///////////////////////// playVsMachine /////////////////////////////////    
    public void playVsMachine() {
        int xm, ym;
        do {
            xm = new Random().nextInt(3);// إن رقم ثلاثة لا يدخل في الإختيار العشوائي
            ym = new Random().nextInt(3);// إن رقم ثلاثة لا يدخل في الإختيار العشوائي
        } while (NewGame.Matrix[xm][ym].compareTo("") != 0);
        get_Button(xm, ym).setIcon(PLAYER_O);
        Sounds.play("soundc2");
        NewGame.Matrix[xm][ym] = "o";
    }// end playVsMachine.

    public void gamerXisDrawThis(String s) {
        try {
            output.writeUTF("xisplayed");// يتم إرسال هذا الأمر ليخبره أن يتلقى الأوامر
            output.writeUTF(s);
            output.flush();
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gamerOisDrawThis(String s) {
        try {
            output.writeUTF("oisplayed");// يتم إرسال هذا الأمر ليخبره أن يتلقى الأوامر
            output.writeUTF(s);
            output.flush();
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /////////////////////////// makeSleep //////////////////////////////////

    public static void makeSleep(int sp) {
        try {
            sleep(sp);
        } catch (InterruptedException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

///////////////////////// main /////////////////////////////////  
    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GamePanel().setVisible(true);
        });
        while (true) {
            //System.out.print("");
            makeSleep(1);
            if (Game_Mode == 3) {
                try {
                    if (serverIsOn == false) {
                        svr = new ServerSocket(5000);
                        // here shwp still equals ziro shwp=0 
                        con = svr.accept();
                        shwp=1;// it mains: hide the waiting panel.
                        input = new DataInputStream(con.getInputStream());
                        output = new DataOutputStream(con.getOutputStream());
                        output.writeInt(cpt_4_change_gamer);
                        output.flush();
                        serverIsOn = true;
                    }
                    //////////////////////////////
                    readData = input.readUTF();
                    if (readData.equals("oisplayed")) {                       
                        readData = input.readUTF();
                        get_Button(Integer.parseInt(readData.substring(0, 1)), Integer.parseInt(readData.substring(1, 2))).setIcon(jButtonPlas.getIcon());
                        NewGame.Matrix[Integer.parseInt(readData.substring(0, 1))][Integer.parseInt(readData.substring(1, 2))] = "o";
                        Sounds.play("soundc2");
                        cpt_4_change_gamer++;
                        cpt++;
                        if (cpt_4_change_gamer % 2 == 0) {
                            jLabel_turn_of.setIcon(jButtonPlas.getRolloverSelectedIcon());
                            Gamer = "o";
                        } else {
                            jLabel_turn_of.setIcon(jButtonPlas.getRolloverIcon());
                            Gamer = "x";
                        }
                        readData = null;
                        oIsPlay = 1;
                    }
                } catch (IOException ex) {
                    try {
                        //Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "sory your freind was exit.");
                        svr.close();
                        con.close();
                        input.close();
                        output.close();
                        break;
                    } catch (IOException ex1) {
                        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }// end if
        }// end while 2.
    }// end main.


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel Gamepanel;
    private javax.swing.JButton about_btn;
    private javax.swing.JButton back;
    private javax.swing.JButton backToMenu_btn1;
    private javax.swing.JButton exit_btn;
    private javax.swing.JButton host_btn;
    private static javax.swing.JButton jButton1;
    private static javax.swing.JButton jButton2;
    private static javax.swing.JButton jButton3;
    private static javax.swing.JButton jButton4;
    private static javax.swing.JButton jButton5;
    private static javax.swing.JButton jButton6;
    private static javax.swing.JButton jButton7;
    private static javax.swing.JButton jButton8;
    private static javax.swing.JButton jButton9;
    private static javax.swing.JButton jButtonPlas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelDevloped;
    private javax.swing.JLabel jLabelDraw_1;
    private javax.swing.JLabel jLabelDraw_10;
    private javax.swing.JLabel jLabelDraw_100;
    private javax.swing.JLabel jLabelDraw_1000;
    private javax.swing.JLabel jLabelMenu;
    private javax.swing.JLabel jLabelMenuBackground;
    private javax.swing.JLabel jLabelOwin_1;
    private javax.swing.JLabel jLabelOwin_10;
    private javax.swing.JLabel jLabelOwin_100;
    private javax.swing.JLabel jLabelOwin_1000;
    private javax.swing.JLabel jLabelXwin_1;
    private javax.swing.JLabel jLabelXwin_10;
    private javax.swing.JLabel jLabelXwin_100;
    private javax.swing.JLabel jLabelXwin_1000;
    private javax.swing.JLabel jLabel_Cross_win;
    private javax.swing.JLabel jLabel_Draw;
    private javax.swing.JLabel jLabel_Owin;
    private javax.swing.JLabel jLabel_Xwin;
    private javax.swing.JLabel jLabel_loading;
    private javax.swing.JLabel jLabel_logoIcon;
    private javax.swing.JLabel jLabel_logoText;
    private static javax.swing.JLabel jLabel_turn_of;
    private javax.swing.JTextField jTextField_Get_Ip;
    private javax.swing.JPanel menu;
    private javax.swing.JButton onePlayer_btn;
    private javax.swing.JButton play2x2_btn;
    private javax.swing.JButton samePanel_btn;
    private javax.swing.JButton setting_btn;
    private javax.swing.JPanel splashScreen;
    private javax.swing.JButton towPlayer_btn;
    private javax.swing.JLabel turn_Text;
    // End of variables declaration//GEN-END:variables

}// end class.
