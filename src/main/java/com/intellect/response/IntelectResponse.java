package com.intellect.response;

import java.util.ArrayList;
import java.util.List;

public class IntelectResponse {

	
	private Long userId;
	
	private String resMsg;
	
	private List<ValidationError> valErrors = new ArrayList<ValidationError>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public List<ValidationError> getValErrors() {
		return valErrors;
	}

	public void setValErrors(List<ValidationError> valErrors) {
		this.valErrors = valErrors;
	}
	
	
}
