/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Ali
 */
public class MakeGraph {
   ArrayList<ArrayList<Position>> p;
    ArrayList<ArrayList<Segment>> s;
    ArrayList<Polygon> poly;
    int count;
    double[][] V;
    double[] ans;
    int[] ansVertices;
    Position[] ansPosition;
    int[] w;
    
    int numberOfPolygon;
    int numberOfEnvironmentVertices;

    public MakeGraph(ArrayList<Polygon> poly, ArrayList<ArrayList<Position>> p, int numberOfPolygon, int numberOfEnvironmentVertices) {
        this.p = p;
        this.poly = poly;
        s = new ArrayList<ArrayList<Segment>>(numberOfPolygon-1);
    //    this.s = s;
        this.numberOfPolygon = numberOfPolygon;
        this.numberOfEnvironmentVertices = numberOfEnvironmentVertices;
        for(int i = 0; i < numberOfPolygon-1; i++) {
            
            s.add(new ArrayList<Segment>());
        }
        
        makeSegments();
        makeV();
        
        
    }
    
    public double[][] getV() {
        return V;
    }

    public void dijkstra() {
        boolean[] visited = new boolean[count];
        int andis;
        for(int i = 0; i < count; i++) {
            w[i] = -1;
            ans[i] = V[i][count-2];
            visited[i] = false;
        }
        visited[count-2] = true;
        double min;
        for(int i = 0; i < count; i++) {
            int j = 0;
            andis = -1;
            while(j < count && (visited[j] == true || ans[j] == -1)) {
                j++;
            }
            
            andis = j;
            if(andis >= count) {
                break;
            }
            min = ans[j];
            for(int o = j+1; o < count; o++) {
                if(ans[o] < min && visited[o] == false && ans[o] != -1) {
                    min = ans[o];
                    andis = o;
                }
            }
            visited[andis] = true;
            double distance;
            for(int o = 0; o < count; o++) {
                if(visited[o] == false) {
                    if(V[andis][o] > 0 && ans[andis] > 0) {
                        distance = ans[andis] + V[andis][o];
                        if(distance < ans[o] || ans[o] == -1) {
                            ans[o] = distance;
                            w[o] = andis;
                        }
                    }
                    
                }
            }
        }
        int countPath = 2;
        int tmp = count-1;
        System.out.println("w : " + w[tmp]);
        while(w[tmp] != -1) {
            tmp = w[tmp];
            countPath++;
        }
        System.out.println("distance : " + V[count-1][count-2]);
        System.out.println("coutn path: " + countPath);
        ansVertices = new int[countPath];
        ansVertices[countPath-1] = count-1;
        ansVertices[0] = count-2;
        tmp = count-1;
        for(int p = countPath-2; p > 0; p--) {
            ansVertices[p] = w[tmp];
            tmp = w[tmp];
        }
        ansPosition = new Position[countPath];
        for(int z = 0; z < countPath; z++) {
            ansPosition[z] = new Position();
        }
        //System.out.println(countPath + "");
        for(int j = 0; j < countPath; j++) {
            double xi = 0,yi = 0;
            int countc = 0;
            int i = ansVertices[j];
            i++;
            while(i != 0) {
                if(i > p.get(countc).size()) {
                    i -= p.get(countc).size();
                    countc++;
                } else {
                    xi = p.get(countc).get(i-1).x;
                    yi = p.get(countc).get(i-1).y;
                    break;
                }
            }
            //System.out.println(j + "");
            //System.out.println(ansPosition[0] +"");
            ansPosition[j].x = (int) xi;
            ansPosition[j].y = (int) yi;
        }
        for(int i = 0; i < ansVertices.length; i++) {
            System.out.print(ansVertices[i] + " ");
        }
        System.out.println("");
        
        for(int i = 0; i < w.length; i++) {
            System.out.print(w[i] + " ");
        }
        System.out.println("");
    }
    
