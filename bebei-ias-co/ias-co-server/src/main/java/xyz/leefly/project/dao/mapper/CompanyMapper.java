package xyz.leefly.project.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import xyz.leefly.project.bo.Company;

public interface CompanyMapper extends BaseMapper<Company> {

    Long saveCompany(Company company);

}
