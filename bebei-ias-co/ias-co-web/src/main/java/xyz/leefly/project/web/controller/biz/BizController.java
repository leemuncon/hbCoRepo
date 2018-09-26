package xyz.leefly.project.web.controller.biz;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.leefly.project.bo.Company;
import xyz.leefly.project.service.BusinessService;
import xyz.leefly.project.web.model.RespData;
import xyz.leefly.project.dto.EnterpriseInfo;

@RestController
@RequestMapping("biz")
public class BizController {

    @Autowired
    private BusinessService businessService;

    @RequestMapping(method = RequestMethod.POST, value = "save")
    public RespData<Long> save(@RequestBody EnterpriseInfo info) {
        Long id = businessService.saveEnterpriseInfo(info);
        RespData<Long> resp = new RespData<>();
        resp.setData(id);
        resp.setSuccess(true);
        resp.setCode(0);
        resp.setMsg("success");
        return resp;
    }


    @RequestMapping(method = RequestMethod.GET, value = "list/{pageSize}/{pageNo}")
    public RespData<Page<Company>> list(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNo") Integer pageNo) {
        if (pageSize == null) {
            pageSize = 10;
        }
        if (pageNo == null) {
            pageNo = 1;
        }
        Page<Company> page = businessService.queryCompanies(null, pageNo, pageSize);
        RespData<Page<Company>> resp = new RespData<>();
        resp.setData(page);
        resp.setSuccess(true);
        resp.setCode(0);
        resp.setMsg("success");
        return resp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "detail/{id}")
    public RespData<EnterpriseInfo> detail(@PathVariable("id") Long id) {
        EnterpriseInfo info = businessService.getEnterpriseInfo(id);
        RespData<EnterpriseInfo> resp = new RespData<>();
        resp.setData(info);
        resp.setSuccess(true);
        resp.setCode(0);
        resp.setMsg("success");
        return resp;
    }


}
