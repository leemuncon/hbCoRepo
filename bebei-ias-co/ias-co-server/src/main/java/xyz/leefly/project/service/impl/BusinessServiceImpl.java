package xyz.leefly.project.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xyz.leefly.project.bo.Company;
import xyz.leefly.project.bo.Equipment;
import xyz.leefly.project.bo.Product;
import xyz.leefly.project.bo.User;
import xyz.leefly.project.dao.mapper.CompanyMapper;
import xyz.leefly.project.dao.mapper.EquipmentMapper;
import xyz.leefly.project.dao.mapper.ProductMapper;
import xyz.leefly.project.dto.CompanyQuery;
import xyz.leefly.project.dto.EnterpriseInfo;
import xyz.leefly.project.service.BusinessService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        Long cid = companyMapper.saveCompany(company);
        List<Product> products = enterpriseInfo.getProducts();
        if (CollectionUtils.isNotEmpty(products)) {
            List<Product> collect = products
                    .stream()
                    .filter(Objects::nonNull)
                    .peek(p -> p.setCompanyId(cid)).collect(Collectors.toList());
            productMapper.batchSaveProducts(collect);
        }
        List<Equipment> equipments = enterpriseInfo.getEquipments();
        if (CollectionUtils.isNotEmpty(equipments)) {
            List<Equipment> collect = equipments
                    .stream()
                    .filter(Objects::nonNull)
                    .peek(p -> p.setCompanyId(cid)).collect(Collectors.toList());
            equipmentMapper.batchSaveEquipments(collect);
        }
        return cid;
    }

    @Override
    public Page<Company> queryCompanies(CompanyQuery query, int pageNo, int pageSize) {
        Page<Company> page = new Page<>(pageNo, pageSize);
        page.setRecords(companyMapper.selectPage(page, null));
        return page;
    }

    @Override
    public Page<Equipment> queryEquipments(Long companyId, int pageNo, int pageSize) {
        Page<Equipment> page = new Page<>(pageNo, pageSize);
        page.setRecords(equipmentMapper.selectPage(page, null));
        return page;
    }

    @Override
    public Page<Product> queryProducts(Long companyId, int pageNo, int pageSize) {
        Page<Product> page = new Page<>(pageNo, pageSize);
        page.setRecords(productMapper.selectPage(page, null));
        return page;
    }

    @Override
    public EnterpriseInfo getEnterpriseInfo(Long companyId) {
        Company company = companyMapper.selectById(companyId);
        if (company == null) {
            return null;
        }
        EnterpriseInfo info = new EnterpriseInfo();

        return info;
    }
}
