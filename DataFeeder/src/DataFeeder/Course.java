/**
 * @author yuwang
 * 
 * 
 *
 */

package DataFeeder;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Course {
	private String dbPath;
	private String user;
	private String password;

	public Course(String dbPath, String user, String password) {
		super();
		this.dbPath = dbPath;
		this.user = user;
		this.password = password;
	}

	public void createCourse() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE COURSE "
					+ "(NUM INT, ID TEXT, ISFUNDA INT, ISOPEN INT, NAME TEXT, PROF TEXT, TA TEXT, DEMAND INT, PRESETSEAT INT)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Course General Table created successfully");

	}

	public void insertCourses(String filepath) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(new File(filepath));

		HashMap<String, ArrayList<String>> hm = new HashMap<>();

		NodeList nodeList = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {

			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) node;

				// Get the value of all sub-elements.

				String id = elem.getElementsByTagName("id")

						.item(0).getChildNodes().item(0).getNodeValue();

				String isfunda = elem.getElementsByTagName("isfunda").item(0)

						.getChildNodes().item(0).getNodeValue();
				String isopen = elem.getElementsByTagName("isopen").item(0)

						.getChildNodes().item(0).getNodeValue();
				String name = elem.getElementsByTagName("name").item(0)

						.getChildNodes().item(0).getNodeValue();
				String prof = elem.getElementsByTagName("prof").item(0)

						.getChildNodes().item(0).getNodeValue();
				String ta = elem.getElementsByTagName("ta").item(0)

						.getChildNodes().item(0).getNodeValue();
				String demand = elem.getElementsByTagName("demand").item(0)

						.getChildNodes().item(0).getNodeValue();
				String pre = elem.getElementsByTagName("presetseat").item(0)

						.getChildNodes().item(0).getNodeValue();

				ArrayList<String> t = new ArrayList<>();

				t.add(isfunda);
				t.add(isopen);
				t.add(name);
				t.add(prof);
				t.add(ta);
				t.add(demand);
				t.add(pre);
				hm.put(id, t);

			}
		}

		// System.out.println(hm.size());
		//
		// for (String key : hm.keySet()) {
		// System.out.println(key + hm.get(key).get(3));
		//
		// }

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);

			stmt = c.createStatement();
			int num = 1;

			for (String key : hm.keySet()) {

				String id = "'" + key + "'";
				int isfunda = Integer.parseInt(hm.get(key).get(0));
				int isopen = Integer.parseInt(hm.get(key).get(1));
				String name = "'" + hm.get(key).get(2) + "'";
				String prof = "'" + hm.get(key).get(3) + "'";
				String ta = "'" + hm.get(key).get(4) + "'";
				int demand = Integer.parseInt(hm.get(key).get(5));
				int preset = Integer.parseInt(hm.get(key).get(6));

				String sql = "INSERT INTO COURSE (NUM, ID, ISFUNDA, ISOPEN, NAME, PROF, TA, DEMAND, PRESETSEAT) "
						+ "VALUES (" + num + ", " + id + ", " + isfunda + ", " + isopen + ", " + name + ", " + prof
						+ ", " + ta + ", " + demand + ", " + preset + ");";
				stmt.executeUpdate(sql);
				num++;

			}

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Courses Tables updated successfully");

	}

}
