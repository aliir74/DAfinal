/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Ali
 */
public class Frame extends JFrame{
    ArrayList<ArrayList<Position>> p;
    ArrayList<Polygon> poly;
    int[] numberOfVertices;
    int numberOfPolygon, numberOfEnvironmentVertices;
    Panel jp;
    public Frame(ArrayList<ArrayList<Position>> p, ArrayList<Polygon> poly, int[] numberOfVertices, int numberOfPolygon, int numberOfEnvironmentVertices) throws HeadlessException {
        //setLayout(null);
        this.p = p;
        this.poly = poly;
        this.numberOfVertices = numberOfVertices;
        this.numberOfPolygon = numberOfPolygon;
        this.numberOfEnvironmentVertices = numberOfEnvironmentVertices;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //Dimension dim2 = dim;
        //dim2.width = dim.width/2;
        //dim2.height = dim.height/2;
        setSize(dim);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jp  = new Panel(p, poly, numberOfVertices, numberOfPolygon, numberOfEnvironmentVertices);
        add(jp);
        //pack();
        setEnabled(true);
        setVisible(true);
    }
    
}
