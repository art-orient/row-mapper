package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RowMapperImpl implements RowMapper<Employee> {
    private BigInteger id;
    private FullName fullName;
    private Position position;
    private LocalDate hired;
    private BigDecimal salary;

    @Override
    public Employee mapRow(ResultSet resultSet) {
        try {
            id = BigInteger.valueOf(resultSet.getInt(1));
            fullName = new FullName(resultSet.getString(2),
                resultSet.getString(3), resultSet.getString(4));
            position = Position.valueOf(resultSet.getString("position"));
            hired = LocalDate.parse(resultSet.getDate("hiredate").toString());
            salary = resultSet.getBigDecimal("salary");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Employee(id, fullName, position, hired, salary);
    }
}
