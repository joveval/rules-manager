package pe.joseval.util.rules.manager.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static pe.joseval.util.rules.manager.core.StaticConditions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class RulesBuildingTest {

	private Map<String, Object> factParams = new HashMap<>();

	public RulesBuildingTest() throws ParseException {
		// TODO Auto-generated constructor stub
		/*All objects*/
		factParams.put("a", "1");
		factParams.put("b", null);
		/*Numeric values*/
		factParams.put("d", 30);
		factParams.put("e", new BigInteger("2"));
		factParams.put("f", new BigDecimal(2));
		factParams.put("g", 30/2.0);
		factParams.put("h", new Integer(20));
		factParams.put("i", new Double(20.0));
		/*String values*/
		factParams.put("j", "hola");
		factParams.put("k", "asdflkjadlf jljfalsdfja");
		factParams.put("l", "asdf asddfiui4iiorepqwurperr");
		factParams.put("m", "123");
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("21/12/2019");
		/*Dates*/
		factParams.put("n",d );
		
	}

	/* Test to validate correct number of parameters */
	@Test
	public void testAThowingCorrectErrors() {

		Condition[] conditions = new Condition[] { lEquals("a", null), lNotEquals("a", null), beginsWith("a", null),
				endsWith("a", null), greaterOrEqualThan("a", null), greaterThan("a", null),match("b", "\n") };

		for (Condition condition : conditions) {
			boolean res=false;
			try {
				res = condition.runValidation(factParams);
			} catch (ConditionValidationException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}

			assertFalse(res);
		}

	}
	
	@Test
	public void testBCorrectNumberOperations() {
		Condition[] conditions = new Condition[] { lessThan("d", 40), lessOrEqualThan("e", 2), greaterThan("f", 1),
				greaterOrEqualThan("g", 15) };
		
		for (Condition condition : conditions) {
			boolean res=false;
			try {
				res = condition.runValidation(factParams);
			} catch (ConditionValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			assertTrue(res);
		}
	}
	
	
	@Test
	public void testBCorrectStringOperations() {
		Condition[] conditions = new Condition[] { beginsWith("j", "ho"), endsWith("k", "dfja"),contains("l", "pe"),notContains("l", "1"),match("m", "\\d{2,3}"),noMatch("m","\\d{1}" ) };
		
		for (Condition condition : conditions) {
			boolean res=false;
			try {
				res = condition.runValidation(factParams);
			} catch (ConditionValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			assertTrue(res);
		}
	}

	
	
	@Test
	public void testDDatesComparition() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dx = sdf.parse("21/12/2019");
		Date d1 = sdf.parse("20/12/2019");
		Date d2 = sdf.parse("24/12/2019");
		
		Condition[] conditions = new Condition[] { between("n", d1,d2),lEquals("n",dx) };
		
		for (Condition condition : conditions) {
			boolean res=false;
			try {
				res = condition.runValidation(factParams);
			} catch (ConditionValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			assertTrue(res);
		}
	}
	
	@Test
	public void testDTrueTest() {
		Condition condition = lTrue();
		boolean res = false;
		try {
			res = condition.runValidation(factParams);
		} catch (ConditionValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(res);
	}
	
	@Test
	public void testELogicTest() {
		Condition condition = lAnd(lTrue(),lNot(lTrue()));
		boolean res = true;
		try {
			res = condition.runValidation(factParams);
		} catch (ConditionValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(res);
	}
	
	@Test
	public void testDNULLs() {
		//Object o1,o2=new String();
		Condition condition = lOr(isNull("p"),notNull("x"));
		boolean res = false;
		try {
			res = condition.runValidation(factParams);
		} catch (ConditionValidationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//res = false;
			System.out.println("NULLS issue"+e.getMessage());
		}
		assertTrue(res);
	}
	
	
	
}
