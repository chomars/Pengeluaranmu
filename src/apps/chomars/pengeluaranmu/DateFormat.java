package apps.chomars.pengeluaranmu;

public class DateFormat {
	
	public static String DateFormat(String Type,String date){
		String[] fulldate = date.split(" ");
		
		String[] datesplit = fulldate[0].split("-");
		String month = null;
		String DateFormatted = null;
		switch(datesplit[1]){
		case "01": month = "Januari";
		break;
		case "02": month = "Februari";
		break;
		case "03": month = "Maret";
		break;
		case "04": month = "April";
		break;
		case "05": month = "Mei";
		break;
		case "06": month = "Juni";
		break;
		case "07": month = "July";
		break;
		case "08": month = "Agustus";
		break;
		case "09": month = "September";
		break;
		case "10": month = "Oktober";
		break;
		case "11": month = "November";
		break;
		case "12": month = "Desember";
		break;
		
		}
		switch(Type){
		case "1" : DateFormatted = datesplit[2]+" "+month+" "+datesplit[0];
		break;
		case "2" : DateFormatted = month+" "+datesplit[0];
		break;
		}
		

		
		return DateFormatted;
	}
}
