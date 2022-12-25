package com.swapnil.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.swapnil.model.Comment;
import com.swapnil.model.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {


	private String title;
	
	private String description;
	private LocalDate createdDate;
	private List<CommentDTO> comments=new ArrayList<>();
}
