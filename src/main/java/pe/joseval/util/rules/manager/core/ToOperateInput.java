package pe.joseval.util.rules.manager.core;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ToOperateInput {
	private Map<String, Object> spectedParams;
	private Map<String, Object> realValues;
}
