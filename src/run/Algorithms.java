/*
 CopyRight © 2018 LOTFI BOUKHEMERRA - All Rights Reserved - developed by LOTFI BOUKHEMERRA

 */
package run;

/**
 *
 * @author BOUKHEMERRA Lotfi.
 * repository https://github.com/LotfiBoukhemerra
 */
public class Algorithms {

    private static final String X = "xxx", O = "ooo";
    String TestWin, drawCross;
    public short win;
    private boolean Exit = false;
    public String[][] Matrix = {{"", "", ""}, {"", "", ""}, {"", "", ""}};
    private Point[] MatrixPoint;

/////////////////////// Who_Win /////////////////////////////////////////
    boolean Who_Win(String TestWin) {
        boolean s = false;
        if (TestWin.compareTo(X) == 0) {
            win = 1;
            s = true;
        }
        if (TestWin.compareTo(O) == 0) {
            win = 2;
            s = true;
        }
        return (s);
    }// end who win

//////////////// WinnerLook //////////////////////////////////////////
    String WinnerLook() {
        Exit = Who_Win(TestWin = Matrix[0][0] + Matrix[1][1] + Matrix[2][2]);
        drawCross = "001122";
        if (Exit == false) {
            Exit = Who_Win(TestWin = Matrix[0][2] + Matrix[1][1] + Matrix[2][0]);
            drawCross = "021120";
            byte i = 0;
            while (Exit == false && i < Matrix.length) {
                Exit = Who_Win(TestWin = Matrix[i][0] + Matrix[i][1] + Matrix[i][2]);
                if (Exit == true) {
                    drawCross = i + "0" + i + "1" + i + "2";
                    break;
                }
                Exit = Who_Win(TestWin = Matrix[0][i] + Matrix[1][i] + Matrix[2][i]);
                drawCross = "0" + i + "1" + i + "2" + i;
                i++;
            }// end while 
        }// end if 1
        return drawCross;
    }// end WinnerLook

///////////////// TestCaseBlank /////////////////////////////////////////////
    boolean TestCaseBlank(byte x, byte y) {
        boolean can = false;
        byte cpt = 0;
        MatrixPoint = new Point[4];
        if (x == y) {
            if (x != 1) {
                if ("".equals(Matrix[x][1])) {
                    MatrixPoint[cpt] = new Point(x, (byte)1);
                    cpt++;
                    can = true;
                }
                if ("".equals(Matrix[1][x])) {
                    MatrixPoint[cpt] = new Point((byte)1, x);
                    cpt++;
                    can = true;
                }
            } else {
                for (byte i = 0; i < (byte)3; i += (byte)2) {
                    if ("".equals(Matrix[i][x])) {
                        MatrixPoint[cpt] = new Point(i, x);
                        cpt++;
                        can = true;
                    }
                    if ("".equals(Matrix[x][i])) {
                        MatrixPoint[cpt] = new Point(x, i);
                        cpt++;
                        can = true;
                    }
                }// end for
            }// end else
        }// end if 1

        if (x + y == 2 && x != 1) {
            if ("".equals(Matrix[x][1])) {
                MatrixPoint[cpt] = new Point(x, (byte)1);
                cpt++;
                can = true;
            }
            if ("".equals(Matrix[1][y])) {
                MatrixPoint[cpt] = new Point((byte)1, y);
                cpt++;
                can = true;
            }
        }// end if 2

        if (x + y == 3 || x + y == 1) {
            byte a = 3;
            if (x + y == 1) {
                a = 2;
            }
            for (byte i = (byte) (a - 2); i < a; i++) {
                if ("".equals(Matrix[i][i])) {
                    MatrixPoint[cpt] = new Point(i, i);
                    cpt++;
                    can = true;
                }
            }// end for

            if (x == a - 2) {
                if ("".equals(Matrix[0][2])) {
                    MatrixPoint[cpt] = new Point((byte)0, (byte)2);
                    cpt++;
                    can = true;
                }
            }
            if (x == a - 1) {
                if ("".equals(Matrix[2][0])) {
                    MatrixPoint[cpt] = new Point((byte)2,(byte)0);
                    cpt++;
                    can = true;
                }
            }
        }
        // end if 3
        return (can);
    }// TestCaseBlank

    /////////////////// show_Matrix_Consol //////////////////////////
    void show_Matrix_Consol() {
        System.out.printf("%3d %1d %1d\n", 0, 1, 2);
        System.out.printf(" +-+-+-+\n");
        for (int i = 0; i < Matrix.length; i++) {
            System.out.printf("%d|%1s|%1s|%1s| \n", i, Matrix[i][0], Matrix[i][1], Matrix[i][2]);
            System.out.printf(" +-+-+-+\n");
        }
    }//show_Matrix_Consol
}// end Algorithms
