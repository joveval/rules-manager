package pe.joseval.util.rules.manager.core;

import java.util.Date;

public class StaticConditions {
	public static Condition Or(Condition... operators) {
		return logicOperator(ConditionType.OR, operators);
	}

	public static Condition And(Condition... operators) {

		return logicOperator(ConditionType.AND, operators);
	}

	public static Condition Not(Condition operator) {

		return logicOperator(ConditionType.NOT, operator);
	}

	public static Condition Between(String paramName, Date dateBefore, Date dateAfter) {
		return new Condition.Builder().condition(ConditionType.BETWEEN).spectedParamName(paramName)
				.spectedValue(dateAfter).spectedValue(dateBefore).build();
	}

	public static Condition Equals(String paramName, Object espectedValue) {
		return neutralBinaryOperator(ConditionType.EQUALS, paramName, espectedValue);
	}

	public static Condition NotEquals(String paramName, Object espectedValue) {
		return neutralBinaryOperator(ConditionType.NEQUALS, paramName, espectedValue);
	}

	public static Condition LessThan(String paramName, Object espectedValue) {
		return arithmeticOperator(ConditionType.LTHAN, paramName, espectedValue);
	}

	public static Condition LessOrEqualThan(String paramName, Object espectedValue) {
		return arithmeticOperator(ConditionType.LETHAN, paramName, espectedValue);
	}

	public static Condition GreaterThan(String paramName, Object espectedValue) {
		return arithmeticOperator(ConditionType.GTHAN, paramName, espectedValue);
	}

	public static Condition GreaterOrEqualThan(String paramName, Object espectedValue) {
		return arithmeticOperator(ConditionType.GETHAN, paramName, espectedValue);
	}

	public static Condition BeginsWith(String paramName, String spectedValue) {
		return stringOperator(ConditionType.BWITH, paramName, spectedValue);
	}

	public static Condition EndsWith(String paramName, String spectedValue) {
		return stringOperator(ConditionType.EWITH, paramName, spectedValue);
	}

	public static Condition Match(String paramName, String spectedValue) {
		return stringOperator(ConditionType.MATCH, paramName, spectedValue);
	}

	public static Condition NoMatch(String paramName, String spectedValue) {
		return stringOperator(ConditionType.NMATCH, paramName, spectedValue);
	}

	public static Condition Contains(String paramName, String spectedValue) {
		return stringOperator(ConditionType.CONTAINS, paramName, spectedValue);
	}

	public static Condition NotContains(String paramName, String spectedValue) {
		return stringOperator(ConditionType.NCONTAINS, paramName, spectedValue);
	}

	public static Condition IsNull(String paramName) {
		return neutralMonoOperator(ConditionType.NULL, paramName);
	}

	public static Condition NotNull(String paramName) {
		return neutralMonoOperator(ConditionType.NOTNULL, paramName);
	}

	private static Condition neutralMonoOperator(ConditionType operationType, String paramName) {
		return new Condition.Builder().condition(operationType).spectedParamName(paramName).build();
	}

	private static Condition neutralBinaryOperator(ConditionType operationType, String paramName, Object spectedValue) {
		return new Condition.Builder().condition(operationType).spectedParamName(paramName).spectedValue(spectedValue)
				.build();
	}

	private static Condition stringOperator(ConditionType operationType, String paramName, String operand) {
		return new Condition.Builder().condition(operationType).spectedParamName(paramName).spectedValue(operand)
				.build();
	}

	private static Condition arithmeticOperator(ConditionType operationType, String paramName, Object operand) {
		return new Condition.Builder().condition(operationType).spectedParamName(paramName).spectedValue(operand)
				.build();
	}

	private static Condition logicOperator(ConditionType operationType, Condition... operators) {
		return new Condition.Builder().childrenConditions(operators).condition(operationType).build();
	}

	private static Condition logicOperator(ConditionType operationType, Condition operator) {
		return logicOperator(operationType, new Condition[] { operator });
	}
}
