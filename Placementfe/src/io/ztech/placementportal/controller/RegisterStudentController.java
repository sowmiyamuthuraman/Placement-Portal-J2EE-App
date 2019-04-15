package io.ztech.placementportal.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;
import io.ztech.placementportal.delegate.StudentDetailDelegate;
import io.ztech.placementportal.delegate.ValidateInput;

@WebServlet("/RegisterStudent")
public class RegisterStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public RegisterStudentController() {
		super();
		log = Logger.getLogger("RegisterStudentController.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering post method of RegisterStudentController ");
		ValidateInput validateInput = new ValidateInput();
		StudentDetailDelegate studentDelegate = new StudentDetailDelegate();
		Object object = JSONValue.parse(request.getParameter("register"));
		JSONObject jsonObject = (JSONObject) object;
		Student student = new Student();
		Register register = new Register();
		RetrieveDetailDelegate delegate = new RetrieveDetailDelegate();
		register.setRole("student");
		student.setDepartment(jsonObject.get("department").toString());
		try {
			if (validateInput.validateDetail(Regex.REGISTER_NO_REGEX, jsonObject.get("registerNumber").toString())) {

				if (!delegate.checkIsAvailable(jsonObject.get("registerNumber").toString(),
						ApplicationConstants.REGISTER_NO)) {
					student.setStudentId(jsonObject.get("registerNumber").toString());
					register.setReg_no(jsonObject.get("registerNumber").toString());
					register.setPassword(jsonObject.get("registerNumber").toString());
				}

				else {
					response.getWriter().write("registerNumber_exists");
					return;
				}
			} else {
				response.getWriter().write("registerNumber_error");
				return;

			}

			if (validateInput.validateDetail(Regex.STUDENT_NAME, jsonObject.get("studentName").toString())) {
				student.setName(jsonObject.get("studentName").toString());
			} else {
				response.getWriter().write("nameError");
				return;

			}
			if (validateInput.validateMark(jsonObject.get("hsc").toString())) {
				student.setMarkX(Float.parseFloat(jsonObject.get("hsc").toString()));
			} else {
				response.getWriter().write("hscError");
				return;

			}
			if (validateInput.validateMark(jsonObject.get("sslc").toString())) {
				student.setMarkXII(Float.parseFloat(jsonObject.get("sslc").toString()));
			} else {
				response.getWriter().write("sslcError");
				return;

			}
			if (validateInput.validateCgpa(jsonObject.get("cgpa").toString())) {
				student.setCgpa(Float.parseFloat(jsonObject.get("cgpa").toString()));
			} else {
				response.getWriter().write("cgpaError");

			}
			if (validateInput.validateArrearCount(jsonObject.get("arrearCount").toString())) {
				student.setArrearCount(Integer.parseInt(jsonObject.get("arrearCount").toString()));
			} else {
				response.getWriter().write("arrearCountError");

			}

			if (validateInput.validateDetail(Regex.EMAIL_REGEX, jsonObject.get("email").toString())) {
				register.setEmail(jsonObject.get("email").toString());
			} else {
				response.getWriter().write("emailError");
				return;

			}
			if (studentDelegate.createStudentLogin(register) && studentDelegate.addStudentDetail(student)) {
				response.getWriter().write("success");
				return;
			}
		}

		catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting the post method of RegisterStudentController");
	}

}
