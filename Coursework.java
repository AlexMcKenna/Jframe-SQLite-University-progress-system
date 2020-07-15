
package planner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author Alex
 */
public class Coursework {

    private String courseworkName;
    private String deadline;
    private int markValue;

    public Coursework() {

    }

    public void insertCourseworkData(int cw_ID, String cw_Name,
            int cw_Weight, String cw_Deadline, String moduleID) throws SQLException, ClassNotFoundException {

        Connection c = null;
        Statement stmt = null;

        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        stmt = c.createStatement();
        String sql = "";
        sql = "INSERT INTO COURSEWORK(COURSEWORK_ID, COURSEWORK_NAME, COURSEWORK_WEIGHT, COURSEWORK_DEADLINE, MODULE_ID)"
                + "VALUES(" + cw_ID + ",'" + cw_Name + "'," + cw_Weight + ",'" + cw_Deadline + "','" + moduleID + "');";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
    }

    public DefaultListModel selectCourseworkData1() throws SQLException, ClassNotFoundException {

        Connection c = null;
        Statement stmt = null;
        DefaultListModel DLM = new DefaultListModel();
//        JTextArea tArea = new JTextArea();
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM COURSEWORK");

        while (rs.next()) {
            int id = rs.getInt("COURSEWORK_ID");
            String cwname = rs.getString("COURSEWORK_NAME");
            int cwWeight = rs.getInt("COURSEWORK_WEIGHT");
            int cwDeadline = rs.getInt("COURSEWORK_DEADLINE");
            String Module_ID = rs.getString("Module_ID");

            System.out.println("ID = " + id);
            System.out.println("NAME = " + cwname);
            System.out.println("Weight = " + cwWeight);
            System.out.println("Deadline = " + cwDeadline);
            System.out.println("ModuleID = " + Module_ID);
            System.out.println();

            DLM.addElement(id);
        }
        rs.close();
        stmt.close();
        c.commit();
        c.close();
        return DLM;
    }

    public DefaultListModel selectCourseworkData(JList list, JList list2) throws SQLException, ClassNotFoundException {
        String temp = (String) list.getSelectedValue();//module
        String temp2 = (String) list2.getSelectedValue();//semester
        Connection c = null;
        Statement stmt = null;
        DefaultListModel DLM = new DefaultListModel();
//        JTextArea tArea = new JTextArea();
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        System.out.println("temp=" + temp);
        stmt = c.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT SEMESTER.SEMESTER_NAME, MODULE.MODULE_NAME, MODULE.MODULE_ID, COURSEWORK.COURSEWORK_ID, COURSEWORK.COURSEWORK_NAME, COURSEWORK.COURSEWORK_WEIGHT, COURSEWORK.COURSEWORK_DEADLINE "
                + "FROM COURSEWORK, MODULE, SEMESTER"
                + " WHERE MODULE.MODULE_NAME ='" + temp + "' "//change to module name
                + "AND SEMESTER.SEMESTER_NAME = '" + temp2 + "'"
                + " AND SEMESTER.SEMESTER_ID = MODULE.SEMESTER_ID"
                + " AND MODULE.MODULE_ID = COURSEWORK.MODULE_ID;");

        while (rs.next()) {
            String id = rs.getString("COURSEWORK_ID");
            String modName = rs.getString("MODULE_NAME");
            String cwName = rs.getString("COURSEWORK_NAME");
            int cwWeight = rs.getInt("COURSEWORK_WEIGHT");
            String cwDeadline = rs.getString("COURSEWORK_DEADLINE");

//            System.out.println("ID = " + id);
            System.out.println("NAME = " + cwName);
            System.out.println("Weight = " + cwWeight);
            System.out.println("Deadline = " + cwDeadline);
            System.out.println();

            DLM.addElement("Module Name: " + modName + " | Coursework ID: " + id + " | NAME:" + cwName + " | Weight: " + cwWeight + " | Deadline: " + cwDeadline);
        }
        rs.close();
        stmt.close();
        c.commit();
        c.close();
        return DLM;
    }

    public String getCourseworkName() {
        return courseworkName;
    }

    public void setCourseworkName(String courseworkName) {
        this.courseworkName = courseworkName;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getMarkValue() {
        return markValue;
    }

    public void setMarkValue(int markValue) {
        this.markValue = markValue;
    }

}
