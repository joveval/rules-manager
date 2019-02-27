# Rules manager

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c796636d6fde4031834c68692c23983b)](https://app.codacy.com/app/joveval/rules-manager?utm_source=github.com&utm_medium=referral&utm_content=joveval/rules-manager&utm_campaign=Badge_Grade_Dashboard)

Rules manager is a java package to define a rule and validate it through the comparison of expected against actual values.

## Installation
Clone this project in your PC. Then install it using Maven:

``` batch
./mvn install
```
## POM reference

``` xml
<dependency>
	<groupId>io.github.joveval.util</groupId>
	<artifactId>RulesManager</artifactId>
	<version>1.0.0-alpha</version>
</dependency>
```
## Use
You could define a rule as a tree of logic, arithmetic, string and neutral comparisons:

*	Define rule:

``` java
Condition condition = lOr( isNull( "p" ), notNull( "x" ) );
```
*	Populate a map of actual values:

``` java
Map<String, Object> factParams = new HashMap<>();
factParams.put("p", "1");
factParams.put("x", null);
```
*	Validate condition against actual values:

``` java
try {
	res = condition.runValidation(factParams);
} catch (ConditionValidationException e) {
	//
}
```

## License
MIT - See [LICENSE](https://github.com/joveval/rules-manager/blob/master/LICENSE) for more details.
