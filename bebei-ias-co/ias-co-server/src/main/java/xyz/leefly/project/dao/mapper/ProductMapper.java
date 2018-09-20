package xyz.leefly.project.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import xyz.leefly.project.bo.Product;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {

    void batchSaveProducts(@Param("products") List<Product> products);

}
