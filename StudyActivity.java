
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
public class StudyActivity {

    private String activityName;
    private String progressUpdate;
    private String note;
    private int timeSpent;

    public StudyActivity() {

    }

    public DefaultListModel selectCwkStudyActivityData(JList list) throws SQLException, ClassNotFoundException {
        String temp = list.getSelectedValue().toString();
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

        ResultSet rs = stmt.executeQuery("SELECT COURSEWORK_STUDY_ACTIVITIES.STUDY_ACTIVITY_ID, "
                + " COURSEWORK_STUDY_ACTIVITIES.STUDY_ACTIVITY_NAME, "
                + " COURSEWORK_STUDY_TASK.COURSEWORK_STUDY_TASK_ID,"
                + " COURSEWORK_STUDY_ACTIVITIES.PROGRESS_UPDATE, "
                + " COURSEWORK_STUDY_ACTIVITIES.NOTE,"
                + " COURSEWORK_STUDY_ACTIVITIES.TIME_SPENT"
                + " FROM COURSEWORK_STUDY_ACTIVITIES,COURSEWORK_STUDY_TASK"
                + " WHERE COURSEWORK_STUDY_TASK.COURSEWORK_STUDY_TASK_ID = " + temp 
                + " AND COURSEWORK_STUDY_ACTIVITIES.COURSEWORK_STUDY_TASK_ID = COURSEWORK_STUDY_TASK.COURSEWORK_STUDY_TASK_ID;");

        while (rs.next()) {
            int cw_study_act_ID = rs.getInt("STUDY_ACTIVITY_ID");
            String cw_study_act_name = rs.getString("STUDY_ACTIVITY_NAME");
            int cw_study_act_task_ID = rs.getInt("COURSEWORK_STUDY_TASK_ID");
            String cw_study_act_progress = rs.getString("PROGRESS_UPDATE");
            String cw_study_act_Note = rs.getString("NOTE");
            int cw_study_act_timeSpent = rs.getInt("TIME_SPENT");

//            System.out.println("ID = " + id);
            System.out.println("Study Activity ID = " + cw_study_act_ID);
            System.out.println("Study Activity Name = " + cw_study_act_name);
            System.out.println("Coursework Study Task = " + cw_study_act_task_ID);
            System.out.println("Progress Update = " + cw_study_act_progress);
            System.out.println("Notes = " + cw_study_act_Note);
            System.out.println("Time Spent = " + cw_study_act_timeSpent);
            System.out.println();

            DLM.addElement("Study Activity ID: " + cw_study_act_ID);
            DLM.addElement("| Study Activity Name: " + cw_study_act_task_ID);
            DLM.addElement("| Coursework Study Task: " + cw_study_act_task_ID);
            DLM.addElement("| Progress Update: " + cw_study_act_progress);
            DLM.addElement("| Notes: " + cw_study_act_Note);
            DLM.addElement("| Time Spent: " + cw_study_act_timeSpent);
            DLM.addElement( "------------------------------------------------");
            
        }
        rs.close();
        stmt.close();
        c.commit();
        c.close();
        return DLM;
    }

    public DefaultListModel selectExamStudyActivityData(JList list) throws SQLException, ClassNotFoundException {
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

        ResultSet rs = stmt.executeQuery("SELECT EXAM_STUDY_ACTIVITIES.STUDY_ACTIVITY_ID, "
                + " EXAM_STUDY_ACTIVITIES.STUDY_ACTIVITY_NAME, "
                + " EXAM_STUDY_TASK.EXAM_STUDY_TASK_ID,"
                + " EXAM_STUDY_ACTIVITIES.PROGESS_UPDATE, "
                + " EXAM_STUDY_ACTIVITIES.NOTE,"
                + " EXAM_STUDY_ACTIVITIES.TIME_SPENT"
                + " FROM EXAM_STUDY_ACTIVITIES,EXAM_STUDY_TASK"
                + " WHERE EXAM_STUDY_ACTIVITIES.EXAM_STUDY_TASK_ID = '" + temp + "'"
                + " AND EXAM_STUDY_ACTIVITIES.EXAM_STUDY_TASK_ID = EXAM_STUDY_TASK.EXAM_STUDY_TASK_ID;");

        while (rs.next()) {
            int exam_study_actID = rs.getInt("STUDY_ACTIVITY_ID");
            String exam_study_actName = rs.getString("STUDY_ACTIVITY_NAME");
            int exam_study_actTaskID = rs.getInt("EXAM_STUDY_TASK_ID");
            String exam_study_act_progress = rs.getString("PROGESS_UPDATE");
            String exam_study_actNote = rs.getString("NOTE");
            int exam_study_actTimeSpent = rs.getInt("TIME_SPENT");

//            System.out.println("ID = " + id);
            System.out.println("Study Activity ID = " + exam_study_actID);
            System.out.println("Study Activity Name = " + exam_study_actName);
            System.out.println("Coursework Study Task = " + exam_study_actTaskID);
            System.out.println("Progress Update = " + exam_study_act_progress);
            System.out.println("Notes = " + exam_study_actNote);
            System.out.println("Time Spent = " + exam_study_actTimeSpent);
            System.out.println();

            DLM.addElement("Study Activity ID: " + exam_study_actID);
            DLM.addElement("| Study Activity Name: " + exam_study_actName);
            DLM.addElement("| Coursework Study Task: " + exam_study_actTaskID);
            DLM.addElement("| Progress Update: " + exam_study_act_progress);
            DLM.addElement("| Notes:" + exam_study_actNote);
            DLM.addElement("| Time Spent:" + exam_study_actTimeSpent);
            DLM.addElement( "------------------------------------------------");
        }
        rs.close();
        stmt.close();
        c.commit();
        c.close();
        return DLM;
    }

    public void insertCwStudyActivityData(int activityID, String activityName,
            int cwStudyID, int progress, String note, int timeSpent) throws SQLException, ClassNotFoundException {
        Connection c = null;
        Statement stmt = null;

        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        stmt = c.createStatement();
        String sql = "";
        sql = "INSERT INTO COURSEWORK_STUDY_ACTIVITIES(STUDY_ACTIVITY_ID, STUDY_ACTIVITY_NAME, COURSEWORK_STUDY_TASK_ID, PROGRESS_UPDATE,NOTE,TIME_SPENT)"
                + "VALUES(" + activityID + ",'" + activityName + "'," + cwStudyID + "," + progress + ",'" + note + "'," + timeSpent + ");";
//        System.out.println(sql);
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
    }

    public void insertExamStudyActivityData(int activityID, String activityName,
            int examStudyID, int progress, String note, int timeSpent) throws SQLException, ClassNotFoundException {
        Connection c = null;
        Statement stmt = null;

        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:planner.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        stmt = c.createStatement();
        String sql = "";
        sql = "INSERT INTO EXAM_STUDY_ACTIVITIES(STUDY_ACTIVITY_ID, STUDY_ACTIVITY_NAME, EXAM_STUDY_TASK_ID, PROGESS_UPDATE,NOTE,TIME_SPENT)"
                + "VALUES(" + activityID + ",'" + activityName + "'," + examStudyID + "," + progress + ",'" + note + "'," + timeSpent + ");";
//        System.out.println(sql);
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
    }
}
