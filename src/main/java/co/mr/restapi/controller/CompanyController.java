package co.mr.restapi.controller;

import co.mr.restapi.model.Company;
import co.mr.restapi.mybatis.CompanyMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyMapper companyMapper;

    @PostMapping("")
    public int post(@RequestBody Company company) {
        return companyMapper.insert(company);
    }

    @GetMapping("")
    public List<Company> getAll(){
        return companyMapper.getAll();
    }


}
