package jdbc.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.sample.bean.SampleBean;
import jdbc.sample.db.SampleTable;
import jdbc.sample.db.ServerSetting;


public class SelectMain {

	public static void main(String[] args) throws SQLException {

		String url = "jdbc:mysql://" + ServerSetting.HOST_NAME + "/" + ServerSetting.DATABASE_NAME;
		Connection con = null;
		con = DriverManager.getConnection(url , ServerSetting.USER , ServerSetting.PASSWORD);

		Statement state = con.createStatement();
		String sql = "select * from " + SampleTable.TABLE_NAME + " where id > 1";
		ResultSet result = state.executeQuery(sql);

		while(result.next()){
			int index = 0;
			SampleBean bean
				= new SampleBean(
						result.getInt(SampleTable.COLUMN_NAMES[index++]),
						result.getString(SampleTable.COLUMN_NAMES[index++]),
						result.getDate(SampleTable.COLUMN_NAMES[index++]),
						result.getDate(SampleTable.COLUMN_NAMES[index++])
						);

			System.out.println(bean.toString());
		}


		result.close();
		state.close();
		con.close();
	}
}
