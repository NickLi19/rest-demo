package com.demo.controller;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.demo.model.Product;

@Path("/products")
public class ProductController {
	
	static ArrayList<Product> list = new ArrayList<Product>();
	
	static {
		Product p1 = new Product(1, "Canon DSL", 299.00);
		Product p2 = new Product(2, "WD 2TB", 75.00);
		
		list.add(p1);
		list.add(p2);
	}
	
	@GET
	@Path("viewall")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Product> viewAllProducts() {
		
		return list;
	}
	
	@POST
	@Path("/create")
	public Response createProduct(Product product) {
		
		String output = "";
		ObjectMapper mapper = new ObjectMapper();
		
		list.add(product);
		
		try {
			output = mapper.writeValueAsString(product);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(201).entity(output).build();
	}
	
	@GET
	@Path("/view/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product viewById(@PathParam("id") int id) {
		
		return list.get(id - 1);
	}

}
