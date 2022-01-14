package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller implements Initializable {
    @FXML private Button btMainLogin;
    @FXML private TextField textID;
    @FXML private PasswordField textPassword;
    
    @FXML private Button btTimeManager;
    @FXML private Button btUserRecord;
    @FXML private Button btAdminMonth;
    @FXML private Button btAdminLogoff;
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    //DB���� �޼���
    public void startDb() {

    	 try {//----2 ����̹��ε�
       	  Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("����̹� �ε� ����");
         } catch (ClassNotFoundException e) {
            System.err.println("����̹� �ε� ����");
            System.exit(0);
         }
         try {//----3
            String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
            conn = DriverManager.getConnection(url, "mytest", "mytest");
            System.out.println("�����ͺ��̽� ���� ����");
         }catch(SQLException sqle) {
            System.err.println("�����ͺ��̽� ���� ����");
            System.exit(0);
         }
    }
    
    //DB ���� ���� �޼���
    public void endDb() {
        try { if(rs != null) rs.close(); }catch(SQLException sqle) { }
        try { if(pstmt != null) pstmt.close(); }catch(SQLException sqle) { }
        try { if(stmt != null) stmt.close(); }catch(SQLException sqle) { }
        try { if(conn != null) conn.close(); }catch(SQLException sqle) { }
   }      
    
    // ���� �α���â �޼���
    public void btMainLogin(ActionEvent e) throws IOException{
    	startDb();
    	if( )
    	try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("admin_Main.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			Stage cw = (Stage)btMainLogin.getScene().getWindow();
			cw.close();
			} catch(IOException ioe) {ioe.printStackTrace();}
	}
    
    public void textID() {    }
    public void textPassword() {    }
    // Admin �α��� �޼���
    public void btTimeManager(ActionEvent e) throws IOException{ 
    	try {
    		Stage	stage = new Stage();
	    	Parent root = FXMLLoader.load(getClass().getResource("admin_timeManager.fxml"));
	    	Scene scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show(); 
	    	} catch (IOException ioe) {ioe.printStackTrace();} 
	}
    
    public void btUserRecord() {
    	try {
    		Stage	stage = new Stage();
	    	Parent root = FXMLLoader.load(getClass().getResource("user_record.fxml"));
	    	Scene scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show(); 
	    	} catch (IOException ioe) {ioe.printStackTrace();} 
    }
    public void btAdminRecord() {
    	try {
    		Stage	stage = new Stage();
	    	Parent root = FXMLLoader.load(getClass().getResource("admin_record.fxml"));
	    	Scene scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show(); 
	    	} catch (IOException ioe) {ioe.printStackTrace();} 
    }
    public void btAdmintLogoff() {
    }
    public void comboAdminMonth() {
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //DB����
	/*
	 * public class JDBCTest { public static void main(String[] args) { try {//----2
	 * ����̹��ε� Class.forName("oracle.jdbc.driver.OracleDriver");
	 * System.out.println("����̹� �ε� ����"); } catch (ClassNotFoundException e) {
	 * System.err.println("����̹� �ε� ����"); System.exit(0); }
	 * 
	 * Connection conn = null; try {//----3 String url =
	 * "jdbc:oracle:thin:@localhost:1521/XEPDB1"; conn =
	 * DriverManager.getConnection(url, "mytest", "mytest");
	 * System.out.println("�����ͺ��̽� ���� ����"); }catch(SQLException sqle) {
	 * System.err.println("�����ͺ��̽� ���� ����"); System.exit(0); }
	 * 
	 * } }
	 */
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	}
}
