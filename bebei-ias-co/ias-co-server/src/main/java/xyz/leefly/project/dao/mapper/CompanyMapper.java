package xyz.leefly.project.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import xyz.leefly.project.bo.Company;

public interface CompanyMapper extends BaseMapper<Company> {

    void saveCompany(Company company);

    int deleteCompany(@Param("companyId")Long companyId);

}
