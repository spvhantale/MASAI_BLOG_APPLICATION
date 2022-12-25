package com.swapnil.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer commentId;
	@Size(min = 2 ,message = "title character should be greater than 2")
	private String title;
	@Size(min = 10 ,message = "description character should be greater than 10")
	private String decription;
	
	private LocalDate createdDate;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Post post;
}
