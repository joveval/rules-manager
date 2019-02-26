# Rules manager
Rules manager is a java package to define a rule and validate it through the comparison of expected against actual values.

## Installation
Clone this project in your PC. Then install it using Maven:
```
./mvn install
```

## Use
You could define a rule as a tree of logic, arithmetic, string and neutral comparisons:

* Define rule:

```
Condition condition = Or( IsNull("p"),
								 NotNull("x") );
```
* Populate a map of actual values:

```
Map<String, Object> factParams = new HashMap<>();
factParams.put("p", "1");
factParams.put("x", null);
```
* Validate condition against actual values:

```
try {
	res = condition.runValidation(factParams);
} catch (ConditionValidationException e) {
	//
}
```