
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
public class StudyMilestone {

    private String milestoneName;
    private String target;
    private int progress;

    public StudyMilestone() {

    }

    public DefaultListModel selectMilestoneTable() throws SQLException, ClassNotFoundException {

        Connection c = null;
        Statement stmt = null;
        DefaultListModel DLM = new DefaultListModel();
//        JTextArea tArea = new JTextArea();
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM MILESTONE");

        while (rs.next()) {
            int milestone_ID = rs.getInt("milestone_ID");
            String milestone_Name = rs.getString("MILESTONE_NAME");
            String milestone_Target = rs.getString("TARGET");
            int milestone_Progress = rs.getInt("PROGRESS");
            
            System.out.println("Milestone ID = " + milestone_ID);
            System.out.println("MileStone Name = " + milestone_Name);
            System.out.println("Target = " + milestone_Target);
            System.out.println("Progress = " + milestone_Progress);
            System.out.println();

            DLM.addElement("ID: " + milestone_ID + " | NAME: " + milestone_Name);
        }
        rs.close();
        stmt.close();
        c.commit();
        c.close();
        return DLM;
    }

    
    
    public DefaultListModel selectStudyMilestoneData(JList list) throws SQLException, ClassNotFoundException {
        String temp = (String) list.getSelectedValue();
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

        ResultSet rs = stmt.executeQuery("SELECT MILESTONE.MILESTONE_ID, "
                                        + "MILESTONE.MILESTONE_NAME, "
                                        + "MILESTONE.TARGET"
                                        + "MILESTONE.PROGRESS"
                                        + "FROM MILESTONE");

        while (rs.next()) {
            String milestone_Name = rs.getString("MILESTONE_NAME");
            int milestone_ID = rs.getInt("MILESTONE_ID");
            String milestone_Target = rs.getString("TARGET");
            int milestone_Progress = rs.getInt("PROGRESS");

//            System.out.println("ID = " + id);
            System.out.println("MileStone Name = " + milestone_Name);
            System.out.println("Milestone IS = " + milestone_ID);
            System.out.println("Target = " + milestone_Target);
            System.out.println("Progress = " + milestone_Progress);
            System.out.println();

            DLM.addElement("MileStone Name: " + milestone_Name + " | Milestone ID: " + milestone_ID + " | Target:" + milestone_Target + " | Progress: " + milestone_Progress);
        }
        rs.close();
        stmt.close();
        c.commit();
        c.close();
        return DLM;
    }

    public void insertMilestoneData(int milestoneID, String milestoneName, String target, int progress) throws SQLException, ClassNotFoundException {
        Connection c = null;
        Statement stmt = null;

        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        stmt = c.createStatement();
        String sql = "";
        sql = "INSERT INTO MILESTONE(MILESTONE_ID, MILESTONE_NAME, TARGET, PROGRESS)"
                + "VALUES(" + milestoneID + ",'" + milestoneName + "','" + target + "'," + progress + ");";
//        System.out.println(sql);
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
    }

}
