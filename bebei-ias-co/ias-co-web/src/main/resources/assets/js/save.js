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
    var productTable = table.render({
        elem: '#products'
        ,title: '产品及产量'
        ,toolbar: '#productToolbar'
        ,defaultToolbar: []
        ,cols: [[
            {field:'productCategory', title:'产品类别', edit: 'text'}
            ,{field:'productName', title:'产品名称', edit: 'text'}
            ,{field:'executiveStandard', title:'产品标准', edit: 'text'}
            ,{field:'output', title:'产量（万吨）', edit: 'text'}
            ,{fixed: 'right', title:'操作', toolbar: '#productBar', width:70, unresize: true}
        ]]
        ,page: false
        ,id: 'productData'
    });
    //头工具栏事件
    table.on('toolbar(products)', function(obj){
        switch(obj.event){
            case 'addProduct':
                var data = obj.config.data;
                if (data === undefined) {
                    data = [];
                }
                data.push({productCategory:'',productName:'',executiveStandard:'',output:''});
                productTable.reload({
                    data: data
                });
                break;
        }
    });
    //监听行工具事件
    table.on('tool(products)', function(obj){
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
            });
        }
    });

    var equipmentTable = table.render({
        elem: '#equipments'
        ,title: '企业设备'
        ,defaultToolbar: []
        ,cols: [[
            {field:'type', title:'装备类型'}
            ,{field:'capacity', title:'产能（万吨）', edit: 'text'}
            ,{field:'output', title:'产量（万吨）', edit: 'text'}
        ]],
        data:[{type:'烧结设备',capacity:'',output:''}
        ,{type:'炼铁设备',capacity:'',output:''}
        ,{type:'炼钢设备',capacity:'',output:''}]
        ,page: false
        ,id: 'equipmentData'
    });
});