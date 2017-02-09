/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ali
 */
public class FinalDA {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numberOfPolygon;
        int numberOfEnvironmentVertices;
        ArrayList<ArrayList<Position>> p;
        //ArrayList<ArrayList<Segment>> s;
        ArrayList<Polygon> poly;
        Scanner scan = new Scanner(System.in);
        System.out.println("numberOfEnvironmentVertices: ");
        numberOfEnvironmentVertices = scan.nextInt();
        System.out.println("numberOfPolygon: ");
        
        numberOfPolygon = scan.nextInt();
        numberOfPolygon+=2;
        int[] numberOfVertices = new int[numberOfPolygon];
        System.out.println("numberOfVertices of Polygon: ");
        numberOfVertices[0] = numberOfEnvironmentVertices;
        numberOfVertices[numberOfPolygon-1] = 2;
        for(int i = 1 ; i < numberOfPolygon-1; i++) {
            numberOfVertices[i] = scan.nextInt();
        }
        poly = new ArrayList<>();
        p = new ArrayList<>(numberOfPolygon);
        
        for(int i = 0; i < numberOfPolygon; i++) {
            
            p.add(new ArrayList<Position>());
        }
        
        //make polygon
        /*
        for(int i = 0; i < numberOfPolygon; i++) {
            int[] tx = new int[p.get(i).size()];
            int[] ty = new int[p.get(i).size()];
            for(int j = 0; j < p.get(i).size(); j++) {
                tx[j] = p.get(i).get(j).x;
                ty[j] = p.get(i).get(j).y;
            }
            Polygon tmp = new Polygon(tx, ty, p.get(i).size());
            poly.add(tmp);
        }
        */
        
        
        
        Frame jf = new Frame(p, poly, numberOfVertices, numberOfPolygon, numberOfEnvironmentVertices);
        //MakeGraph mgraph = new MakeGraph(poly, p, numberOfPolygon, numberOfEnvironmentVertices);
        //mgraph.dijkstra();
               
   }
   
}
