package xyz.leefly.project.dto;

import lombok.Data;
import xyz.leefly.project.bo.Company;
import xyz.leefly.project.bo.Equipment;
import xyz.leefly.project.bo.Product;

import java.util.List;

@Data
public class EnterpriseInfo {

    private Company company;

    private List<Product> products;

    private List<Equipment> equipments;

}