    private void makeV(){
        //int count = 0;
        count = 0;
        for(int i = 0; i < numberOfPolygon; i++) {
            count += p.get(i).size();
        }
        V = new double[count][count];
        ans = new double[count];
        w = new int[count];
        for(int i = 0; i < count; i ++) {
            V[i][i] = -1;
            for(int j = i+1; j < count; j++) {
                V[i][j] = getDistance(i, j);
                if(checkIntersection(i, j)) {
                    //System.out.println((i+1) + " " + (j+1));
                    V[i][j] = -1;
                }
                V[j][i] = V[i][j];
                //System.out.println(V[i][j] + "");
            }
          
        }
        int[] o1 = new int[2];
        int[] o2 = new int[2];
        for(int i = 0; i < p.get(0).size(); i++) {
            for(int j = 0; j < p.get(0).size(); j++) {
                o1 = getFromV(i);
                o2 = getFromV(j);
                int x = (o1[0] + o2[0])/2;
                int y = (o1[1] + o2[1])/2;
                if(!poly.get(0).contains(x, y)) {
                    V[i][j] = -1;
                    V[j][i] = -1;
                }
            }
        }
           
        
    }
    public int[] getFromV(int i) {
        int xi=0, yi=0;
        int countc = 0;
        i++;
        while(i != 0) {
            if(i > p.get(countc).size()) {
                i -= p.get(countc).size();
                countc++;
            } else {
                xi = p.get(countc).get(i-1).x;
                yi = p.get(countc).get(i-1).y;
                break;
            }
        }
        int[] k = new int[2];
        k[0] = xi;
        k[1] = yi;
        return k;
    }
    private boolean checkIntersection(int i, int j) {
        double xi = 0,yi = 0, xj = 0, yj = 0;
        System.out.println(" " + (i+1) + " " + (j+1));
        int[] o1 = getFromV(i);
        int[] o2 = getFromV(j);
        xi = o1[0];
        yi = o1[1];
        xj = o2[0];
        yj = o2[1];
//        int countc = 0;
//        i++;
//        j++;
//        while(i != 0) {
//            if(i > p.get(countc).size()) {
//                i -= p.get(countc).size();
//                countc++;
//            } else {
//                xi = p.get(countc).get(i-1).x;
//                yi = p.get(countc).get(i-1).y;
//                break;
//            }
//        }
//        countc = 0;
//        while(j != 0) {
//            if(j > p.get(countc).size()) {
//                j -= p.get(countc).size();
//                countc++;
//            } else {
//                xj = p.get(countc).get(j-1).x;
//                yj = p.get(countc).get(j-1).y;
//                break;
//            }
//        }
        for(int o = 0; o < s.size(); o++) {
            for(int l = 0; l < s.get(o).size(); l++) {
                int tt = getAndisOfV(o, l);
                int tt1;
                if(l < s.get(o).size()-1){
                    tt1 = tt + 1;
                } else {
                    tt1 = (tt + 1) - s.get(o).size();
                }
                    if(i != tt && i != tt1 && j != tt && j != tt1 &&  s.get(o).get(l).findIntersection((int)xi, (int)yi, (int)xj, (int)yj)) {
                        System.out.println((o+1) + " " + (l+1) + " 1");
                        System.out.println("i: " + (i+1) + " j: " + (j+1));
                        System.out.println("tt: " + (tt+1) + " tt1:" + (tt1+1));
                        return true;
                    }
                
            }
        }
        double tmpx = (xi + xj)/2, tmpy = (yi + yj)/2;
        for(int o = 1; o < poly.size(); o++) {
            if(poly.get(o).contains((int)tmpx, (int)tmpy)) {
                System.out.println(" 2");
                return true;
            }
        }
        System.out.println(" 3");
        return false;
    }
    
    private int getAndisOfV(int row, int col) {
        int r = 0;
        for(int i = 0; i < row; i++) {
            r += p.get(i).size();
        }
        r += col;
        return r;
    }
    
    private double getDistance(int i, int j) {
        double xi = 0,yi = 0, xj = 0, yj = 0;
        int[] o1 = getFromV(i);
        int[] o2 = getFromV(j);
        xi = o1[0];
        yi = o1[1];
        xj = o2[0];
        yj = o2[1];
//        int countc = 0;
//        i++;
//        j++;
//        while(i != 0) {
//            if(i > p.get(countc).size()) {
//                i -= p.get(countc).size();
//            } else {
//                xi = p.get(countc).get(i-1).x;
//                yi = p.get(countc).get(i-1).y;
//                break;
//            }
//        }
//        countc = 0;
//        while(j != 0) {
//            if(j > p.get(countc).size()) {
//                j -= p.get(countc).size();
//            } else {
//                xj = p.get(countc).get(j-1).x;
//                yj = p.get(countc).get(j-1).y;
//                break;
//            }
//        }
        return Math.sqrt(Math.pow(xi - xj, 2)+Math.pow(yi - yj, 2));
    
    }
    
    private void makeSegments() {
        for(int i = 0; i < numberOfPolygon-1; i++) {
            for(int j = 0; j < p.get(i).size(); j++) {
                
                s.get(i).add(new Segment(p.get(i).get(j).x, p.get(i).get(j).y, p.get(i).get((j+1)%p.get(i).size()).x, p.get(i).get((j+1)%p.get(i).size()).y));
            }
        }
    }
    
}
