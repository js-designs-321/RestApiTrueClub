package com.jatin.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.jatin.springboot.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

}
