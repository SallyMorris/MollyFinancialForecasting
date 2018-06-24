$(document).ready( function () {

    var table = $('#datatable').DataTable({
        "sAjaxSource": "/api/portsamplings",
        "sAjaxDataProp": "",
        "order": [[ 1, "asc" ]],
        "aoColumns": [
            { "mData": "id"},
            { "mData": "id"},
            { "mData": "portCode" },
            { "mData": "vesselCode" },
            { "mData": "status" },
            { "mData": "created" },
            // { "mData": "modified"},
            {}
        ],
        "columnDefs": [
            {
                "targets": 0,
                "searchable": false,
                "orderable": false,
                "width": "1%",
                "className": "dt-body-center",
                "render": function (data, type, full, meta){
                    return '<input type="checkbox" value="'+data+'" name="ids[]" class="sel">';
                }
            },
            {
                "targets": 5,
                "render": function (data, type, full, meta){
                    return moment(data).format('YYYY-MM-DD hh:mm A');
                }
            },
            {
                "targets": 6,
                "searchable": false,
                "orderable": false,
                "className": "dt-body-center",
                "render": function (data, type, full, meta){
                    return '<div class="ui icon buttons"><button class="ui button btn-transparent editor-edit"><i class="fas fa-pencil-alt orange"></i></button>\n' +
                        '<button class="ui button btn-transparent editor-delete"><i class="fas fa-trash red"></i></button></div>\n';
                }
            }

        ],
        select: {
            style:    'os',
            selector: 'td:first-child'
        }
    })

    // Edit a record
    $('#datatable').on('click', 'button.editor-edit', function (e) {
        e.preventDefault();

        var id = $(this).closest("tr").find(".sel").val();
        location.href = "/portsampling/wizard/"+id;
    });

    // Delete a record
    $('#datatable').on('click', 'button.editor-delete', function (e) {
        e.preventDefault();

        var id = $(this).closest("tr").find(".sel").val();
        var title = $(this).closest("tr").children("td").eq(2).html();

        $('#modal_delete_item_text_placeholder').html(title);
        $('#button-modal-delete-item')
            .api({
                action : 'del portsampling',
                method : 'DELETE',
                // Substituted into URL
                urlData: {
                    id: id
                },
                onSuccess    : function(response) {
                    //alert('success');
                    $("#message_delete_item_error").hide();
                    $("#message_delete_item_successful").show();
                    setTimeout(function () {
                        $("#message_delete_item_successful").hide();
                    }, 10000)
                    $("#modal_delete_item").modal('hide');
                    $('#datatable').DataTable().ajax.reload();
                    console.log(response);
                },
                onFailure    : function(response) {
                    $("#message_delete_item_error").show();
                    $("#message_delete_item_successful").hide();
                    setTimeout(function () {
                        $("#message_delete_item_error").hide();
                    }, 10000)
                    $("#modal_delete_item").modal('hide');
                    $('#datatable').DataTable().ajax.reload();
                    console.log(response);
                }
            })
        ;

        $('#modal_delete_item')
            .modal('show', id, title);

    });

});