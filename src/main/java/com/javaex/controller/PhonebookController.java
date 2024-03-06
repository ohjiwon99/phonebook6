package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.PhonebookService;
import com.javaex.vo.PersonVo;

@Controller
public class PhonebookController {

	@Autowired
	private PhonebookService phonebookService;

	/****************
	 * 1. 등록폼
	 *****************/
	// localhost:8880/phonebook6/writeform
	@RequestMapping(value = "/writeform", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeform() {
		System.out.println("phonebookcontroller.writeform()");

		// return "/WEB-INF/views/writeform.jsp";
		/*
		 * •application.properties에서 #view resolve
		 * spring.mvc.view.prefix=/WEB-INF/views/ spring.mvc.view.suffix=.jsp 추가하면 줄여서
		 * 쓰기 가능 ex)return "writeform";
		 */

		return "writeform";
	}

	/****************
	 * 1-1. 등록
	 *****************/
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("phonebookcontroller.write()");

		// System.out.println(personVo);
		phonebookService.exeWrite(personVo);
		return "redirect:/list";// 리다이렉트

	}

	/****************
	 * 2. 리스트
	 *****************/
	// http://localhost:8880/phonebook6/list
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	private String list(Model model) {
		System.out.println("phonebookcontroller.list()");

		List<PersonVo> pList = phonebookService.exeList();

		model.addAttribute("pList", pList);

		return "list";
	}

	/****************
	 * 3. 삭제
	 *****************/
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("PhonebookController.delete()");

		phonebookService.exeDelete(no);

		return "redirect:/list";

	}

	/****************
	 * 4. 수정폼
	 *****************/
	@RequestMapping(value = "/modifyform", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam(value = "no") int no, Model model) {
		System.out.println("PhonebookController.modifyForm()");

		PersonVo personVo = phonebookService.exeModifyForm(no);

		model.addAttribute("personVo", personVo);

		return "modifyForm";
	}

	/****************
	 * 4-1. 수정
	 *****************/
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute PersonVo personVo) {
		System.out.println("PhonebookController.modify()");

		phonebookService.exeModify(personVo);

		return "redirect:/list";
	}

}
