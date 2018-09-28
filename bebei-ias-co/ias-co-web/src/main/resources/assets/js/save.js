layui.use(['form', 'layedit', 'laydate', 'table'], function () {

    $('#left_menus').children().removeClass('layui-this');
    $('#edit_menu').addClass('layui-this');

    var element = layui.element;
    //日期
    var laydate = layui.laydate;
    laydate.render({
        elem: '#date',
        type: 'year',
        btns: ['now', 'confirm']
    });
    // table
    var table = layui.table;

    var id = $('#id').val();
    if (id) {
        $.ajax({
            url: '/biz/detail/' + id,
            type: 'get',
            dataType: 'json',
            success: function (resp) {
                if (resp.success) {
                    initCompany(resp.data.company);
                    initProducts(resp.data.products);
                    initEquipments(resp.data.equipments);
                }
            },
            error: function () {
                layer.alert("保存失败");
            }
        });
    } else {
        $('#newSubmitBtn').hide();
        initProducts();
        initEquipments();
    }

    function initCompany(company) {
        var form = $('#companyForm');
        form.find('[name="name"]').val(company.name);
        initLayuiSelect(form.find('[name="nature"]'), company.nature);
        form.find('[name="legalPerson"]').val(company.legalPerson);
        form.find('[name="businessLicense"]').val(company.businessLicense);
        form.find('[name="contact"]').val(company.contact);
        form.find('[name="phone"]').val(company.phone);
        initLayuiSelect(form.find('[name="province"]'), company.province);
        initLayuiSelect(form.find('[name="city"]'), company.city);
        initLayuiSelect(form.find('[name="district"]'), company.district);

        form.find('[name="address"]').val(company.address);
        form.find('[name="stuffNumber"]').val(company.stuffNumber);
        form.find('[name="lastYearProductionValue"]').val(company.lastYearProductionValue);
        form.find('[name="laboratoryLevel"]').val(company.laboratoryLevel);
        form.find('[name="authentication"]').val(company.authentication);
        form.find('[name="productionLicense"]').val(company.productionLicense);
        initLayuiSelect(form.find('[name="status"]'), company.status);
        form.find('[name="checkYear"]').val(company.checkYear);
    }

    function initLayuiSelect(dom, value) {
        var select = 'dd[lay-value=' + value + ']';
        dom.siblings("div.layui-form-select").find('dl').find(select).click();
    }

    function initProducts(products) {
        if (products === undefined || products === null) {
            products = [];
        } else {
            for (x in products) {
                products[x].LAY_CHECKED = true;
            }
        }
        table.render({
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
            , data: products
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
                    table.reload('productData', {
                        data: data
                    });
                    break;
            }
        });
        //监听行工具事件
        table.on('tool(products)', function (obj) {
            if (obj.event === 'del') {
                layer.confirm('真的删除么？', function (index) {
                    obj.del();
                    layer.close(index);
                });
            }
        });
    }

    function initEquipments(equipments) {
        if (equipments === undefined || equipments === null) {
            equipments = [{type: '烧结设备', capacity: '', output: '', LAY_CHECKED: true}
                , {type: '炼铁设备', capacity: '', output: '', LAY_CHECKED: true}
                , {type: '炼钢设备', capacity: '', output: '', LAY_CHECKED: true}];
        } else {
            for (x in equipments) {
                equipments[x].LAY_CHECKED = true;
            }
        }
        table.render({
            elem: '#equipments'
            , title: '企业设备'
            , defaultToolbar: []
            , cols: [[
                {field: 'type', title: '装备类型'}
                , {field: 'capacity', title: '产能（万吨）', edit: 'text'}
                , {field: 'output', title: '产量（万吨）', edit: 'text'}
            ]],
            data: equipments
            , page: false
            , id: 'equipmentData'
        });
    }

    $('.save-btn-class').on('click', function () {
        var form = $('#companyForm');

        var _id = id;
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

        if($(this).attr('id') === 'newSubmitBtn') {
            _id = null;
        }

        var data = {
            company: {
                id: _id,
                name: name,
                nature: nature,
                legalPerson: legalPerson,
                businessLicense: businessLicense,
                contact: contact,
                phone: phone,
                province: province,
                city: city,
                district: district,
                address: address,
                stuffNumber: stuffNumber,
                lastYearProductionValue: lastYearProductionValue,
                laboratoryLevel: laboratoryLevel,
                authentication: authentication,
                productionLicense: productionLicense,
                status: status,
                checkYear: checkYear
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
            success: function (resp) {
                if (resp.success) {
                    window.location = '/list';
                } else {
                    layer.alert("保存失败");
                }
            },
            error: function () {
                layer.alert("保存失败");
            }
        })
    })

});