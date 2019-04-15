package io.ztech.placementportal.bean;

import io.ztech.placementportal.constants.ApplicationConstants;

public class PersonalInfo {
	private int personalInfoId;
	private String phoneNumber, alternatePhone, email, alternateEmail, bloodGroup, location;
	private String studentId, DateOfBirth, gender;

	@Override
	public String toString() {
		return ApplicationConstants.D_O_B + DateOfBirth + ApplicationConstants.GENDER + gender
				+ ApplicationConstants.PHONE_NUMBER + phoneNumber + ApplicationConstants.ALTERNATE_PHONE
				+ alternatePhone + ApplicationConstants.EMAIL_ID + email + ApplicationConstants.ALTERNATE_EMAIL_ID
				+ alternateEmail + ApplicationConstants.BLOOD_GROUP + bloodGroup + ApplicationConstants.LOCATION
				+ location;
	}

	public int getPersonalInfoId() {
		return personalInfoId;
	}

	public void setPersonalInfoId(int personalInfoId) {
		this.personalInfoId = personalInfoId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAlternatePhone() {
		return alternatePhone;
	}

	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
