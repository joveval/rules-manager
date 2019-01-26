package pe.joseval.util.rules.manager.core;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;
import lombok.Builder.Default;
import static pe.joseval.util.rules.manager.core.BigDecimalUtil.*;

@Slf4j
@Data
@AllArgsConstructor
@Builder(builderClassName = "Builder", builderMethodName = "builder")
public class Condition {

	private String spectedParamName;
	@Singular
	private List<Object> spectedValues;
	private Condition[] childrenConditions;
	private ConditionType condition;
	@Default
	private String stringResult = "First run and then print these variable";

	private void validate(Map<String, Object> factParams) throws ConditionValidationException {
		if (spectedParamName == null) {
				throw new ConditionValidationException("spectedParamName must not be null.");
		}
		if (!(condition.isPermitNulls())) {

			for (int i = 0; i < condition.getSpectedParamsNumber(); i++) {
				if (spectedValues.get(i) == null) {
					throw new ConditionValidationException("number of parameters must be "
							+ condition.getSpectedParamsNumber() + " in conditionType " + condition.name());
				}
			}

			if (factParams.get(spectedParamName) == null) {
				throw new ConditionValidationException("spectedParamName must be present in factParams");
			}

		}
	}

	public Boolean runValidation(Map<String, Object> factParams) throws ConditionValidationException {
		String conditionString = "";
		boolean response = false;
		validate(factParams);
		switch (condition.getValue()) {
		case ARITHMETIC:
			BigDecimal spectedNumber = null;
			BigDecimal actualNumber = null;
			try {
				spectedNumber = toBigDecimal(spectedValues.get(0));
				actualNumber = toBigDecimal(factParams.get(spectedParamName));
			} catch (NotSupportedConversionException e) {
				log.error("[ERROR]", e);
				return false;
			} catch (NullPointerException e) {
				log.error("[ERROR]", e);
				return false;
			}
			int comparitionVal = actualNumber.compareTo(spectedNumber);
			switch (condition) {
			case GETHAN:
				response = comparitionVal >= 0;
				break;
			case GTHAN:
				response = comparitionVal > 0;
				break;
			case LETHAN:
				response = comparitionVal <= 0;
				break;
			case LTHAN:
				response = comparitionVal < 0;
				break;
			default:
				break;
			}
			conditionString = spectedParamName + "[" + actualNumber + "]" + condition.getOperatorSymbol()
					+ spectedNumber;
			break;
		case DATE:

			switch (condition) {
			case BETWEEN:

				try {

					Date spectedDateBegin = (Date) spectedValues.get(0);
					Date spectedDateEnd = (Date) spectedValues.get(1);
					Date actualDate = (Date) factParams.get(spectedParamName);
					response = actualDate.after(spectedDateEnd) && actualDate.before(spectedDateBegin);
					conditionString = spectedParamName + "[" + actualDate + "]" + condition.getOperatorSymbol()
							+ spectedDateBegin + " and " + spectedDateEnd;
				} catch (Exception e) {
					log.error("[ERROR]:", e);
					return null;
				}

				break;
			default:
			}

			break;
		case LOGIC:

			switch (condition) {
			case OR:
				response = false;
				for (int i = 0; i < childrenConditions.length; ++i) {
					Condition subCondition = childrenConditions[i];
					response |= subCondition.runValidation(factParams);
					conditionString += i < childrenConditions.length - 1 ? condition.getGroupBegin()
							+ subCondition.getStringResult() + condition.getGroubEnd() + condition.getOperatorSymbol()
							: "( " + subCondition.getStringResult() + " )";
				}
				break;
			case AND:
				response = true;
				for (int i = 0; i < childrenConditions.length; ++i) {
					Condition subCondition = childrenConditions[i];
					response &= subCondition.runValidation(factParams);
					conditionString += i < childrenConditions.length - 1 ? condition.getGroupBegin()
							+ subCondition.getStringResult() + condition.getGroubEnd() + condition.getOperatorSymbol()
							: "( " + subCondition.getStringResult() + " )";
				}
				break;
			case NOT:
				Condition subCondition = childrenConditions[0];
				response = !subCondition.runValidation(factParams);
				conditionString += condition.getOperatorSymbol() + condition.getGroupBegin()
						+ subCondition.getStringResult() + condition.getGroubEnd();
				break;
			default:
				log.warn("It's possible the existence of a bug problem here.");
			}

			break;
		case NEUTRAL:

			Object actualNeutral = factParams.get(spectedParamName);
			Object spectedNeutral = null;
			switch (condition) {
			case EQUALS:
				spectedNeutral = spectedValues.get(0);
				response = actualNeutral.equals(spectedNeutral);
				break;
			case NEQUALS:
				spectedNeutral = spectedValues.get(0);
				response = !actualNeutral.equals(spectedNeutral);
				break;
			case NOTNULL:
				response = actualNeutral != null;
				break;
			case NULL:
				response = actualNeutral == null;
				break;
			default:
				System.out.println("It's possible the existence of a bug problem here.");
				break;

			}
			if (spectedNeutral != null) {
				conditionString = spectedParamName + "[" + actualNeutral + "]" + condition.getOperatorSymbol()
						+ spectedNeutral.toString();
			} else {
				conditionString = spectedParamName + "[" + actualNeutral + "]" + condition.getOperatorSymbol();
			}
			break;
		case STRING:
			String spectedString = (String) spectedValues.get(0);
			String factString = (String) factParams.get(spectedParamName);
			switch (condition) {
			case BWITH:
				response = factString.startsWith(spectedString);
				break;
			case CONTAINS:
				response = factString.contains(spectedString);
				break;
			case EWITH:
				response = factString.endsWith(spectedString);
				break;
			case MATCH:
				response = factString.matches(spectedString);
				break;
			case NCONTAINS:
				response = !factString.contains(spectedString);
				break;
			case NMATCH:
				response = !factString.matches(spectedString);
				break;
			default:
				System.out.println("It's possible the existence of a bug problem here.");
				break;

			}
			conditionString = spectedParamName + "[" + factString + "]" + condition.getOperatorSymbol() + spectedString;
			break;
		default:
			log.warn("It's possible the existence of a bug here.");
			break;

		}
		stringResult = conditionString;
		return response;
	}

}
