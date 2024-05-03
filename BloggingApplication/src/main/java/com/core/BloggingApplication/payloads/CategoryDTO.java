package com.core.BloggingApplication.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDTO {
	
	private int categoryId;
	@NotEmpty(message="Title Necessary")
	private String categoryTitle;
	@NotEmpty(message="Description Necessary")
	private String categoryDescription;
	
}
