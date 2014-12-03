package apps.chomars.pengeluaranmu;

public class DateFormat {
	
	public static String DateFormat(String Type,String date){
		String[] fulldate = date.split(" ");
		
		String[] datesplit = fulldate[0].split("-");
		String month = null;
		String ShortMonth = null;
		String DateFormatted = null;
		String ShortYear = datesplit[0].substring(2,4);
		String Year = datesplit[0];
		switch(datesplit[1]){
		case "01": month = "Januari";
		ShortMonth= "Jan";
		break;
		case "02": month = "Februari";
		ShortMonth = "Feb";
		break;
		case "03": month = "Maret";
		ShortMonth = "Mar";
		break;
		case "04": month = "April";
		ShortMonth = "Apr";
		break;
		case "05": month = "Mei";
		ShortMonth = "Mei";
		break;
		case "06": month = "Juni";
		ShortMonth = "Jun";
		break;
		case "07": month = "July";
		ShortMonth = "Jul";
		break;
		case "08": month = "Agustus";
		ShortMonth = "Agt";
		break;
		case "09": month = "September";
		ShortMonth = "Sep";
		break;
		case "10": month = "Oktober";
		ShortMonth = "Okt";
		break;
		case "11": month = "November";
		ShortMonth = "Nov";
		break;
		case "12": month = "Desember";
		ShortMonth = "Des";
		break;
		
		}
		switch(Type){
		case "1" : DateFormatted = datesplit[2]+" "+month+" "+Year;
		break;
		case "2" : DateFormatted = month+" "+datesplit[0];
		break;
		case "3" : DateFormatted = ShortMonth+" "+Year;
		break;
		case "4" : DateFormatted = datesplit[2]+" "+ShortMonth+" "+Year;
		break;
		case "5" : DateFormatted = datesplit[2]+" "+ShortMonth+" "+ShortYear ;
		}
		

		
		return DateFormatted;
	}
}
