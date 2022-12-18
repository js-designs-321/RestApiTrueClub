package com.jatin.springboot.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jatin.springboot.exception.ResourceNotFoundException;
import com.jatin.springboot.model.Member;
import com.jatin.springboot.repository.MemberRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class MemberController {
	
	@Autowired
	private MemberRepository memberRepository; 
	
	@GetMapping("/members")
	public List<Member> getAllMembers(){
		return memberRepository.findAll(); 
	}
	
	@GetMapping("/members/{id}")
	public Member getMemberById(@PathVariable Long id) {
		Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member does not exist with id :" + id)); 
		return member; 
	}
	
	@PostMapping("/members")
	public Member createMember(@RequestBody Member member) {
		return memberRepository.save(member); 
	}
	
	@PutMapping("/members/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable Long id,
											   @RequestBody Member member){
		Member updateMember = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member does not exist with id :" + id)); 
		if(member.getAddress() != null) {
			updateMember.setAddress(member.getAddress()); 
		}if(member.getName() != null) {
			updateMember.setName(member.getName());
		}if(member.getPincode() != 0) {
			updateMember.setPincode(member.getPincode());
		}if(member.getState() != null) {
			updateMember.setState(member.getState());
		}
		memberRepository.save(updateMember); 
		return ResponseEntity.ok(updateMember); 
	}
	
	@DeleteMapping("/members/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteNote(@PathVariable Long id){
		Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member does not exist with id :" + id)); 
		memberRepository.delete(member);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
