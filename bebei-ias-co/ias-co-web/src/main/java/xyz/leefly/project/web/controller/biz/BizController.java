package xyz.leefly.project.web.controller.biz;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.leefly.project.web.model.RespData;
import xyz.leefly.project.dto.EnterpriseInfo;

@RestController
@RequestMapping("biz")
public class BizController {

    @RequestMapping(method = RequestMethod.POST, value = "save")
    public RespData<Long> save(EnterpriseInfo info) {
        RespData<Long> resp = new RespData<>();
        return resp;
    }


    @RequestMapping(method = RequestMethod.GET, value = "list")
    public RespData<Long> list(@PathVariable("id") Long id) {
        RespData<Long> resp = new RespData<>();
        return resp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "detail/{id}")
    public RespData<EnterpriseInfo> detail(@PathVariable("id") Long id) {
        RespData<EnterpriseInfo> resp = new RespData<>();
        return resp;
    }


}
