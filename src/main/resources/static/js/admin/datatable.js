$(document).ready( function () {
	$.ajaxSetup({
        async: false
    });
    $.getJSON('/api/price', {ticker: ticker}, function (list) {
        data = list;
    });
    $.ajaxSetup({
        async: true
    });
    var table = $('#PriceTable' + idForPriceTable + '').DataTable({
    	"bInfo": false,
        "bPaginate": false,
        "data": data,
        "columns": [
            {"data": "date"},
            {"data": "open"},
            {"data": "high"},
            {"data": "low"},
            {"data": "close"},
            {"data": "dayName"},
            {"data": "priceDirection"}
        ],
        dom: 'Bfrtip',
        buttons: [
             { extend: 'excel', filename: ticker+' Prices' ,title : ticker+' Prices'}, { extend: 'pdf', filename: ticker+' Prices' ,title :ticker+' Prices'},{ extend: 'print', title :ticker+' Prices'}
        ],
        "order": [[ 0, "desc" ]]
   });

});
