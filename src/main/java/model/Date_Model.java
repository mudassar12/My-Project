package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Date_Model {
	public Date_Model(String StartDate, String EndDate) throws ParseException  {
	
		 DateFormat formatter = new SimpleDateFormat("dd MMM yyyy"); 
		  this.start_date = (Date)formatter.parse(StartDate);
		  this.End_date=(Date)formatter.parse(EndDate);
		 //SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public Date_Model() {
		// TODO Auto-generated constructor stub
	}
	Date start_date;
	Date End_date;
	//String sdate;
	/**
	 * @return the sdate
	 */
//	public String getSdate() {
//		return sdate;
//	}
//	/**
//	 * @param sdate the sdate to set
//	 */
//	public void setSdate(String sdate) {
//		this.sdate = sdate;
//	}
	/**
	 * @return the edate
	 */
	
	public Date getEnd_date() {
		return End_date;
	}
	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(Date end_date) {
		End_date = end_date;
	}
	/**
	 * @return the start_date
	 */
	public Date getStart_date() {
		return start_date;
	}
	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	

}
