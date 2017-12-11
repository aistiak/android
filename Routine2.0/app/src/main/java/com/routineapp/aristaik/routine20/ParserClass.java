package com.routineapp.aristaik.routine20;

/**
 * Created by AR Istaik on 11/16/2017.
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by AR Istaik on 11/15/2017.
 */


public class ParserClass {
    public String FileName;

    public ParserClass(String FileName) {
        this.FileName = FileName;
    }

    public ArrayList[] getData(String Dept, String Semester) {


        // TODO code application logic here

        ArrayList[] listArray = new ArrayList[5];

        int key = 0, flag = 1;

        try {
            File inputFile = new File(FileName);

            InputStream is = new ByteArrayInputStream(FileName.getBytes("UTF-8"));

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();
            // Log.d("arif","document parsed");
            Element root = doc.getDocumentElement();
            NodeList nl = root.getChildNodes();


            for (int i = 0; i < nl.getLength(); i++) {

                Node n = nl.item(i);

                if (n.getNodeName().equals(Dept)) { // department

                    NodeList nodeList = n.getChildNodes();

                    for (int j = 0; j < nodeList.getLength(); j++) {
                        Node tn = nodeList.item(j);
                        if (tn.getNodeType() == Node.ELEMENT_NODE) {

                            Element e = (Element) tn;
                            if (Semester.equals(e.getAttribute("id"))) { // SEMESTER
                                NodeList nodeList_1 = (NodeList) e.getChildNodes(); // days list

                                for (int k = 0; k < nodeList_1.getLength(); k++) {

                                    n = nodeList_1.item(k); // DAY
                                    if (n.getNodeType() == Node.ELEMENT_NODE) { // PERIOD
                                        //
                                        e = (Element) n;


                                        NodeList nodeList2 = e.getChildNodes();//period list

                                        ArrayList<Period> list = new ArrayList();
                                        for (int p = 0; p < nodeList2.getLength(); p++) {
                                            n = nodeList2.item(p);

                                            Period temp = new Period();

                                            if (n.getNodeType() == Node.ELEMENT_NODE) {
                                                flag = 1;
                                                Element tele = (Element) n;

                                                temp.setStart_time(tele.getElementsByTagName("start_time").item(0).getTextContent());
                                                temp.setEnd_time(tele.getElementsByTagName("end_time").item(0).getTextContent());
                                                temp.setCourse_code(tele.getElementsByTagName("course_code").item(0).getTextContent());
                                                temp.setSub_name(tele.getElementsByTagName("course_title").item(0).getTextContent());
                                                temp.setTeacher_name(tele.getElementsByTagName("course_teacher").item(0).getTextContent());

                                                list.add(temp);


                                            }


                                        }


                                        if (flag == 1) {
                                            listArray[key] = list;
                                            if (key == 4) {
                                                return listArray;
                                            }
                                            key++;
                                            flag = 0;

                                        }

                                    }
                                }

                                return listArray;
                            }


                        }

                    }


                }
            }
            System.out.println(root);

        } catch (Exception e) {


            e.printStackTrace();
        }


        return null;

    }

}
