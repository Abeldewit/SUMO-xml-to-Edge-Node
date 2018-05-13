import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MapReader {
    public static void main(String[] args){
        MapReader instance = new MapReader();
        instance.readNodes();
        instance.readEdges();

    }

    public ArrayList<Node> readNodes() {
        //Choosing the Node xml file

        ArrayList<Node> nodeList = new ArrayList<>();

        String xmlFile = null;
        JFileChooser chooser = new JFileChooser("C:\\Users\\Abel\\Documents\\School\\Year 2\\Project 2-2\\");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Node XML file", "xml");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            xmlFile = chooser.getSelectedFile().getAbsolutePath();
            System.out.println("Reading file : " + xmlFile);

            try {
                Scanner sc = new Scanner(new File(xmlFile));

                while(sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if(line.startsWith("    <node")) {
                        String[] properties = line.split("\"");
                        String id = properties[1];
                        double x = Double.parseDouble(properties[3]);
                        double y = Double.parseDouble(properties[5]);
                        String type = properties[7];
                        
                        nodeList.add(new Node(id,x,y,type));
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return nodeList;
    }

    public ArrayList<Edge> readEdges() {
        ArrayList<Edge> edgeList = new ArrayList<>();

        String xmlFile = null;
        JFileChooser chooser = new JFileChooser("C:\\Users\\Abel\\Documents\\School\\Year 2\\Project 2-2\\");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Edge XML file", "xml");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            xmlFile = chooser.getSelectedFile().getAbsolutePath();
            System.out.println("Reading file : " + xmlFile);

            try {
                Scanner sc = new Scanner(new File(xmlFile));

                while(sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if(line.startsWith("    <edge")) {
                        String[] properties = line.split("\"");
                        String id = properties[1];
                        String from = properties[3];
                        String to = properties[5];
                        int priority = Integer.parseInt(properties[7]);
                        String type = properties[9];
                        double speed = Double.parseDouble(properties[13]);

                        edgeList.add(new Edge(speed,type,priority,to,from,id));
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return edgeList;
    }
}
