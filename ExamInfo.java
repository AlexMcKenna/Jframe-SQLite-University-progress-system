
package planner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Alex
 */
public class ExamInfo {
//    private Module module;

    private String examDate;
    private int length;//exam time length(120min)
    private int markValue;//eg: 40%

    public ExamInfo() {
    }

    public void insertExamData(int examID, String examDate, int examWeight, int examLength, String moduleID) throws SQLException, ClassNotFoundException {
        Connection c = null;
        Statement stmt = null;

        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        stmt = c.createStatement();
        String sql = "";
        sql = "INSERT INTO EXAM(EXAM_ID, EXAM_DATE, EXAM_WEIGHT, EXAM_LENGTH, MODULE_ID)"
                + "VALUES(" + examID + ",'" + examDate + "'," + examWeight + "," + examLength + ",'" + moduleID + "');";
//        System.out.println(sql);
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
    }

    public DefaultListModel selectExamTable() throws SQLException, ClassNotFoundException {
        Connection c = null;
        Statement stmt = null;
        DefaultListModel DLM = new DefaultListModel();
//        JTextArea tArea = new JTextArea();
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM EXAM");

        while (rs.next()) {
            String ID = rs.getString("EXAM_ID");
            String examDate1 = rs.getString("EXAM_DATE");
            int examWeight = rs.getInt("EXAM_WEIGHT");
            String examLength = rs.getString("EXAM_LENGTH");
            String modID = rs.getString("Module_ID");

//            System.out.println("ID = " + id);
            System.out.println("NAME = " + examDate1);
            System.out.println("Weight = " + examWeight);
            System.out.println("Deadline = " + examLength);
            System.out.println("Module ID = " + modID);
            System.out.println();

            DLM.addElement(ID);
        }
        rs.close();
        stmt.close();
        c.commit();
        c.close();
        return DLM;
    }
    
    public DefaultListModel selectExamData(JList list, JList list2) throws SQLException, ClassNotFoundException {
        String temp = (String) list.getSelectedValue();
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

        ResultSet rs = stmt.executeQuery("SELECT MODULE.MODULE_NAME, MODULE.MODULE_ID, EXAM.EXAM_DATE, EXAM.EXAM_WEIGHT, EXAM.EXAM_LENGTH "
                + "FROM EXAM, MODULE, SEMESTER"
                + " WHERE MODULE.MODULE_NAME ='" + temp + "'"
                + "AND SEMESTER.SEMESTER_NAME = '" + temp2 + "'"
                + " AND SEMESTER.SEMESTER_ID = MODULE.SEMESTER_ID"
                + " AND MODULE.MODULE_ID = EXAM.MODULE_ID;");

        while (rs.next()) {
//            String id = rs.getString("Module_ID");
            String modName = rs.getString("MODULE_NAME");
            String examDate1 = rs.getString("EXAM_DATE");
            int examWeight = rs.getInt("EXAM_WEIGHT");
            String examLength = rs.getString("EXAM_LENGTH");

//            System.out.println("ID = " + id);
            System.out.println("NAME = " + examDate1);
            System.out.println("Weight = " + examWeight);
            System.out.println("Deadline = " + examLength);
            System.out.println();

            DLM.addElement("Module Name: " + modName + " Date:" + examDate1 + " Weight: " + examWeight + " Exam Length: " + examLength);
        }
        rs.close();
        stmt.close();
        c.commit();
        c.close();
        return DLM;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMarkValue() {
        return markValue;
    }

    public void setMarkValue(int markValue) {
        this.markValue = markValue;
    }

}
