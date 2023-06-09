package com.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.web.dto.TestResultDto;
import com.demo.web.form.TestForm;
import com.demo.web.service.TestService;


@Controller
public class TestController {

	@Autowired
	TestService testService;

	@GetMapping("/request")
	public String testRequest() {
		return "test_request";
	}

	@PostMapping("/confirm")
	public String testConfirm(@ModelAttribute TestForm testForm, Model model) {
		
		int ticketNumber = testService.testRequest(testForm);
		//System.out.println(testForm.getFaultDevice() + ":" + testForm.getContractType());
		model.addAttribute("ticketNumber", ticketNumber);
		System.out.println(ticketNumber);
		if (ticketNumber == 0) {
			return "test_request_fault";
		}
		System.out.println("test_request_fault.html");

		return "test_confirm";

	}

	@GetMapping("/result/{ticket_number}")
	public String testResult(@PathVariable("ticket_number") int ticketNumber, Model model ) {
		System.out.println(ticketNumber);
		TestResultDto testResultDto = testService.testResult(ticketNumber);
		model.addAttribute("testResult", testResultDto);
		return "test_result";
	}

}
