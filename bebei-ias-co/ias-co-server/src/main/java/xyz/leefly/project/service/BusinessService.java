package xyz.leefly.project.service;

import com.baomidou.mybatisplus.plugins.Page;
import xyz.leefly.project.bo.Company;
import xyz.leefly.project.bo.Equipment;
import xyz.leefly.project.bo.Product;
import xyz.leefly.project.dto.CompanyQuery;
import xyz.leefly.project.dto.EnterpriseInfo;

public interface BusinessService {

    /**
     * 保存企业信息
     * @param enterpriseInfo
     * @return
     */
    Long saveEnterpriseInfo(EnterpriseInfo enterpriseInfo);

    /**
     * 通过查询条件查询公司
     * @param query
     * @return
     */
    Page<Company> queryCompanies(CompanyQuery query, int pageNo, int pageSize);

    /**
     * 查询企业的设备
     * @param companyId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Equipment> queryEquipments(Long companyId, int pageNo, int pageSize);

    /**
     * 查询企业的产品
     * @param companyId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Product> queryProducts(Long companyId, int pageNo, int pageSize);

    /**
     * 获取企业详情
     * @param companyId
     * @return
     */
    EnterpriseInfo getEnterpriseInfo(Long companyId);

}