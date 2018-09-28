package xyz.leefly.project.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.leefly.project.bo.Company;
import xyz.leefly.project.bo.Equipment;
import xyz.leefly.project.bo.Product;
import xyz.leefly.project.dao.mapper.CompanyMapper;
import xyz.leefly.project.dao.mapper.EquipmentMapper;
import xyz.leefly.project.dao.mapper.ProductMapper;
import xyz.leefly.project.dto.EnterpriseInfo;
import xyz.leefly.project.service.BusinessService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private EquipmentMapper equipmentMapper;


    @Transactional
    @Override
    public Long saveEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
        if (enterpriseInfo == null) {
            return 0L;
        }
        Company company = enterpriseInfo.getCompany();
        if (company == null) {
            return -1L;
        }
        if (company.getProductionValueUnit() == null) {
            company.setProductionValueUnit("万元");
        }
        if (company.getId() == null) {
            companyMapper.saveCompany(company);
        } else {
            companyMapper.updateById(company);
        }
        List<Product> products = enterpriseInfo.getProducts();
        if (CollectionUtils.isNotEmpty(products)) {
            productMapper.deleteProductsByCompanyId(company.getId());
            List<Product> collect = products
                    .stream()
                    .filter(Objects::nonNull)
                    .peek(p -> {
                        p.setCompanyId(company.getId());
                        if (p.getUnit() == null) {
                            p.setUnit("万吨");
                        }
                    }).collect(Collectors.toList());
            productMapper.batchSaveProducts(collect);
        }
        List<Equipment> equipments = enterpriseInfo.getEquipments();
        if (CollectionUtils.isNotEmpty(equipments)) {
            equipmentMapper.deleteEquipmentsByCompanyId(company.getId());
            List<Equipment> collect = equipments
                    .stream()
                    .filter(Objects::nonNull)
                    .peek(e -> {
                        e.setCompanyId(company.getId());
                        if (e.getUnit() == null) {
                            e.setUnit("万吨");
                        }
                    }).collect(Collectors.toList());
            equipmentMapper.batchSaveEquipments(collect);
        }
        return company.getId();
    }

    @Override
    public Page<Company> queryCompanies(Company query, int pageNo, int pageSize) {
        Page<Company> page = new Page<>(pageNo, pageSize);
        EntityWrapper<Company> wrapper = Condition.wrapper();
        wrapper.eq("deleted", 0);
        if (query != null) {
            if (StringUtils.isNotBlank(query.getName())) {
                wrapper.like("name", query.getName());
            }
            if (StringUtils.isNotBlank(query.getNature())) {
                wrapper.eq("nature", query.getNature());
            }
            if (StringUtils.isNotBlank(query.getLegalPerson())) {
                wrapper.like("legal_person", query.getLegalPerson());
            }
            if (StringUtils.isNotBlank(query.getBusinessLicense())) {
                wrapper.eq("business_license", query.getBusinessLicense());
            }
            if (StringUtils.isNotBlank(query.getContact())) {
                wrapper.like("contact", query.getContact());
            }
            if (StringUtils.isNotBlank(query.getPhone())) {
                wrapper.like("phone", query.getPhone());
            }
            if (StringUtils.isNotBlank(query.getProvince())) {
                wrapper.eq("province", query.getProvince());
            }
            if (StringUtils.isNotBlank(query.getCity())) {
                wrapper.eq("city", query.getCity());
            }
            if (StringUtils.isNotBlank(query.getDistrict())) {
                wrapper.eq("district", query.getDistrict());
            }
            if (StringUtils.isNotBlank(query.getProductionLicense())) {
                wrapper.eq("production_license", query.getProductionLicense());
            }
            if (query.getStatus() != null) {
                wrapper.eq("status", query.getStatus());
            }if (StringUtils.isNotBlank(query.getCheckYear())) {
                wrapper.eq("check_year", query.getCheckYear());
            }
        }
        page.setRecords(companyMapper.selectPage(page, wrapper));
        return page;
    }

    @Override
    public Page<Equipment> queryEquipments(Long companyId, int pageNo, int pageSize) {
        Page<Equipment> page = new Page<>(pageNo, pageSize);
        EntityWrapper<Equipment> wrapper = Condition.wrapper();
        wrapper.eq("company_id", companyId).eq("deleted", 0);
        page.setRecords(equipmentMapper.selectPage(page, wrapper));
        return page;
    }

    @Override
    public Page<Product> queryProducts(Long companyId, int pageNo, int pageSize) {
        Page<Product> page = new Page<>(pageNo, pageSize);
        EntityWrapper<Product> wrapper = Condition.wrapper();
        wrapper.eq("company_id", companyId).eq("deleted", 0);
        page.setRecords(productMapper.selectPage(page, wrapper));
        return page;
    }

    @Override
    public EnterpriseInfo getEnterpriseInfo(Long companyId) {
        Company company = companyMapper.selectById(companyId);
        if (company == null) {
            return null;
        }
        EnterpriseInfo info = new EnterpriseInfo();
        info.setCompany(company);

        EntityWrapper<Product> pw = Condition.wrapper();
        List<Product> products = productMapper.selectList(pw.eq("company_id", companyId).eq("deleted", 0));
        info.setProducts(products);

        EntityWrapper<Equipment> ew = Condition.wrapper();
        List<Equipment> equipments = equipmentMapper.selectList(ew.eq("company_id", companyId).eq("deleted", 0));
        info.setEquipments(equipments);
        return info;
    }

    @Override
    public boolean deleteCompany(Long companyId) {
        int row = companyMapper.deleteCompany(companyId);
        if (row > 0) {
            productMapper.deleteProductsByCompanyId(companyId);
            equipmentMapper.deleteEquipmentsByCompanyId(companyId);
        }
        return row > 0;
    }
}
