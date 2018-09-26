layui.use(['form', 'layedit', 'laydate', 'table'], function () {
    var element = layui.element;
    //日期
    var laydate = layui.laydate;
    laydate.render({
        elem: '#date',
        type: 'year'
    });
    // table
    var table = layui.table;
    var productArr = [];
    var productTable = table.render({
        elem: '#products'
        , title: '产品及产量'
        , toolbar: '#productToolbar'
        , defaultToolbar: []
        , cols: [[
            {field: 'productCategory', title: '产品类别', edit: 'text'}
            , {field: 'productName', title: '产品名称', edit: 'text'}
            , {field: 'executiveStandard', title: '产品标准', edit: 'text'}
            , {field: 'output', title: '产量（万吨）', edit: 'text'}
            , {fixed: 'right', title: '操作', toolbar: '#productBar', width: 70, unresize: true}
        ]]
        , page: false
        , id: 'productData'
    });
    //头工具栏事件
    table.on('toolbar(products)', function (obj) {
        switch (obj.event) {
            case 'addProduct':
                var data = obj.config.data;
                if (data === undefined) {
                    data = [];
                }
                data.push({productCategory: '', productName: '', executiveStandard: '', output: '', LAY_CHECKED: true});
                productTable.reload({
                    data: data
                });
                break;
        }
    });
    //监听行工具事件
    table.on('tool(products)', function (obj) {
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                layer.close(index);
            });
        }
    });
    var equipmentTable = table.render({
        elem: '#equipments'
        , title: '企业设备'
        , defaultToolbar: []
        , cols: [[
            {field: 'type', title: '装备类型'}
            , {field: 'capacity', title: '产能（万吨）', edit: 'text'}
            , {field: 'output', title: '产量（万吨）', edit: 'text'}
        ]],
        data: [{type: '烧结设备', capacity: '', output: '', LAY_CHECKED: true}
            , {type: '炼铁设备', capacity: '', output: '', LAY_CHECKED: true}
            , {type: '炼钢设备', capacity: '', output: '', LAY_CHECKED: true}]
        , page: false
        , id: 'equipmentData'
    });

    $('#submitBtn').on('click', function () {
        var form = $('#companyForm');
        var name = form.find('[name="name"]').val();
        var nature = form.find('[name="nature"]').val();
        var legalPerson = form.find('[name="legalPerson"]').val();
        var businessLicense = form.find('[name="businessLicense"]').val();
        var contact = form.find('[name="contact"]').val();
        var phone = form.find('[name="phone"]').val();
        var province = form.find('[name="province"]').val();
        var city = form.find('[name="city"]').val();
        var district = form.find('[name="district"]').val();
        var address = form.find('[name="address"]').val();

        var products = table.checkStatus('productData').data;
        var equipments = table.checkStatus('equipmentData').data;

        var stuffNumber = form.find('[name="stuffNumber"]').val();
        var lastYearProductionValue = form.find('[name="lastYearProductionValue"]').val();
        var laboratoryLevel = form.find('[name="laboratoryLevel"]').val();
        var authentication = form.find('[name="authentication"]').val();
        var productionLicense = form.find('[name="productionLicense"]').val();
        var status = form.find('[name="status"]').val();
        var checkYear = form.find('[name="checkYear"]').val();

        var data = {
            company: {
                name: name,
                nature: nature,
                legalPerson: legalPerson,
                businessLicense: businessLicense,
                contact: contact,
                phone: phone,
                area: province + city + district,
                address: address,
                stuffNumber: stuffNumber,
                lastYearProductionValue: lastYearProductionValue,
                laboratoryLevel: laboratoryLevel,
                authentication: authentication,
                productionLicense: productionLicense,
                status: status,
                checkYear: checkYear,
            },
            products: products,
            equipments: equipments
        };

        $.ajax({
            url: '/biz/save',
            type: 'post',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: 'json',
            success: function () {
                layer.alert("保存成功");
            },
            error: function () {
                layer.alert("保存失败");
            }
        })
    })

});