package com.shareshipping.utils.workflowEngine.enums;

import java.util.HashMap;

import com.google.common.collect.Maps;

public enum NodeTypeEnum {

	UNDEF(-1, "Undef"), BOOLEAN_GATEWAY_ELEMENT(0, "BooleanGateway"), GATEWAY_ELEMENT(1, "Gateway"), END_ELEMENT(2,
			"End"), START_ELEMENT(3,
					"Start"), ERROR_HANDLER_ELEMENT(4, "ErrorHandler"), USER_TASK_ELEMENT(5, "UserTask");

	private static final HashMap<Integer, NodeTypeEnum> fromIntegerValue = Maps.newHashMap();
	private static final HashMap<String, NodeTypeEnum> fromStringValue = Maps.newHashMap();

	private Integer code;
	private String value;

	static {
		for (NodeTypeEnum elem : NodeTypeEnum.values()) {
			fromIntegerValue.put(elem.code, elem);
			fromStringValue.put(elem.value, elem);
		}
	}

	private NodeTypeEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	public static NodeTypeEnum fromDisplayString(String text) {
		return fromStringValue.getOrDefault(text, UNDEF);
	}

	public static NodeTypeEnum fromIntegerString(Integer code) {
		return fromIntegerValue.getOrDefault(code, UNDEF);
	}

}
