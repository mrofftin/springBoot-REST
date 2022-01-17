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
    @Results({
            @Result(property="name", column="company_name"),
            @Result(property="address", column="company_address")
    })
    List<Company> getAll();
}
