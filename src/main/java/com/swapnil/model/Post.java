package com.swapnil.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	@Size(min = 2 ,message = "title character should be greater than 2")
	private String title;
	@Size(min = 10 ,message = "description character should be greater than 10")
	private String description;
	private LocalDate createdDate;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> comments=new ArrayList<>();
	
}
