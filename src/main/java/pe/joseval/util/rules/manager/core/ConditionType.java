package pe.joseval.util.rules.manager.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pe.joseval.util.rules.manager.core.ConditionCategory;


@Getter
@AllArgsConstructor
public enum ConditionType {
	TRUE(ConditionCategory.NEUTRAL," TRUE "," ( "," ) ",false,0),
	OR(ConditionCategory.LOGIC," or "," ( "," ) ",false,0), 
	AND(ConditionCategory.LOGIC," and "," ( "," ) ",false,0), 
	NOT(ConditionCategory.LOGIC," ~ "," ( "," ) ",false,0),
	LTHAN(ConditionCategory.ARITHMETIC," < "," ( "," ) ",false,1),
	GTHAN(ConditionCategory.ARITHMETIC," > "," ( "," ) ",false,1), 
	LETHAN(ConditionCategory.ARITHMETIC," <= "," ( "," ) ",false,1),
	GETHAN(ConditionCategory.ARITHMETIC," >= "," ( "," ) ",false,1), 
	BWITH(ConditionCategory.STRING," beginsWith "," ( "," ) ",false,1), 
	EWITH(ConditionCategory.STRING," endsWith "," ( "," ) ",false,1),
	MATCH(ConditionCategory.STRING," matches "," ( "," ) ",false,1), 
	NMATCH(ConditionCategory.STRING," not matches "," ( "," ) ",false,1), 
	CONTAINS(ConditionCategory.STRING," contains "," ( "," ) ",false,1),
	NCONTAINS(ConditionCategory.STRING," not contains "," ( "," ) ",false,1), 
	BETWEEN(ConditionCategory.DATE," between "," ( "," ) ",false,2), 
	NULL(ConditionCategory.NEUTRAL," is NULL "," ( "," ) ",true,1),
	NOTNULL(ConditionCategory.NEUTRAL," is not NULL "," ( "," ) ",true,1), 
	EQUALS(ConditionCategory.NEUTRAL," = "," ( "," ) ",false,1), 
	NEQUALS(ConditionCategory.NEUTRAL," != "," ( "," ) ",false,1);
	private final ConditionCategory value;
	private final String operatorSymbol;
	private final String groupBegin;
	private final String groubEnd;
	private final boolean permitNulls;
	private final int spectedParamsNumber;
}
