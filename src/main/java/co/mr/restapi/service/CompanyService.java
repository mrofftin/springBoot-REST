package co.mr.restapi.service;

import co.mr.restapi.model.Company;
import co.mr.restapi.mybatis.CompanyMapper;
import co.mr.restapi.mybatis.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Company> getAll() {
        List<Company> companyList = companyMapper.getAll();

        if(companyList != null && companyList.size() > 0) {
            for(Company company:companyList) {
                company.setEmployeeList(employeeMapper.getByCompanyId(company.getId()));
            }
        }
        return companyList;
    }
}
