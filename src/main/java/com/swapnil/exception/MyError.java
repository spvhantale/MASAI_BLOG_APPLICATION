package com.swapnil.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyError {

	private String message;
	private LocalDateTime localtime;
	private String description;
}
