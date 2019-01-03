package jdbc.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import jdbc.sample.bean.SampleBean;
import jdbc.sample.db.ServerSetting;

public class InsertMain {

	public static void main(String[] args) throws SQLException{

		String url = "jdbc:mysql://" + ServerSetting.HOST_NAME + "/" + ServerSetting.DATABASE_NAME;
		Connection con = null;
		con = DriverManager.getConnection(url , ServerSetting.USER , ServerSetting.PASSWORD);

		Statement state = con.createStatement();

		String insert = new SampleBean("hogehoge" , new Date() , null).generateInsert();
		int count = state.executeUpdate(insert);
		System.out.println(count + "件登録。");
	}
}
