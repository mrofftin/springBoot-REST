package co.mr.restapi.mybatis;

import co.mr.restapi.model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface EmployeeMapper {
    @Insert("insert into employee(company_id, employee_name, employee_address) values(#{employee.companyId}, #{employee.name}, #{employee.address})")
    @Options(useGeneratedKeys=true, keyProperty="id") // 자동으로 생성되는 id값이 자바객체의 keyProperty id에 생성되도록 설정
    int insert(@Param("employee") Employee employee); //파라미터 company가 "company"라는 이름으로 매핑된다.

    @Select("select * from employee")
    @Results(id="EmployeeMap", value={
            @Result(property="name", column="employee_name"),
            @Result(property="address", column="employee_address")
    })
    List<Employee> getAll();

    @ResultMap("EmployeeMap") // 매핑관계의 재사용
    @Select("select * from employee where id=#{id}")
    Employee getById(@Param("id") int id);

    // employee목록을 얻어오는 API
    @Select("select * from employee where company_id=#{companyId}")
    @ResultMap("EmployeeMap")
    List<Employee> getByCompanyId(@Param("companyId") int companyId);
}
