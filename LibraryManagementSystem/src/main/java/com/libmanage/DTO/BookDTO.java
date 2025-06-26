package com.libmanage.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BookDTO {
	private String titile;
	private String author;
	private String isbn;
	private Integer quantity;
	private boolean available;	
	
}
