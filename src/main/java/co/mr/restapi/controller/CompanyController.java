package co.mr.restapi.controller;

import co.mr.restapi.model.Company;
import co.mr.restapi.mybatis.CompanyMapper;
import co.mr.restapi.service.CompanyService;
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

    @Autowired
    private CompanyService companyService;

    @PostMapping("")
    public Company post(@RequestBody Company company) {
        companyMapper.insert(company);
        return company;
    }

    @GetMapping("")
    public List<Company> getAll(){
        return companyMapper.getAll();
//        return companyService.getAll();
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable("id") int id){
        return companyMapper.getById(id);
    }
}
