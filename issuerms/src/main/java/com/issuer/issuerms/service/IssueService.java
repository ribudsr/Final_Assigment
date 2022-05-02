package com.issuer.issuerms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.issuer.issuerms.data.Issue;
import com.issuer.issuerms.data.IssueRepository;

@Service
public class IssueService {

	@Autowired
	IssueRepository issueRepository;
	
	public Issue issueBooks(Issue issue) {
		return issueRepository.save(issue);	
	}
}
