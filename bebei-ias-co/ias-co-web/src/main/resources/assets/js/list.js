layui.use(['table', 'laydate', 'laypage'], function () {

    $('#left_menus').children().removeClass('layui-this');
    $('#list_menu').addClass('layui-this');

    var element = layui.element;
    //日期
    var laydate = layui.laydate;
    laydate.render({
        elem: '#date',
        type: 'year'
    });
    var table = layui.table;
    var companyTable = table.render({
        elem: '#companies'
        ,url:'/biz/list'
        ,title: '企业数据列表'
        ,cols: [[
            {field:'id', title:'ID', hide: true}
            ,{field:'name', title:'企业名称'}
            ,{field:'nature', title:'企业性质'}
            ,{field:'legalPerson', title:'法人代表'}
            ,{field:'businessLicense', title:'营业执照'}
            ,{field:'contact', title:'联系人'}
            ,{field:'phone', title:'联系电话'}
            ,{field:'area', title:'区域', templet: function(res){
                    return res.province + res.city + res.district;
                }}
            ,{field:'address', title:'地址'}
            ,{field:'stuffNumber', title:'企业人数（人）'}
            ,{field:'lastYearProductionValue', title:'去年产值（万元）'}
            ,{field:'laboratoryLevel', title:'实验室水平'}
            ,{field:'authentication', title:'认证情况'}
            ,{field:'productionLicense', title:'生产许可证'}
            ,{field:'status', title:'企业状态', templet: function(res){
                    return res.status > 0 ? '停业' : '正常';
                }}
            ,{field:'checkYear', title:'统计年份'}
            , {fixed: 'right', title: '操作', toolbar: '#companyBar', width: 160, unresize: true}
        ]]
        ,page: true
        ,parseData: function(res){ //res 即为原始返回的数据
            if (res.success) {
                return {
                    "code": res.code,           //解析接口状态
                    "msg": res.msg,             //解析提示文本
                    "count": res.data.total,    //解析数据长度
                    "data": res.data.records    //解析数据列表
                };
            } else {
                return {
                    "code": res.code,
                    "msg": res.msg,
                    "count": 0,
                    "data": []
                };
            }
        }
        ,done: function(res, curr, count){
            console.log(res);
        }
        ,id: 'companyTable'
    });
    //监听行工具事件
    table.on('tool(companies)', function (obj) {
        if (obj.event === 'del') {
            layer.confirm('真的删除吗？', function (index) {
                deleteCompany(obj.data.id);
                layer.close(index);
            });
        } else if (obj.event === 'see') {
            window.location = '/detail/' + obj.data.id;
        } else if (obj.event === 'edit') {
            window.location = '/edit/' + obj.data.id;
        }
    });

    function deleteCompany(id) {
        $.ajax({
            url: '/biz/delete/' + id,
            type: 'get',
            dataType: 'json',
            success: function (res) {
                if (res.success) {
                    companyTable.reload();
                } else {
                    layer.msg('删除失败', {
                        time: 2000, //2s后自动关闭
                        btn: ['确定']
                    });
                }
            },
            error: function () {
                layer.msg('删除失败', {
                    time: 2000, //2s后自动关闭
                    btn: ['确定']
                });
            }
        })
    }

    $('#searchBtn').on('click', function () {
        var form = $('#queryForm');
        var name = form.find('[name="name"]').val();
        var nature = form.find('[name="nature"]').val();
        var legalPerson = form.find('[name="legalPerson"]').val();
        var businessLicense = form.find('[name="businessLicense"]').val();
        var contact = form.find('[name="contact"]').val();
        var phone = form.find('[name="phone"]').val();
        var province = form.find('[name="province"]').val();
        var city = form.find('[name="city"]').val();
        var district = form.find('[name="district"]').val();
        var productionLicense = form.find('[name="productionLicense"]').val();
        var status = form.find('[name="status"]').val();
        var checkYear = form.find('[name="checkYear"]').val();
        var productName = form.find('[name="productName"]').val();

        var data = {
            name: name,
            nature: nature,
            legalPerson: legalPerson,
            businessLicense: businessLicense,
            contact: contact,
            phone: phone,
            province: province,
            city: city,
            district: district,
            productionLicense: productionLicense,
            status: status,
            checkYear: checkYear,
            productName: productName
        };
        table.reload('companyTable', {
            url:'/biz/list'
            ,method : 'post'
            ,where: data
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })

});