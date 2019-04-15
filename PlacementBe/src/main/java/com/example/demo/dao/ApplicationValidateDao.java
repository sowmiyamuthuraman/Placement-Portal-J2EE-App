package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.example.demo.beans.Company;
import com.example.demo.constants.SqlConstants;
import com.example.demo.dbutil.DbConnection;

public class ApplicationValidateDao {
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public LocalDate getDate(Company company) {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.GETLASTDATE);
			preparedStatement.setInt(1, company.getCompanyId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.isBeforeFirst() == false) {
				return (LocalDate) null;
			}
			resultSet.next();
			return resultSet.getDate(1).toLocalDate();
		} catch (SQLException e) {
			return null;
		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}

	}

	public boolean checkIsApplied(Company company, String reg_no) throws SQLException {
		Connection connection = DbConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.CHECKAPPLIED);
			preparedStatement.setInt(1, company.getCompanyId());
			preparedStatement.setString(2, reg_no);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {

		} finally {
			DbConnection.closeConnection(resultSet, preparedStatement, connection);
		}
		return false;
	}

}
