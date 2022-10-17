package com.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
	private String bookName;
	private String bookDesc;
	private String authorName;
	private String isbn;
	private double price;
	private int page;
}
