layui.use(['form', 'layedit', 'laydate', 'table'], function () {
    $('#left_menus').children().removeClass('layui-this');
    $('#list_menu').addClass('layui-this');

    var element = layui.element;
    var id = $('#id').val();

    $.ajax({
        url: '/biz/detail/' + id,
        type: 'get',
        dataType: 'json',
        success: function (resp) {
            if (resp.success) {
                initCompany(resp.data.company);
                initProducts(resp.data.products);
                initEquipments(resp.data.equipments);
            } else {
                layer.msg('查询失败', {
                    time: 2000, //2s后自动关闭
                    btn: ['确定']
                });
            }
        },
        error: function () {
            layer.msg('查询失败', {
                time: 2000, //2s后自动关闭
                btn: ['确定']
            });
        }
    });

    // table
    var table = layui.table;

    function initCompany(company) {
        $('#name').text(company.name);
        $('#nature').text(company.nature);
        $('#legalPerson').text(company.legalPerson);
        $('#businessLicense').text(company.businessLicense);
        $('#contact').text(company.contact);
        $('#phone').text(company.phone);
        $('#area').text(company.province + company.city + company.district);
        $('#address').text(company.address);
        $('#stuffNumber').text(company.stuffNumber);
        $('#lastYearProductionValue').text(company.lastYearProductionValue);
        $('#laboratoryLevel').text(company.laboratoryLevel);
        $('#authentication').text(company.authentication);
        $('#productionLicense').text(company.productionLicense);
        $('#status').text(company.status > 0 ? '停业' : '正常');
        $('#checkYear').text(company.checkYear);
    }

    function initProducts(products) {
       table.render({
            elem: '#products'
            , title: '产品及产量'
            , defaultToolbar: []
            , cols: [[
                {field: 'productCategory', title: '产品类别'}
                , {field: 'productName', title: '产品名称'}
                , {field: 'executiveStandard', title: '产品标准'}
                , {field: 'output', title: '产量（万吨）'}
            ]]
            , data: products
            , page: false
            , id: 'productData'
        });
    }

    function initEquipments(equipments) {
        table.render({
            elem: '#equipments'
            , title: '企业设备'
            , defaultToolbar: []
            , cols: [[
                {field: 'type', title: '装备类型'}
                , {field: 'capacity', title: '产能（万吨）'}
                , {field: 'output', title: '产量（万吨）'}
            ]]
            , data: equipments
            , page: false
            , id: 'equipmentData'
        });
    }

});