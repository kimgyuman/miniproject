package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller implements Initializable {
	@FXML	private Button btMainLogin;
	@FXML	private TextField textID;
	@FXML	private PasswordField textPassword;
	@FXML	private Button btTimeManager;
	@FXML	private Button btUserRecord;
	@FXML	private Button btAdminMonth;
	@FXML	private Button btadminLogoff;/////////////
	@FXML	private TextField tmId;
	@FXML	private TextField tmPassword;
	@FXML	private TextField tmName;
	@FXML	private ChoiceBox<String> tmTel1;
	@FXML	private TextField tmTel2;
	@FXML	private TextField tmTel3;
	@FXML	private TextField tmIdNumber1;
	@FXML	private TextField tmIdNumber2;
	@FXML	private Button btuserLogoff;/////////////
	@FXML	private Button btGoOffice;
	@FXML	private Button btLeaveOffice;
	@FXML	private ChoiceBox<String> btChoiceName;
	@FXML	private Button btCreate;
	@FXML	private Button btDelete;
	@FXML	private Button btUpdate;
	@FXML	private ChoiceBox<String> btChoiceName1;
	@FXML	private ComboBox<String> btcomboname1;
	//@FXML	private ComboBox<String> btcomboname;
	
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("MM");
	SimpleDateFormat format3 = new SimpleDateFormat("dd");
	SimpleDateFormat format4 = new SimpleDateFormat("HH");
	SimpleDateFormat format5 = new SimpleDateFormat("mm");
	SimpleDateFormat format6 = new SimpleDateFormat ("yyyy-MM-dd");
	
	String format_time1 = format1.format(System.currentTimeMillis());
	String format_time2 = format2.format(System.currentTimeMillis());
	String format_time3 = format3.format(System.currentTimeMillis());
	String format_time4 = format4.format(System.currentTimeMillis());
	String format_time5 = format5.format(System.currentTimeMillis());
	String format_time6 = format6.format(System.currentTimeMillis());
	///////////
	
	
	
	static String loginName = null;
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	//db���� �޼���
	// DB���� �޼���
	public void startDb() {

		try {// ----2 ����̹��ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����");
		} catch (ClassNotFoundException e) {
			System.err.println("����̹� �ε� ����");
			System.exit(0);
		}
		try {// ----3
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			conn = DriverManager.getConnection(url, "mytest", "mytest");
			System.out.println("�����ͺ��̽� ���� ����");
		} catch (SQLException sqle) {
			System.err.println("�����ͺ��̽� ���� ����");
			System.exit(0);
		}
	}
	// DB ���� ���� �޼���
	public void endDb() {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException sqle) {
		}
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException sqle) {
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException sqle) {
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException sqle) {
		}
	}
	//mainȭ�� ���� �޼���
	// ----------- Main login �޼���
	public void btMainLogin(ActionEvent e) throws IOException {
		startDb();

		try {
			pstmt = conn.prepareStatement("select * from employee where EMPID = ?");
			pstmt.setString(1, textID.getText());
			rs = pstmt.executeQuery();

			if ((textID.getText()).equals("0000")) { // admin ID�� �´ٸ�
				if ((textPassword.getText()).equals("0000")) { // admin password�� �´ٸ�

					try { // admin â ����
						Stage stage = new Stage();
						Parent root = FXMLLoader.load(getClass().getResource("admin_Main.fxml"));
						Scene scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
						Stage cw = (Stage) btMainLogin.getScene().getWindow();
						cw.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}

				} else {
					System.out.println("����� ���̵�� ��й�ȣ�� Ȯ���ϼ���");
				}

			} else { // admin ID�� �ƴ϶��
				if (rs.next()) {
					loginName = rs.getString(1);
					String userPassword = rs.getString(2);
					if (userPassword.equals(textPassword.getText())) {
						try { // user â ����
							Stage stage = new Stage();
							Parent root = FXMLLoader.load(getClass().getResource("user_Main.fxml"));
							Scene scene = new Scene(root);
							stage.setScene(scene);
							stage.show();
							Stage cw = (Stage) btMainLogin.getScene().getWindow();
							cw.close();
						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
					} else
						System.out.println("��");
				}
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("������ ����");
		} finally {
			endDb();
		}
	}
	public void textID() {
	}
	public void textPassword() {
	}
	//adminȭ�� ���� �޼���
	// ----------- Admin Main �޼���
	public void btTimeManager(ActionEvent e) throws IOException {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("admin_timeManager.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	public void btUserRecord() {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("user_record.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	public void btAdminRecord() {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("admin_record.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	public void btadminLogoff() {
		try { // admin â ����
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("login_main.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			Stage cw = (Stage) btadminLogoff.getScene().getWindow();
			cw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
	public void comboAdminMonth() {
	}
	// ---------- Admin timeManager ȭ�� �޼���
	
	public void userVOcreate() {
		userVO uv = new userVO();
		uv.setEmpId(tmId.getText());
		uv.setPWD(tmPassword.getText());
		uv.setEmpName(tmName.getText());
		uv.setTel(tmTel1.getValue()+ tmTel2.getText() + tmTel3.getText());
		uv.setIdNumber(tmIdNumber1.getText() + tmIdNumber2.getText());	
	}
	public void TimeVO() {
		timeVO tv = new timeVO();
		tv.setTimeId(userVO.empId);
		tv.setTimeDate(userVO.empId);
		tv.setTimegoOffice(userVO.empId);
		tv.setTimeleaveOffice(userVO.empId);
		tv.setTimeSum(userVO.empId);
		tv.setTimenote(userVO.empId);
			
	}
	public void btCreate() { //ȸ�����Թ�ư
		
		String userId = tmId.getText();
		String userPassword = tmPassword.getText();
		String userName = tmName.getText();
		String userTel = tmTel2.getText() + tmTel3.getText();
		String userIdnumber = tmIdNumber1.getText() + tmIdNumber2.getText();

		startDb();
		try {
			pstmt = conn.prepareStatement("insert into employee values (?,?,?,?,?)");
			pstmt.setString(1, userId);
			pstmt.setString(2, userPassword);
			pstmt.setString(3, userName);
			pstmt.setString(4, userTel);
			pstmt.setString(5, userIdnumber);
			rs = pstmt.executeQuery();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("������ ����");
		} finally {
			endDb();
		}
	}
	public void btUpdate() {//ȸ������
		startDb();

		try {
			pstmt = conn.prepareStatement("UPDATE employee SET TIMELEAVEOFFICE=? WHERE TIMEID = ?  AND TIMELEAVEOFFICE = ?"); 
			pstmt.setString(1, (format_time4+format_time5));
			pstmt.setString(2, loginName);
			pstmt.setString(3, "EMPTY");
			rs = pstmt.executeQuery();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("������ ����");
		} finally {
			endDb();
		}
	}	
	public void btDelete() {//ȸ������
	}
	//admin �˹ٰ��� ȭ�� �޼���
	
	public void btcomboname1() {
		String[] strArrList = {"��ö��","ȫ�浿","������Ʈ","�̻��","���","������"};
//		//�޺� �ڽ��� �迭�� ��Ҹ� ��ġ ��Ų��.
		ObservableList<String> fxComboNameList = FXCollections.observableArrayList();
		fxComboNameList.add("temp");
//		btcomboname1.getItems().addAll(strArrList);
		btcomboname1.setItems(fxComboNameList);		
//		
	}
	public void btChoiceName1()  {//�̸� ����
		ChoiceBox<String> cb = new ChoiceBox<>();
		cb.getItems().addAll("�ù�","������","���Ƕ��");
		
		//		startDb();
//		ChoiceBox<String> cb = new ChoiceBox<String>();
//		System.out.println("2");
//				try {
//			         pstmt = conn.prepareStatement("select * from employee ");
//			         rs = pstmt.executeQuery(); 
//			        while(rs.next()) {
//				        String EMPNAME = rs.getString(3);
//				      	System.out.println(EMPNAME);
//				      	cb.getItems().add(EMPNAME);
//				      	String st[] = 
//				      	
//			        }
//				} catch (SQLException sqle) {
//					sqle.printStackTrace();
//					System.err.println("������ ����");
//				} finally {  
//				}endDb();
				 	
			       //  while(rs.next()) {
				   // System.out.println(rs.getString(1));	
			        	 /*
							 * int EMPNO = rs.getInt(1); 
							 * String EMPID = rs.getString(2);
							 * String PWD = rs.getString(3); 
							 * String EMPNAME = rs.getString(4); 
							 * int TEL = rs.getInt(5);
							 * int IDNUMBER = rs.getInt(6); 
							 * System.out.println(EMPID + "\t" + PWD + "\t" +
							 * EMPNAME + "\t" + TEL + "\t" + IDNUMBER );
							 */    
				
			     //   cb.getItems().addAll(	)
		//resultSet = statement.executeQuery("Select name from department");

        //while (resultSet.next()) {  // loop

           // Now add the comboBox addAll statement 
            //comboBox.getItems().addAll(resultSet.getString("name")); 
//       }
	}
	public void btChoiceName() {

		/*
		 * ChoiceBox<String> choicebox = new ChoiceBox<>();
		 * 
		 * choicebox.getItems().add("1600"); choicebox.getItems().add("1700");
		 * choicebox.getItems().add("1800"); choicebox.getItems().add("1900");
		 */

	}
	//user ȭ�� ���� �޼���
	// ----------------user_Main �޼���
	
	public void btuserLogoff() { //�α׿���
		try { // admin â ����
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("login_main.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			Stage cw = (Stage) btuserLogoff.getScene().getWindow();
			cw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	public void btGoOffice() {//��ٹ�ư
		startDb();
		
		try {
			pstmt = conn.prepareStatement("insert into time (TIMENO,TIMEID, TIMEGOOFFICE, TIMEDATE,TIMESUM,TIMENOTE) values (SEQ.nextval,?,?,SYSDATE,null,null)");
			System.out.println("1");
			pstmt.setString(1, loginName);
			System.out.println("2");
			pstmt.setString(2, (format_time4 + format_time5));
			System.out.println("3");
			rs = pstmt.executeQuery();
			//pstmt.setString(3, format_time6);
			//pstmt.setString(6, );
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("������ ����");
		} finally {
			endDb();
		}
	}
	public void btLeaveOffice() {//��ٹ�ư
		startDb();

		try {
			pstmt = conn.prepareStatement("UPDATE TIME SET TIMELEAVEOFFICE=? WHERE TIMEID = ?  AND TIMELEAVEOFFICE = ?"); 
			pstmt.setString(1, (format_time4+format_time5));
			pstmt.setString(2, loginName);
			pstmt.setString(3, "EMPTY");
			rs = pstmt.executeQuery();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("������ ����");
		} finally {
			endDb();
		}

	}


	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}
