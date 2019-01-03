package jdbc.sample.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.sample.db.SampleTable;

public class SampleBean {


	private static int AUTO_INCREMENT = -1;
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat DATE_FORMAT_FOR_DB = new SimpleDateFormat("''yyyy-MM-dd HH:mm:ss''");

	private int id;
	private String value;
	private Date startDate;
	private Date endDate;

	public SampleBean(int id, String value, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.value = value;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public SampleBean(String value , Date startDate , Date endDate){
		this.id = AUTO_INCREMENT--;
		this.value = value;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public SampleBean clone(){
		return new SampleBean(this.id,this.value,this.startDate,this.endDate);
	}

	public int getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	@Override
	public String toString() {
		return "SampleBean [id=" + id + ", value=" + value + ", startDate="
				+ DATE_FORMAT.format(startDate) + ", endDate=" + DATE_FORMAT.format(endDate) + "]";
	}

	public String generateInsert(){
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO " + SampleTable.TABLE_NAME + " SET value = '" + value + "'");

		List<String> others = new ArrayList<String>();
		others.add(this.getIdForInsert());
		others.add(this.getStartDateForInsert());
		others.add(this.getEndDateForInsert());
		others
			.stream()
			.filter(v -> v != null)
			.forEach(values -> {
				System.out.println(values);
				sqlBuilder.append(",").append(values);
		});

		return sqlBuilder.toString();

	}

	private String getIdForInsert(){
		if(this.id > 0){
			return "id = " + this.id;
		} else {
			return null;
		}
	}

	private String getStartDateForInsert(){
		if(this.startDate != null){
			return "start_date = " + DATE_FORMAT_FOR_DB.format(startDate);
		} else {
			return null;
		}
	}

	private String getEndDateForInsert(){
		if(this.endDate != null){
			return "end_date = " + DATE_FORMAT_FOR_DB.format(endDate);
		} else {
			return null;
		}
	}

}
