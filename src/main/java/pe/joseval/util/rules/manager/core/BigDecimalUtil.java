package pe.joseval.util.rules.manager.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BigDecimalUtil {
	
	public static BigDecimal toBigDecimal(Object from) throws NotSupportedConversionException{
		// log.debug("Object name={}",from.getClass().getName());
		BigDecimalFromType type = BigDecimalFromType.valueOf(from.getClass().getSimpleName());
		switch (type) {
		case BigDecimal:
			return (BigDecimal) from;
		case BigInteger:
			BigInteger bigIntVal = (BigInteger) from;

			return new BigDecimal(bigIntVal);
		case Double:
			Double doubleVal = (Double) from;

			return new BigDecimal(doubleVal);
		case Float:
			Float floatVal = (Float) from;

			return new BigDecimal(floatVal);
		case Integer:
			Integer intValue = (Integer) from;

			return new BigDecimal(intValue);
		default:
			throw new NotSupportedConversionException("Can't not convert from "+type.getClass() +" to BigDecimal.");

		}

	}
	
	
	public static class NotSupportedConversionException extends RuntimeException{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 5354794193236644898L;

		public NotSupportedConversionException() {
			super();
		}

		public NotSupportedConversionException(String message) {
			super(message);
		}

		public NotSupportedConversionException(String message, Throwable throwable) {
			super(message, throwable);
		}

		public NotSupportedConversionException(Throwable throwable) {
			super(throwable);
		}
	}
	
	
	@Getter
	@AllArgsConstructor
	public static enum BigDecimalFromType {
		String(new String().getClass().getName()), Double(new Double(0).getClass().getName()),
		Integer(new Integer(0).getClass().getName()), Float(new Float(0).getClass().getName()),
		BigInteger(new BigInteger("0").getClass().getName()), BigDecimal(new BigDecimal("0").getClass().getName()),
		Date(new Date().getClass().getName());

		private final String value;
	}

	
}
