package com.issuer.issuerms.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.issuer.issuerms.data.BookDO;
import com.issuer.issuerms.data.Issue;
import com.issuer.issuerms.service.IssueService;
import com.netflix.discovery.EurekaClient;

@RestController
public class IssueController {
	
	@Autowired
	private EurekaClient discoveryClient;
	
	@Autowired
	IssueService issueService;
	
	
	@PostMapping("/issueBook/{isbn}/{noofcopies}")
	public ResponseEntity issueBook(@PathVariable Long isbn,@PathVariable Long noofcopies) {
		//check for availability of book
		String serviceurl=discoveryClient.getNextServerFromEureka("BOOK_SERVICE", false).getHomePageUrl();
		//http://localhost:6061/getBook/{isbn}
		String checkbooksurl=serviceurl+"/getBook/{isbn}";
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Long> vars = new HashMap<>();
		vars.put("isbn", isbn);
		BookDO bookDO=restTemplate.getForObject(checkbooksurl, BookDO.class,vars);
		
		Long availableCopies= bookDO.getTotalCopies() - bookDO.getIssuedCopies();
		if(availableCopies > noofcopies) {
			Issue issue=new Issue();
			issue.setID(1L);
			issue.setCustId(1L);
			issue.setIsbn(isbn);
			issue.setNoOfCopies(noofcopies);
			issueService.issueBooks(issue);
			
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
	
	@PostMapping("/issueBook")
	public ResponseEntity saveBook(@RequestBody Issue issue) {
		Issue issueresponse=issueService.issueBooks(issue);
		if(issueresponse!=null) {
			return new ResponseEntity("Book saved"+issueresponse,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Error issueing books",HttpStatus.NOT_FOUND);
		}
		
	}

}
