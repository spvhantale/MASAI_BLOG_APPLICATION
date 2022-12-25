package com.swapnil.dto;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import com.swapnil.model.Comment;
import com.swapnil.model.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	
	private String title;
	private String decription;
	
	private LocalDate createdDate;
}
