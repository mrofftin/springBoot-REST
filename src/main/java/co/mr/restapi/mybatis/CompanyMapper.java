package co.mr.restapi.mybatis;

import co.mr.restapi.model.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyMapper {
    @Insert("insert into company(company_name, company_address) values(#{company.name}, #{company.address})")
    // 자동으로 생성되는 id값이 자바객체의 keyProperty id에 생성되도록 설정
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insert(@Param("company") Company company);

    @Select("select * from company")
    @Results(id="CompanyMap", value={
            @Result(property="name", column="company_name"),
            @Result(property="address", column="company_address"),
//		파라미터 id를 이용해서 EmployeeMapper.getByCompanyId를 호출해서 전달된 결과를 employeeList에 매핑하라는 의미
//		여기서 @Many(select="co.mr.web.EmployeeMapper.getByCompanyId")는 서브쿼리
//		      서브쿼리에 co.mr.web.EmployeeMapper.getByCompanyId API를 이용하겠다는 의미

//		이렇게 하면 CompanyController 에서 companyMapper.getAll()을 그대로 사용할 수 있다..
//		즉, CompanyService에서 만든 getAll() 자바로직이 필요없다, 간단하게 아래 한줄만 입력하면된다는 의미이다.

            // company의 id column을 의미함
            @Result(property="employeeList",column="id", many=@Many(select="co.mr.restapi.mybatis.EmployeeMapper.getByCompanyId"))


    })
    List<Company> getAll();

    @ResultMap("CompanyMap")
    @Select("select * from company where id=#{id}")
    Company getById(@Param("id") int id);
}
