package io.ztech.placementportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.placementportal.bean.Marks;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;
import io.ztech.placementportal.dbutil.DbConnection;

public class UpdateStudentDetailDao {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public void updatePlacementDetail(PlacedDetail student) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.PLACED_DETAIL);
			preparedStatement.setString(1, student.getStudentId());
			preparedStatement.setInt(2, student.getCompanyId());
			preparedStatement.setString(3, student.getJobStatus());
			if (preparedStatement.executeUpdate() >= 0) {
				preparedStatement = connection.prepareStatement(SqlConstants.UPDATE_PLACED);
				preparedStatement.setString(1, student.getStudentId());
				preparedStatement.executeUpdate();
			}

		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

	}

	public void updateMarkDetail(Marks mark) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.EDITED_MARK_DETAIL);
			preparedStatement.setFloat(1, mark.getMarkX());
			preparedStatement.setFloat(2, mark.getMarkXII());
			preparedStatement.setFloat(3, mark.getCgpa());
			preparedStatement.setInt(4, mark.getArrearCount());
			preparedStatement.setString(5, mark.getStudentId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

	}

	public void updateMarkDetail(Student studentDetail) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.EDITED_DETAIL);
			preparedStatement.setString(1, studentDetail.getName());
			preparedStatement.setString(2, studentDetail.getDepartment());
			preparedStatement.setString(3, studentDetail.getStudentId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
	}

	public void updatePersonalDetail(PersonalInfo personalInfo) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.UPDATE_PERSONAL_INFO);
			preparedStatement.setString(1, personalInfo.getStudentId());
			preparedStatement.setString(2, personalInfo.getDateOfBirth());
			preparedStatement.setString(3, personalInfo.getBloodGroup());
			preparedStatement.setString(4, personalInfo.getPhoneNumber());
			preparedStatement.setString(5, personalInfo.getAlternatePhone());
			preparedStatement.setString(6, personalInfo.getEmail());
			preparedStatement.setString(7, personalInfo.getAlternateEmail());
			preparedStatement.setString(8, personalInfo.getLocation());
			preparedStatement.setString(9, personalInfo.getGender());
			preparedStatement.executeUpdate();

		} catch (SQLException e1) {
			throw new SQLException(ApplicationConstants.ERROR);
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
	}
}