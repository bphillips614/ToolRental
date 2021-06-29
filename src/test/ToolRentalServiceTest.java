package test;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import service.ToolRentalService;

public class ToolRentalServiceTest {

    @Test  
    public void generateRentalAgreement_test1() {
    	ToolRentalService trs = new ToolRentalService();
    	String rentalAgreement = trs.generateRentalAgreement("JAKR", 1441252800000L, 5, 1.01);
    	
        assertEquals("{message: 'Invalid Discount'}", rentalAgreement);
    }  	
	
    @Test 
    public void generateRentalAgreement_test2() {
    	ToolRentalService trs = new ToolRentalService();
    	JSONObject rentalAgreement = new JSONObject(trs.generateRentalAgreement("LADW", 1593662400000L, 3, 0.1));
    	
    	assertEquals("Werner", rentalAgreement.get("brand"));
    	assertEquals(2, rentalAgreement.get("charge_days"));
    	assertEquals(1.99, rentalAgreement.get("daily_charge"));
    	assertEquals(0.39, rentalAgreement.get("discount_amount"));
    	assertEquals(1593921600000L, rentalAgreement.get("due_date"));
    	assertEquals(3.59, rentalAgreement.get("final_charge"));
    	assertEquals(3.98, rentalAgreement.get("pre_discount_charge"));
    	assertEquals("Ladder", rentalAgreement.get("type"));
    }
    
    @Test 
    public void generateRentalAgreement_test3() {
    	ToolRentalService trs = new ToolRentalService();
    	JSONObject rentalAgreement = new JSONObject(trs.generateRentalAgreement("CHNS", 1435809600000L, 5, 0.25));
    	
    	assertEquals("Stihl", rentalAgreement.get("brand"));
    	assertEquals(3, rentalAgreement.get("charge_days"));
    	assertEquals(1.49, rentalAgreement.get("daily_charge"));
    	assertEquals(1.11, rentalAgreement.get("discount_amount"));
    	assertEquals(1436241600000L, rentalAgreement.get("due_date"));
    	assertEquals(4.47, rentalAgreement.get("pre_discount_charge"));
    	assertEquals("Chainsaw", rentalAgreement.get("type"));
    	assertEquals(3.36, rentalAgreement.get("final_charge"));
    }
    
    @Test
    public void generateRentalAgreement_test4() {
    	ToolRentalService trs = new ToolRentalService();
    	JSONObject rentalAgreement = new JSONObject(trs.generateRentalAgreement("JAKD", 1441252800000L, 6, 0));
    	
    	assertEquals("DeWalt", rentalAgreement.get("brand"));
    	assertEquals(3, rentalAgreement.get("charge_days"));
    	assertEquals(2.99, rentalAgreement.get("daily_charge"));
    	assertEquals(0, rentalAgreement.get("discount_amount"));
    	assertEquals(1441771200000L, rentalAgreement.get("due_date"));
    	assertEquals(8.97, rentalAgreement.get("pre_discount_charge"));
    	assertEquals("Jackhammer", rentalAgreement.get("type"));
    	assertEquals(8.97, rentalAgreement.get("final_charge"));
    }
    
    @Test
    public void generateRentalAgreement_test5() {
    	ToolRentalService trs = new ToolRentalService();
    	JSONObject rentalAgreement = new JSONObject(trs.generateRentalAgreement("JAKR", 1435809600000L, 9, 0));
    	
    	assertEquals("Ridgid", rentalAgreement.get("brand"));
    	assertEquals(6, rentalAgreement.get("charge_days"));
    	assertEquals(2.99, rentalAgreement.get("daily_charge"));
    	assertEquals(0, rentalAgreement.get("discount_amount"));
    	assertEquals(1436587200000L, rentalAgreement.get("due_date"));
    	assertEquals(17.94, rentalAgreement.get("pre_discount_charge"));
    	assertEquals("Jackhammer", rentalAgreement.get("type"));
    	assertEquals(17.94, rentalAgreement.get("final_charge"));
    }
    
    @Test
    public void generateRentalAgreement_test6() {
    	ToolRentalService trs = new ToolRentalService();
    	JSONObject rentalAgreement = new JSONObject(trs.generateRentalAgreement("JAKR", 1593662400000L, 4, .50));
    	
    	assertEquals("Ridgid", rentalAgreement.get("brand"));
    	assertEquals(1, rentalAgreement.get("charge_days"));
    	assertEquals(2.99, rentalAgreement.get("daily_charge"));
    	assertEquals(1.49, rentalAgreement.get("discount_amount"));
    	assertEquals(1594008000000L, rentalAgreement.get("due_date"));
    	assertEquals(2.99, rentalAgreement.get("pre_discount_charge"));
    	assertEquals("Jackhammer", rentalAgreement.get("type"));
    	assertEquals(1.50, rentalAgreement.get("final_charge"));    	
    }
}
