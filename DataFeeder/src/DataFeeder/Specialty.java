package DataFeeder;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Specialty {

	private String dbPath;
	private String user;
	private String password;

	public Specialty(String dbPath, String user, String password) {
		super();
		this.dbPath = dbPath;
		this.user = user;
		this.password = password;
	}

	public void createSpecialties() {
		// TODO Auto-generated method stub

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);

			stmt = c.createStatement();
			String sql = "CREATE TABLE CPR " + "(ID TEXT, MUST INT)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE CS " + "(ID TEXT, MUST INT)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE II" + "(ID TEXT, MUST INT)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE ML " + "(ID TEXT, MUST INT)";
			stmt.executeUpdate(sql);

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("4 Speciaty Tables created successfully");

	}

	public void insertCourses(String filepath) throws ParserConfigurationException, SAXException, IOException {

		String table = "";
		switch (filepath.charAt(23)) {
		case 'p':
			table = "CPR";
			break;
		case 's':
			table = "CS";
			break;
		case 'i':
			table = "II";
			break;
		case 'l':
			table = "ML";
			break;
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(new File(filepath));

		HashMap<String, Integer> hm = new HashMap<>();

		NodeList nodeList = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {

			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) node;

				// Get the value of all sub-elements.

				String id = elem.getElementsByTagName("id")

						.item(0).getChildNodes().item(0).getNodeValue();

				String core = elem.getElementsByTagName("iscore").item(0)

						.getChildNodes().item(0).getNodeValue();

				hm.put(id, Integer.parseInt(core));

			}

		}

		/*
		 * System.out.println(hm.size()); for (String key : hm.keySet()) {
		 * System.out.println(key + ": " + hm.get(key));
		 * 
		 * }
		 */
		// System.out.println(table);

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(this.dbPath, this.user, this.password);

			stmt = c.createStatement();

			for (String key : hm.keySet()) {
				String k = "'" + key + "'";
				String sql = "INSERT INTO " + table + "(ID, MUST) " + "VALUES (" + k + ", " + hm.get(key) + ");";
				stmt.executeUpdate(sql);

			}

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("4 Speciaty Tables created successfully");

	}

}
