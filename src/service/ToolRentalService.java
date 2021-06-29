package service;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import model.Tool;
import util.ToolsUtil;

public class ToolRentalService {

	public String generateRentalAgreement(String code, Long checkoutDate, int rentalDays, double discount) {
		//validate
		if (discount < 0 || discount > 1) {
			return "{message: 'Invalid Discount'}";
		}
		else if (rentalDays <= 1) {
			return "{message: 'Invalid Rental Period'}";
		}
		
		Tool tool = new Tool(code);
		Long dueDate = checkoutDate + (rentalDays * ToolsUtil.DAY_MILLIS);
		int chargeDays = getChargeDays(tool, checkoutDate, rentalDays);
		double preDiscountCharge = chargeDays * tool.getDailyCharge();
		double discountAmount = (Math.floor((preDiscountCharge * discount) * 100)) / 100;
		System.out.println("preDiscountCharge = " + preDiscountCharge);
		System.out.println("discountAmount = " + discountAmount);
		System.out.println(preDiscountCharge - discountAmount);
		double finalCharge = Math.round((preDiscountCharge - discountAmount) * 100.0) / 100.0;
		
		JSONObject json = new JSONObject();
		json.put("code", tool.getCode());
		json.put("type", tool.getType());
		json.put("brand", tool.getBrand());
		json.put("rental_days", rentalDays);
		json.put("checkout_date", checkoutDate);
		json.put("due_date", dueDate);
		json.put("daily_charge", tool.getDailyCharge());
		json.put("charge_days", chargeDays);
		json.put("pre_discount_charge", preDiscountCharge);
		json.put("discount", discount);
		json.put("discount_amount", discountAmount);
		json.put("final_charge", finalCharge);
		
		return json.toString();
	}
	
	private int getChargeDays(Tool tool, Long checkoutDate, int rentalDays) {
		Long day = checkoutDate;
		Calendar cal = Calendar.getInstance();
		int chargeDays = 0;
		for (int i = 0; i < rentalDays; i++) {
			cal.setTime(new Date(day));
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			boolean isWeekend = dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
			boolean isHoliday = getIsHoliday(cal, isWeekend);
			if ((!isWeekend && !isHoliday) ||
				(isWeekend && tool.isWeekendCharge()) ||
				(isHoliday && tool.isHolidayCharge())) {
				//day is a charge day
				chargeDays++;
			}
			day += ToolsUtil.DAY_MILLIS;
		}
		return chargeDays;
	}
	
	private boolean getIsHoliday(Calendar cal, boolean isWeekend) {
		int month = cal.get(Calendar.MONTH);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		if (month == Calendar.SEPTEMBER && dayOfWeek == Calendar.MONDAY && dayOfMonth <= 7) {
			//Labor Day
			return true;
		}
		
		else if ((month == Calendar.JULY && dayOfMonth == 4 && !isWeekend) ||
				(month == Calendar.JULY && dayOfMonth == 3 && dayOfWeek == Calendar.FRIDAY) ||
				(month == Calendar.JULY && dayOfMonth == 5 && dayOfWeek == Calendar.MONDAY)) {
			//Fourth of July observed
			return true;
		}
		return false;
	}
	
	//handles double math roundoff error
	private double getFinalCharge(double preDiscountCharge, double discountAmount) {
		return 0.0;
	}
}
