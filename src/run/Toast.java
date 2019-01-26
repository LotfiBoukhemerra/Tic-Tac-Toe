package run;

/**
 * this class is copied and modified from Greg Neiheisel repository.
 * Greg Neiheisel repository: https://github.com/schnie
 * original class (code Source): https://github.com/schnie/android-toasts-for-swing/blob/master/Toast.java
 */
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Toast extends JDialog {

    private static final long serialVersionUID = -1602907470843951525L;
    private static Toast makeText(JFrame owner, String text, ImageIcon i, int LENGTH_SHORT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Toast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public enum Style {

        NORMAL, SUCCESS, ERROR
    };

    public static final int LENGTH_SHORT = 2000;//3000
    public static final int LENGTH_LONG = 6000;
    public static final Color ERROR_RED = new Color(121, 0, 0);
    public static final Color SUCCESS_GREEN = new Color(22, 127, 57);
    public static final Color NORMAL_BLACK = new Color(50, 50, 50);//new Color(0, 0, 0);

    private final float MAX_OPACITY = 1f;//0.8f;
    private final float OPACITY_INCREMENT = 0.05f;
    private final int FADE_REFRESH_RATE = 20;
    private final int WINDOW_RADIUS = 15;
    private final int CHARACTER_LENGTH_MULTIPLIER = 10;
    private final int DISTANCE_FROM_PARENT_TOP = 280;// الأصلي كان 100

    private JFrame mOwner;
    private String mText;
    private int mDuration;
    private ImageIcon img;
    private Color mBackgroundColor = Color.BLACK;
    private Color mForegroundColor = Color.WHITE;

    public Toast(JFrame owner) {
        super(owner);
        mOwner = owner;
    }

    private void createGUI() {
        setLayout(new GridBagLayout());
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), WINDOW_RADIUS, WINDOW_RADIUS));
            }
        });

        setAlwaysOnTop(true);
        setUndecorated(true);
        setFocusableWindowState(false);
        setModalityType(ModalityType.MODELESS);
        setSize(mText.length() * CHARACTER_LENGTH_MULTIPLIER, 45);
        getContentPane().setBackground(mBackgroundColor);

        JLabel label = new JLabel(mText);
        JLabel label2 = new JLabel(" ");
        label2.setIcon(img);
        label.setForeground(mForegroundColor);
        label.setFont(new java.awt.Font("Tahoma", 1, 15));
//        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(label2);
        add(label);
    }

    public void fadeIn() {
        final Timer timer = new Timer(FADE_REFRESH_RATE, null);
        timer.setRepeats(true);
        timer.addActionListener(new ActionListener() {
            private float opacity = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += OPACITY_INCREMENT;
                setOpacity(Math.min(opacity, MAX_OPACITY));
                if (opacity >= MAX_OPACITY) {
                    timer.stop();
                }
            }
        });

        setOpacity(0);
        timer.start();

        setLocation(getToastLocation());
        setVisible(true);
    }

    public void fadeOut() {
        final Timer timer = new Timer(FADE_REFRESH_RATE, null);
        timer.setRepeats(true);
        timer.addActionListener(new ActionListener() {
            private float opacity = MAX_OPACITY;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= OPACITY_INCREMENT;
                setOpacity(Math.max(opacity, 0));
                if (opacity <= 0) {
                    timer.stop();
                    setVisible(false);
                    dispose();
                }
            }
        });

        setOpacity(MAX_OPACITY);
        timer.start();
    }

    private Point getToastLocation() {
        Point ownerLoc = mOwner.getLocation();
        int x = (int) (ownerLoc.getX() + ((mOwner.getWidth() - this.getWidth()) / 2));
        //int y = (int) (ownerLoc.getY() + DISTANCE_FROM_PARENT_TOP);//الأصلي
        int y = (int) (ownerLoc.getY() + ((mOwner.getHeight() - this.getHeight()) / 2));
        return new Point(x, y);
    }

    public void setText(String text) {
        mText = text;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    @Override
    public void setBackground(Color backgroundColor) {
        mBackgroundColor = backgroundColor;
    }

    @Override
    public void setForeground(Color foregroundColor) {
        mForegroundColor = foregroundColor;
    }

    public static Toast makeText(JFrame owner, String text, ImageIcon i) {
        return makeText(owner, text, i, LENGTH_SHORT);
    }

    public static Toast makeText(JFrame owner, String text, ImageIcon i, Style style) {
        return makeText(owner, text, i, LENGTH_SHORT, style);
    }

    public static Toast makeText(JFrame owner, String text, ImageIcon i, Style style, int duration) {
        return makeText(owner, text, i, duration, Style.NORMAL);
    }

    public static Toast makeText(JFrame owner, String text, ImageIcon i, int duration, Style style) {
        Toast toast = new Toast(owner);
        toast.mText = text;
        toast.mDuration = duration;
        toast.img = i;
        if (style == Style.SUCCESS) {
            toast.mBackgroundColor = SUCCESS_GREEN;
        }
        if (style == Style.ERROR) {
            toast.mBackgroundColor = ERROR_RED;
        }
        if (style == Style.NORMAL) {
            toast.mBackgroundColor = NORMAL_BLACK;
        }

        return toast;
    }

    public void display() {
        new Thread(() -> {
            try {
                createGUI();
                fadeIn();
                Thread.sleep(mDuration);
                fadeOut();
            } catch (InterruptedException ex) {
            }
        }).start();
    }

}
