package com.swapnil.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer userId;
	private String uuid;
	private LocalDateTime datetime;
	public CurrentUserSession(Integer userId, String uUid, LocalDateTime datetime) {
		super();
		this.userId = userId;
		this.uuid = uUid;
		this.datetime = datetime;
	}
	
}
