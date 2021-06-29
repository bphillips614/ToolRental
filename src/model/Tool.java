package model;

public class Tool {

	private String code;
	private String type;
	private String brand;
	private double dailyCharge;
	private boolean weekendCharge;
	private boolean holidayCharge;
	
	public Tool(String code) {
		this.code = code;
		switch (code) {
			case "LADW": {
				type = "Ladder";
				brand = "Werner";
				dailyCharge = 1.99;
				weekendCharge = true;
				holidayCharge = false;
				break;
			}
			case "CHNS": {
				type = "Chainsaw";
				brand = "Stihl";
				dailyCharge = 1.49;
				weekendCharge = false;
				holidayCharge = true;
				break;
			}
			case "JAKR": {
				type = "Jackhammer";
				brand = "Ridgid";
				dailyCharge = 2.99;
				weekendCharge = false;
				holidayCharge = false;
				break;
			}
			case "JAKD": {
				type = "Jackhammer";
				brand = "DeWalt";
				dailyCharge = 2.99;
				weekendCharge = false;
				holidayCharge = false;
			}
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public double getDailyCharge() {
		return dailyCharge;
	}

	public void setDailyCharge(double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}
	
	public boolean isWeekendCharge() {
		return weekendCharge;
	}

	public void setWeekendCharge(boolean weekendCharge) {
		this.weekendCharge = weekendCharge;
	}

	public boolean isHolidayCharge() {
		return holidayCharge;
	}

	public void setHolidayCharge(boolean holidayCharge) {
		this.holidayCharge = holidayCharge;
	}
}
