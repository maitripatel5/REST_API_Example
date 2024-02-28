package com.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.demo.model.Book;

@Path("/books")
public class BookController {
	
	static List<Book> books = new ArrayList<>(
			Arrays.asList(
					new Book(1, "Core Java", "Herbert Schildt", 24.99),
					new Book(2, "Microservices", "Johny Depp", 11.90),
					new Book(3, "Data Analytics", "Will Smith", 28.24),
					new Book(4, "AI ML", "Just Chill", 49.50)
			)
			
	);
	
	@GET
	@Path("viewall")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> viewAllBooks() {
		
		return books;
	}
	
	@GET
	@Path("/find/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book findById(@PathParam("id") String id) {
		
		return books.get(Integer.parseInt(id) - 1);
		
	}
	
	@POST
	@Path("/create")
	public Response createBook(Book book) {
		
		String output = "";
		ObjectMapper mapper = new ObjectMapper();
		books.add(book);
		
		try {
			output = mapper.writeValueAsString(book);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(201).entity(output).build();
	}

}
