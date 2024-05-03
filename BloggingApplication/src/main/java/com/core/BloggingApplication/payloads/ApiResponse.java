package com.core.BloggingApplication.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
String msg;
boolean status;

public ApiResponse(String msg, boolean status) {
	super();
	this.msg = msg;
	this.status = status;
}


}
