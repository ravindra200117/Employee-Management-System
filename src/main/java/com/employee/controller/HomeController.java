package com.employee.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.employee.dao.EmpDao;
import com.employee.model.Emp;

@Controller
public class HomeController 
{
	@Autowired
	private EmpDao empDao;
	
	@RequestMapping(path = "/home")
	public String home(Model m)
	{
		List<Emp> list = empDao.getAllEmp();
		m.addAttribute("empList",list);
		return "home";
	}
	
	@RequestMapping(path = "/addEmp")
	public String addEmp()
	{
		return "addemp";
	}
	
	@RequestMapping(path = "/createEmp", method = RequestMethod.POST)
	public String createEmp(@ModelAttribute Emp emp, HttpSession session) {
		System.out.println(emp);

		int i = empDao.saveEmp(emp);
		session.setAttribute("msg", "Register Sucessfully");
		return "redirect:/addEmp";
	}
	
	@RequestMapping(path = "/editEmp/{id}")
	public String editEmp(@PathVariable int id, Model m)
	{
		Emp emp = empDao.getEmpById(id);
		m.addAttribute("emp",emp);
		return "editemp";
	}
	
	@RequestMapping(path = "/updateEmp", method = RequestMethod.POST)
	public String updateEmp(@ModelAttribute Emp emp, HttpSession session) 
	{
		empDao.update(emp);
		session.setAttribute("msg", "Update Sucessfully");
		return "redirect:/home";
	}
	
	@RequestMapping(path = "/deleteEmp/{id}")
	public String deleteEmp(@PathVariable int id, HttpSession session) 
	{
		empDao.deleteEmp(id);
		session.setAttribute("msg", "delete Sucessfully");
		return "redirect:/home";
	}
}
