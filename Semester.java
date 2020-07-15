
package planner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Alex
 */
public class Semester {

    private ArrayList<Module> module = new ArrayList<>();
    private String semesterName;
//    private String ModuleID;
//    private String ModuleName;
//    private Coursework coursework;
//    private ExamInfo exam;

    public Semester() {

    }

    public void addModule(Module module) {
        this.module.add(module);
    }

    public void insertSemesterData(int semesterID, String semesterName) throws SQLException, ClassNotFoundException {
        Connection c = null;
        Statement stmt = null;
        this.semesterName = semesterName;

        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        stmt = c.createStatement();
        String sql = "";
        sql = "INSERT INTO SEMESTER(SEMESTER_ID, SEMESTER_NAME)"
                + "VALUES(" + semesterID + ",'" + semesterName + "');";
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
    }

    public DefaultListModel selectSemesterTable() throws SQLException, ClassNotFoundException {
        Connection c = null;
        Statement stmt = null;
        DefaultListModel DLM = new DefaultListModel();  
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM SEMESTER;");
        while (rs.next()) {
            int id = rs.getInt("SEMESTER_ID");
            String name = rs.getString("SEMESTER_NAME");

            System.out.println("ID = " + id);
            System.out.println("NAME = " + name);
            System.out.println();
            
            DLM.addElement(name);
        }
        rs.close();
        stmt.close();
        c.commit();
        c.close();
        return DLM;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

}
