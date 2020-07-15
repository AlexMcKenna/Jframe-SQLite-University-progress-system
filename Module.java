package planner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;


/**
 *
 * @author Alex
 */
public class Module {

    private String moduleID;
    private String moduleName;
//    private String semester; //A, B , Y
    private ArrayList<Coursework> coursework = new ArrayList<>();
    private ArrayList<ExamInfo> examInfo = new ArrayList<>();
//    private String courseworkName;
//    private Date courseworkDeadline;
//    private int courseworkMarkValue;
//    private Date examDate;
//    private int examMarkValue;

    Module() {

    }

    public void insertModuleData(String moduleID, String moduleName, int semesterID) throws SQLException, ClassNotFoundException {
        Connection c = null;
        Statement stmt = null;

        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        stmt = c.createStatement();
        String sql = "";
        sql = "INSERT INTO MODULE(MODULE_ID, MODULE_NAME, SEMESTER_ID)"
                + "VALUES('" + moduleID + "','" + moduleName + "'," + semesterID + ");";
//        System.out.println(sql);
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void addCoursework(Coursework coursework) {
        this.coursework.add(coursework);
    }

    public void addExam(ExamInfo exam) {
        this.examInfo.add(exam);
    }

    public DefaultListModel selectModuleTable(JList list) throws SQLException, ClassNotFoundException {
        String temp = (String)list.getSelectedValue();
        Connection c = null;
        Statement stmt = null;
        DefaultListModel DLM = new DefaultListModel();
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        System.out.println("temp="+temp);
        stmt = c.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT MODULE.MODULE_ID, MODULE.MODULE_NAME, SEMESTER.SEMESTER_NAME FROM MODULE, SEMESTER"
                + " WHERE SEMESTER_NAME = '"+temp+"'"
                + "AND MODULE.SEMESTER_ID = SEMESTER.SEMESTER_ID;");
        while (rs.next()) {
            String id= rs.getString("MODULE_ID");
            String name = rs.getString("MODULE_NAME");

            System.out.println("ID = " + id);
            System.out.println("NAME = " + name);
            System.out.println();

            DLM.addElement(id);
        }
        rs.close();
        stmt.close();
        c.commit();
        c.close();
        return DLM;
    }
}
