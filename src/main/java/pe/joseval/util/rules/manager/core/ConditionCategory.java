package pe.joseval.util.rules.manager.core;

public enum ConditionCategory {
	LOGIC, // Or,And,Xor,Xand,Not
	ARITHMETIC, // LessThan,GreaterThan,LessThanOrEq,GreaterThanOrEq
	STRING, // BeginsWith,EndsWith,Contains,NotContains,Match,NoMatch
	DATE, // Between
	NEUTRAL // Equals,NotEquals,Null,NotNull
}
