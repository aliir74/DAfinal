/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import jdk.internal.org.objectweb.asm.Handle;

/**
 *
 * @author Ali
 */
public class Panel extends JPanel{
    HandlerMouse handler = new HandlerMouse();

    ArrayList<Polygon> poly;
    ArrayList<ArrayList<Position>> p;
    int numberOfPolygon, numberOfEnvironmentVertices;
    MakeGraph mgraph;
    double[][] V;
    boolean graphic11, lineShow, dij;
    
    int[] numberOfVertices;
    Graphics g;

    public Panel(ArrayList<ArrayList<Position>> p, ArrayList<Polygon> poly, int[] numberOfVertices, int numberOfPolygon, int numberOfEnvironmentVertices) {
        setLayout(null);
        this.p = p;
        this.poly = poly;
        this.numberOfVertices = numberOfVertices;
        this.numberOfEnvironmentVertices = numberOfEnvironmentVertices;
        this.numberOfPolygon = numberOfPolygon;
        graphic11 = false;
        setVisible(true);
        lineShow = false;
        dij = false;
        addMouseListener(handler);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(graphic11) {
            System.out.println("hhhhh");
//            Polygon tmp = new Polygon();
            g.setColor(Color.DARK_GRAY);
            
            //g.drawPolyline(poly.get(0).xpoints, poly.get(0).ypoints, poly.get(0).npoints);
            g.drawPolygon(poly.get(0));
            //g.drawLine(poly.get(0).xpoints[0], poly.get(0).ypoints[0], poly.get(0).xpoints[1], poly.get(0).ypoints[1]);
            //System.out.println(poly.get(0).xpoints[0]+ "");
            //System.out.println(poly.get(0).npoints + "");
            //System.out.println(poly.size() + "");
//            for(Polygon qq: poly) {
//                g.fillPolygon(qq);
//            }
            
            for(int i = 1; i < poly.size(); i++) {
                g.fillPolygon(poly.get(i));
                //System.out.println("" + poly.get(i).npoints);
            
            }
        }
        if(lineShow) {
            int[] o1 = new int[2];
            int[] o2 = new int[2];
            for(int i = 0; i < V.length; i++) {
                for(int j = i+1 ; j < V.length; j++) {
                    if(V[i][j] > 0) {
                        o1 = mgraph.getFromV(i);
                        o2 = mgraph.getFromV(j);
                        g.setColor(Color.GRAY);
                        g.drawLine(o1[0], o1[1], o2[0], o2[1]);
                    }
                }
            }
        }
        
        int k = 1;
//        Polygon tmp = new Polygon();
//            tmp.addPoint(100, 100);
//            tmp.addPoint(200, 300);
//            tmp.addPoint(400, 600);
//            g.setColor(Color.BLACK);
//            g.drawPolygon(tmp);
        
        if(numberOfVertices[numberOfPolygon-1] <= 2) {
            for(int i = 0; i < numberOfPolygon-1; i++) {
                for(int j = 0 ;j < p.get(i).size(); j++) {
                    int x = p.get(i).get(j).x - (int) (Math.floor(10 / 2));
                    int y = p.get(i).get(j).y - (int) (Math.floor(10 / 2));
                    g.setColor(Color.BLACK);
                    g.fillOval(x, y, 10, 10);
                    String s = Integer.toString(p.get(i).get(j).x);
                    
                    s = s + " " + Integer.toString(p.get(i).get(j).y);
                    g.drawString(k+" " + s, x, y);
                    k++;
                }
                
            }
        }
        if(numberOfVertices[numberOfPolygon-1] < 2) {
            
            for(int i = 0; i < p.get(numberOfPolygon-1).size(); i++) {
                int x = p.get(numberOfPolygon-1).get(i).x - (int) (Math.floor(13 / 2));
                int y = p.get(numberOfPolygon-1).get(i).y - (int) (Math.floor(13 / 2));
                g.setColor(Color.PINK);
                g.fillOval(x, y, 10, 10);
                //String s = Integer.toString(i+1);
                String s = Integer.toString(p.get(numberOfPolygon-1).get(i).x);
                s = s + " " + Integer.toString(p.get(numberOfPolygon-1).get(i).y);
                g.drawString((i+1)+" "+s, x, y);
            }
        }
        
        if(dij) {
            g.setColor(Color.GREEN);
            for(int i = 0; i < mgraph.ansPosition.length-1; i++) {
                g.drawLine(mgraph.ansPosition[i].x, mgraph.ansPosition[i].y, mgraph.ansPosition[i+1].x, mgraph.ansPosition[i+1].y);
            }
        }
        
    }
    @Override
            public Dimension getPreferredSize() {
                return new Dimension(800, 600);
            }
    private void makePolygon() {
        System.out.println("hellooooo");
        for(int i = 0; i < p.size()-1; i++) {
            int[] x = new int[p.get(i).size()];
            int[] y = new int[p.get(i).size()];
            for(int j = 0; j < p.get(i).size(); j++) {
                x[j] = p.get(i).get(j).x;
                y[j] = p.get(i).get(j).y;
            }
            poly.add(new Polygon(x, y, x.length));
        //    System.out.println(x[0] + " " + x[1] + " " + x[2] + "");
        }
        //draw polygon
        graphic11 = true;
        showPositions();
        repaint();
        checkPoly();
        mgraph = new MakeGraph(poly, p, numberOfPolygon, numberOfEnvironmentVertices);
        mgraph.dijkstra();
        V = mgraph.getV();
        lineShow = true;
        repaint();
        dij = true;
    }
    
    private void checkPoly() {
        for(int i = 0; i < poly.size(); i++) {
            //System.out.println(poly.get(i).xpoints + "");
        }
    }
    private void showPositions() {
        for(int i = 0; i < p.size(); i++) {
            for(int j = 0; j < p.get(i).size(); j++) {
                System.out.println("x: " + p.get(i).get(j).x + " y: " + p.get(i).get(j).y);
            }
            
            System.out.println("");
        }
    }
    private void addPoint(int x, int y) {
        int i = 0;
        //System.out.println(y + "");
        while(i < numberOfPolygon && numberOfVertices[i] == 0) {
            i++;
        }
        if(i == numberOfPolygon) {
            System.err.println("Don't Enter Point!");
        } else {
            numberOfVertices[i]--;
            Position tmp = new Position();
            tmp.x = x;
            tmp.y = y;
            p.get(i).add(tmp);
            
            System.out.println(p.get(i).get(0).y + "");
            if(p.get(i).size() > 1) {
                //create line!
               
            }
            if(numberOfVertices[i] == 0) {
                //Fill into polygon!
            }
            if(i == numberOfPolygon-1 && numberOfVertices[i] == 0) {
                makePolygon();
            }
        }
        repaint();
    }
    
     private class HandlerMouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 1) {
                addPoint(e.getX(), e.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    
    
    
}
