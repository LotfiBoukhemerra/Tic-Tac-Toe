/*
 CopyRight © 2018 LOTFI BOUKHEMERRA - All Rights Reserved - developed by LOTFI BOUKHEMERRA
 */
package run;
/**
 *
 * @author BOUKHEMERRA Lotfi.
 * repository https://github.com/LotfiBoukhemerra
 */
public class Point {

    byte x;
    byte y;

    Point(byte x, byte y) {
        this.x = x;
        this.y = y;
    }// end constructor

    /**
     * @return Point Class Of a String
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

}// end PointMin
