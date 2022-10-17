package com.rest.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private long bookId;
	
	@Column(name = "book_name")
	private String bookName;
	
	@Column(name = "book_desc")
	private String bookDesc;
	
	@Column(name = "author_name")
	private String authorName;
	
	@Column(name = "isbn")
	private String isbn;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "page")
	private int page;
}
