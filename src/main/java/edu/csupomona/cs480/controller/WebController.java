package edu.csupomona.cs480.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;

import org.h2.Driver;
import java.sql.*;


/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;

	/**
	 * This is a simple example of how the HTTP API works.
	 * It returns a String "OK" in the HTTP response.
	 * To try it, run the web application locally,
	 * in your web browser, type the link:
	 * 	http://localhost:8080/cs480/ping
	 */
	@RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "OK";
	}
	
	//Pedro's 1st method (A3)
	@RequestMapping(value = "/cs480/pedro", method = RequestMethod.GET)
	String pedrosFirstHttpApi() {
		return "My First HTTP API :)";
	}
	
	//Pedro's 2nd method (A4)
	@RequestMapping(value = "/cs480/pedro2", method = RequestMethod.GET)
	String pedrosUserInfo() {
		User u = new User();
		u.setId("123");
		u.setName("Pedro");
		u.setMajor("CS");
		return u.toString();
	}
	
	@RequestMapping(value = "/cs480/chris", method = RequestMethod.GET)
	String heAwaits() {
		return "Ph'nglui mglw'nafh Cthulhu R'lyeh wgah'nagl fhtagn";
	}
	
	// Will add data passing to Chart JavaScript -Chris
	
	@RequestMapping(value = "/cs480/darren", method = RequestMethod.GET)
	String darrenHelloWorld() {
		return "Wow! This how HTTP API works?";
	}
	
	@RequestMapping(value = "/cs480/sonia", method = RequestMethod.GET)
	String testing123() {
		return "Hi! can I have a job now?";
	}
	
	@RequestMapping(value = "/cs480/carmelo", method = RequestMethod.GET)
	String carmeloPasswordGenerator() {
		Random r = new Random();
		String password = "";

	    String alphabet = "123456789abcdefghijklmnopqrstuvwxyz";
	    for (int i = 0; i < 10; i++) {
	    	password += alphabet.charAt(r.nextInt(alphabet.length()));
	    } // prints 50 random characters from alphabet
	    return password;
	}
	
	@RequestMapping(value = "/cs480/carmelo2", method = RequestMethod.GET)
	String carmeloDatabase() throws Exception {
		int countNum = 0;
		 Connection conn = DriverManager.getConnection("jdbc:h2:./src/main/java/edu/csupomona/cs480/controller/test", "sa", "");
		        // add application code here
		 Statement Stmt = conn.createStatement(); 
		 Stmt.executeUpdate("insert into test (name) values('visitor')");
		 ResultSet rs = Stmt.executeQuery("Select count(*) as countNum from test");
		 while(rs.next()) { 
	            // Retrieve by column name 
	            countNum  = rs.getInt("countNum"); 
		 }
		        conn.close();
		        return "Connected - " + countNum + " page views";
	}

	/**
	 * This is a simple example of how to use a data manager
	 * to retrieve the data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be
	 * automatically converted to JSON format.
	 * <p>
	 * Try it in your web browser:
	 * 	http://localhost:8080/cs480/user/user101
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}

	/**
	 * This is an example of sending an HTTP POST request to
	 * update a user's information (or create the user if not
	 * exists before).
	 *
	 * You can test this with a HTTP client by sending
	 *  http://localhost:8080/cs480/user/user101
	 *  	name=John major=CS
	 *
	 * Note, the URL will not work directly in browser, because
	 * it is not a GET request. You need to use a tool such as
	 * curl.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
	User updateUser(
			@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		user.setMajor(major);
		user.setName(name);
		userManager.updateUser(user);
		return user;
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(
			@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}

}
