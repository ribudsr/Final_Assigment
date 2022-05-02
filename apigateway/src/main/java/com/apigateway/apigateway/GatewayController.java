package com.apigateway.apigateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apigateway.apigateway.data.BookDO;
import com.apigateway.apigateway.data.IssueDO;
import com.netflix.discovery.EurekaClient;

@RestController
public class GatewayController {

	@Autowired
	private EurekaClient discoveryClient;
	
	
	 @GetMapping("/user")
	  public Map<String, Object> user(Authentication auth) {
	  if(auth.getPrincipal()!=null) {
	        	System.out.println("User logged in" + auth.getDetails());
	   }
	        
	  return null;
	  }
	 
	 
	 @GetMapping("/apigateway/getAllbooks")
	 public ResponseEntity getAllBooks(Authentication auth) {
		 if(auth.getPrincipal()!=null) {
		 String serviceurl=discoveryClient.getNextServerFromEureka("BOOK_SERVICE", false).getHomePageUrl();
			//http://localhost:6061/getBook/{isbn}
			String getallbooksurl=serviceurl+"getAllbooks";
			RestTemplate restTemplate = new RestTemplate();
			List<BookDO> bookDOList=(List<BookDO>)restTemplate.getForObject(getallbooksurl, Object.class);
			if(bookDOList!=null && (!bookDOList.isEmpty())) {
				return new ResponseEntity(bookDOList,HttpStatus.OK);
			}
			else {
				
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
		 }
		 else {
			 return new ResponseEntity("User not authenticated",HttpStatus.UNAUTHORIZED);
		 }
	 }
	 
	 @GetMapping("/apigateway/getBook/{isbn}")
	 public ResponseEntity getBook(@PathVariable Long isbn,Authentication auth) {
		 if(auth.getPrincipal()!=null) {
		 String serviceurl=discoveryClient.getNextServerFromEureka("BOOK_SERVICE", false).getHomePageUrl();
			//http://localhost:6061/getBook/{isbn}
		 String checkbooksurl=serviceurl+"/getBook/{isbn}";
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Long> vars = new HashMap<>();
			vars.put("isbn", isbn);
			BookDO bookDO=restTemplate.getForObject(checkbooksurl, BookDO.class,vars);
			if(bookDO!=null) {
				return new ResponseEntity(bookDO,HttpStatus.OK);
			}
			else {
				
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
		 }
		 else {
			 return new ResponseEntity("User not authenticated",HttpStatus.UNAUTHORIZED);
		 }
	 }
	 
	 @PostMapping("/issueBook/{isbn}/{noofcopies}")
		public ResponseEntity issueBook(@PathVariable Long isbn,@PathVariable Long noofcopies,Authentication auth) {
			//check for availability of book
		 if(auth.getPrincipal()!=null) {
		 String serviceurl=discoveryClient.getNextServerFromEureka("BOOK_SERVICE", false).getHomePageUrl();
			//http://localhost:6061/getBook/{isbn}
			String checkbooksurl=serviceurl+"/getBook/{isbn}";
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Long> vars = new HashMap<>();
			vars.put("isbn", isbn);
			BookDO bookDO=restTemplate.getForObject(checkbooksurl, BookDO.class,vars);
			
			Long availableCopies= bookDO.getTotalCopies() - bookDO.getIssuedCopies();
			if(availableCopies > noofcopies) {
				
				String issueserviceurl=discoveryClient.getNextServerFromEureka("ISSUE_SERVICE", false).getHomePageUrl();
				IssueDO issue=new IssueDO();
				issue.setID(1L);
				issue.setCustId(1L);
				issue.setIsbn(isbn);
				issue.setNoOfCopies(noofcopies);
	
				String issuebookurl = issueserviceurl +"issueBook";
				IssueDO responseIssue=restTemplate.postForObject(issuebookurl,issue,IssueDO.class);
				
				String editbooksurl=serviceurl+"editBook";
				bookDO.setIssuedCopies(noofcopies);
				BookDO bookDoPatch=restTemplate.postForObject(editbooksurl,bookDO,BookDO.class);
				if(bookDoPatch!=null) {
					return new ResponseEntity("Book saved"+bookDoPatch,HttpStatus.OK);
				}
				else {
					return new ResponseEntity("Error issueing books",HttpStatus.NOT_FOUND);
				}
				
			}
			else {
				return new ResponseEntity("Book not available",HttpStatus.NOT_FOUND);
			}
		

		}
		 else {
			 return new ResponseEntity("User not authenticated",HttpStatus.UNAUTHORIZED);
		 }
}
}
