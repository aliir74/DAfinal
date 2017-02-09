/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Ali
 */
public class Segment {
    int maxx;
    int maxy;
    int minx;
    int miny;
    double m;
    double intercept;

    public Segment(int x1, int y1, int x2, int y2) {
        minx = x1;
        maxx = x2;
        if(maxx < minx) {
            minx = x2;
            maxx = x1;
        }
        miny = y1;
        maxy = y2;
        if(maxy < miny) {
            miny = y2;
            maxy = y1;
        }
        m = (double)(y2 - y1) / (double)(x2 - x1);
        intercept = (y1 - m*x1);
    }
    
    public boolean findIntersection(int x11, int y11, int x21, int y21) {
        int minxx = x11;
        int maxxx = x21;
        if(maxxx < minxx) {
            minxx = x21;
            maxxx = x11;
        }
        int minyy = y11;
        int maxyy = y21;
        if(maxyy < minyy) {
            minyy = y21;
            maxyy = y11;
        }
        double m2 = (double)(y21 - y11) / (double)(x21 - x11);
        double i2 = (y11 - m2*x11);
        if(m2 == m) {
            return false;
        }
        double x = ((i2 - intercept) / (m - m2));
        double y = (m*x + intercept);
        if(x >= minx && x <= maxx && y >= miny && y <= maxy && x <= maxxx && x >= minxx && y <= maxyy && y >= minyy) {
            //System.out.println("maxx :" + maxx + " maxy: " + maxy + " minx: " + minx + " miny: " + miny);
            System.out.println("Intersection Point: " + x + " " + y);
            System.out.println("1'st Line: m=" + m + " intercept=" + intercept);
            System.out.println("2'nd Line: m2=" + m2+ " intercept2=" + i2);
            return true;
        } else {
            return false;
        }
    }
    
    
}
