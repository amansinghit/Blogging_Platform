package com.core.BloggingApplication.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
String resourceName;
String feildName;
long feildvalue;
//Service Exception handler
public ResourceNotFoundException(String resourceName,String feildName,long feildvalue) {
	super(String.format("%s not found with---- %s:%s", resourceName, feildName, feildvalue));
	this.feildName= feildName;
	this.resourceName=resourceName;
	this.feildvalue=feildvalue;
}

	
}
