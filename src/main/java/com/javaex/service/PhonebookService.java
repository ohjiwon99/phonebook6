package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;

@Service//자동연결
public class PhonebookService {
	
	@Autowired
	private PhonebookDao phonebookDao;
	
	
	/****************
	 *  1-1.  등록
	 *****************/
	public int exeWrite(PersonVo personVo) {
		System.out.println("PhonebookService.exeWrite()");
		
		int count = phonebookDao.personInsert(personVo);
		
		return count;
	}
	
	/****************
	 *   2.리스트
	 *****************/
	public List<PersonVo> exeList() {
		System.out.println("PhonebookService.exeList()");
		
		List<PersonVo> personList = phonebookDao.personSelect();
		
		return personList;
	}
	
	/****************
	 *   3. 삭제
	 *****************/
	public int exeDelete(int no) {
		System.out.println("PhonebookService.exeDelete()");

		int count = phonebookDao.personDelete(no);

		return count;
	}
	
	
	

}
