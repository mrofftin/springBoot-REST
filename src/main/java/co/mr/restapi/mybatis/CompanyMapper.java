package co.mr.restapi.mybatis;

import co.mr.restapi.model.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyMapper {
    @Insert("insert into company(company_name, company_address) values(#{company.name}, #{company.address})")
    int insert(@Param("company") Company company);

    @Select("select * from company")
    @Results({
            @Result(property="name", column="company_name"),
            @Result(property="address", column="company_address")
    })
    List<Company> getAll();
}
