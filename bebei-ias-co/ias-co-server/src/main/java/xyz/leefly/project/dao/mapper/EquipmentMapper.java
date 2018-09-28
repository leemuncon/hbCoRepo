package xyz.leefly.project.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import xyz.leefly.project.bo.Equipment;

import java.util.List;

public interface EquipmentMapper extends BaseMapper<Equipment> {


    void batchSaveEquipments(@Param("equipments") List<Equipment> equipments);

    void deleteEquipmentsByCompanyId(@Param("companyId") Long companyId);

}
