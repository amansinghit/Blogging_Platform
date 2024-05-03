package com.core.BloggingApplication.payloads;

import java.util.Date;

import com.core.BloggingApplication.entity.Category;
import com.core.BloggingApplication.entity.User;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
	private int post_id;
private String title;
private String content;

private String imageName;
private Date  addedDate;

private CategoryDTO category;
private UserDTO user;


}
