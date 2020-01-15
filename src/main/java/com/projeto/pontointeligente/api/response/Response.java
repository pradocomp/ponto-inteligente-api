package com.projeto.pontointeligente.api.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response<T> {
	
	private T data;
	private List<String> errors;
	
	public List<String> getErrors() {
		if (this.errors == null) {
			return this.errors = new ArrayList<String>();
		}
		return this.errors;
	}

}
