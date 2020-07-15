
package planner;

import java.io.IOException;
//import java.sql.Connection;
import java.sql.*;
import java.text.ParseException;

//planner

/**
 *
 * @author Alex
 */
public class Planner {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.text.ParseException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws IOException, ParseException, SQLException, ClassNotFoundException {
//        createTables(connectDB());
//        insertData(connectDB());
//        selectData(connectDB());
//        insertSemesterData(2,"Summer Semester 2015-2016");
//        selectSemesterTable();
//        insertModuleData("MODULE","Programming2",1,1,1);
//        insertExamData(1,"20-05-2017",20,120);
//        insertMilestoneData(1,"Complete java tutorial","finish java coursework",20);
//        insertCwStudyActivityData(1, "study c++",1,15,"hello world",20);
//        insertExamStudyActivityData(1, "study c++",1,15,"hello world",20);
//        Semester semester = new Semester();
//       semester.insertSemesterData(0, "Spring Semester 2015-2016");
//          Module module = new Module();
//          module.insertModuleData("MODULE", "Programming", 1);
//        createTables(connectDB());
    }

    public static Connection connectDB() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:planner.db");
            c.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

    public static void createTables(Connection c) throws SQLException {
        Statement stmt = null;
        stmt = c.createStatement();
        String sql = "";
        sql = "CREATE TABLE SEMESTER"
                + "(SEMESTER_ID INT PRIMARY KEY     NOT NULL,"
                + "SEMESTER_NAME    CHAR(20)        NOT NULL);";
        stmt.executeUpdate(sql);
        
//        stmt.executeUpdate("DROP TABLE IF EXISTS 'MODULE';");
        
        sql = "CREATE TABLE MODULE"
                + "(MODULE_ID       CHAR(10) PRIMARY KEY   NOT NULL,"
                + "MODULE_NAME      CHAR(20)               NOT NULL,"
                + "SEMESTER_ID      INT                    NOT NULL,"
                + "FOREIGN KEY(SEMESTER_ID) REFERENCES SEMESTER(SEMESTER_ID));";
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE COURSEWORK"
                + "(COURSEWORK_ID       INT PRIMARY KEY        NOT NULL,"
                + "COURSEWORK_NAME      CHAR(20)               NOT NULL,"
                + "COURSEWORK_WEIGHT    INT                    NOT NULL,"
                + "COURSEWORK_DEADLINE  DATE                   NOT NULL,"
                + "Module_ID            CHAR(10)                    NOT NULL,"
                + "FOREIGN KEY(Module_ID) REFERENCES MODULE(MODULE_ID));";
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE EXAM"
                + "(EXAM_ID             INT PRIMARY KEY        NOT NULL,"
                + "EXAM_DATE            DATE                   NOT NULL,"
                + "EXAM_WEIGHT          INT                    NOT NULL,"
                + "EXAM_LENGTH          INT                    NOT NULL,"
                + "Module_ID            CHAR(10)                    NOT NULL,"
                + "FOREIGN KEY(Module_ID) REFERENCES MODULE(MODULE_ID));";
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE COURSEWORK_STUDY_TASK"
                + "(COURSEWORK_STUDY_TASK_ID    INT PRIMARY KEY     NOT NULL,"
                + "STUDY_TASK_NAME              CHAR(50)            NOT NULL,"
                + "COURSEWORK_ID                INT                 NOT NULL,"
                + "NOTE                         TEXT                NOT NULL,"
                + "TIME_SPENT                   INT                 NOT NULL,"
                + "STUDY_TYPE                   CHAR(20)            NOT NULL,"
                + "FOREIGN KEY(COURSEWORK_ID) REFERENCES COURSEWORK(COURSEWORK_ID));";
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE EXAM_STUDY_TASK"
                + "(EXAM_STUDY_TASK_ID  INT PRIMARY KEY     NOT NULL,"
                + " STUDY_TASK_NAME     CHAR(50)            NOT NULL, "
                + " EXAM_ID             INT                 NOT NULL, "
                + " NOTE                TEXT                NOT NULL, "
                + " TIME_SPENT          INT                 NOT NULL, "
                + " STUDY_TYPE          CHAR(50)            NOT NULL,"
                + "FOREIGN KEY(EXAM_ID) REFERENCES EXAM(EXAM_ID));";
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE MILESTONE"
                + "(MILESTONE_ID        INT   PRIMARY KEY   NOT NULL,"
                + " MILESTONE_NAME      CHAR(50)            NOT NULL, "
                + " TARGET              CHAR(50)            NOT NULL, "
                + " PROGRESS            INT                 NOT NULL);";
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE COURSEWORK_STUDY_ACTIVITIES"
                + "(STUDY_ACTIVITY_ID    INT PRIMARY KEY    NOT NULL, "
                + "STUDY_ACTIVITY_NAME  CHAR(50)            NOT NULL, "
                + "COURSEWORK_STUDY_TASK_ID INT             NOT NULL, "
                + "PROGRESS_UPDATE      CHAR(50)            NOT NULL, "
                + "NOTE                 TEXT                NOT NULL, "
                + "TIME_SPENT           INT                 NOT NULL, "
                + "FOREIGN KEY(COURSEWORK_STUDY_TASK_ID) "
                + "REFERENCES COURSEWORK_STUDY_TASK(COURSEWORK_STUDY_TASK_ID));";
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE EXAM_STUDY_ACTIVITIES"
                + "(STUDY_ACTIVITY_ID    INT PRIMARY KEY    NOT NULL, "
                + "STUDY_ACTIVITY_NAME  CHAR(50)            NOT NULL, "
                + "EXAM_STUDY_TASK_ID   INT                 NOT NULL, "
                + "PROGESS_UPDATE       CHAR(50)            NOT NULL, "
                + "NOTE                 TEXT                NOT NULL, "
                + "TIME_SPENT           INT                 NOT NULL,"
                + "FOREIGN KEY(EXAM_STUDY_TASK_ID) "
                + "REFERENCES EXAM_STUDY_TASK(EXAM_STUDY_TASK_ID));";
        stmt.executeUpdate(sql);
        stmt.close();
        c.commit();
        c.close();
    }

    public static void insertData(Connection c) throws SQLException {
        Statement stmt = null;
        stmt = c.createStatement();
//        String sql = "INSERT INTO SEMESTER(SEMESTER_ID, SEMESTER_NAME, MODULE_ID)"
//                + "VALUES(1,'spring 2015-2016','CMP-2039');";
//        String sql = "DROP TABLE SEMESTER;";
//        stmt.executeUpdate(sql);
        stmt.close();
        c.close();
    }

    public static void selectData(Connection c) throws SQLException {
        Statement stmt = null;
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + "SEMESTER" + ";");
        while (rs.next()) {
            int id = rs.getInt("SEMESTER_ID");
            String name = rs.getString("SEMESTER_NAME");
            String moduleID = rs.getString("MODULE_ID");
            System.out.println(id);
            System.out.println(name);
            System.out.println(moduleID + "\n");
        }
    }
        public static void dropTable(Connection c) throws SQLException {
        Statement stmt = null;
        stmt = c.createStatement();
        stmt.executeUpdate("DROP TABLE EXAM");
    }
}
