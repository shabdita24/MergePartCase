package com.mergepc.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	public static ResponseEntity<Object> response(HttpStatus httpStatus, Boolean isError, String message,
			Object responseObject) {
		Map<String, Object> map = new HashMap<>();
		map.put("timestamp", new Date().getTime());
		map.put("status", httpStatus.value());
		map.put("isError", isError);
		map.put("message", message);
		map.put("responseObject", responseObject);
		return new ResponseEntity<>(map, httpStatus);

	}

	public static ResponseEntity<Object> response(HttpStatus httpStatus, Boolean isError, String message) {
		Map<String, Object> map = new HashMap<>();
		map.put("timestamp", new Date().getTime());
		map.put("status", httpStatus.value());
		map.put("isError", isError);
		map.put("message", message);
		return new ResponseEntity<>(map, httpStatus);

	}

}
