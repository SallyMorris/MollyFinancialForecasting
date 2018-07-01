$(document).ready( function () {
	$("#Forecasting").hide();
	$("#forecastingResults").hide();
	var dayName = getTodayName();
	var data;
	var result;
	var ticker;
	$("#forecasting-question").html('what is the probability that today’s price will increase given that today is '+dayName+' ?');
	var idForPriceTable = 1;
	$( '#ticker' ).keypress(function( event ) {
	  if ( event.which == 13 ) {
		ticker = $('#ticker').val();
		var DateTable = '<table id="PriceTable' + idForPriceTable + '" class="display nowrap" style="width:100%"><thead><tr><th>Date</th><th>Open</th><th>High</th><th>Low</th><th>Close</th><th>Day Name</th><th>Price Direction</th></tr></thead></table>';
		$('#priceDataTable').empty();
		$(DateTable).appendTo('#priceDataTable');
	    
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
	    idForPriceTable++;
	    $("#Forecasting").show();
	    
	  }
	});
	$('#forecasting-question').on("click",function(){
		$("#forecastingResults").show();
		
		 $.ajaxSetup({
	            async: false
        });
		$.getJSON('/api/priceForecasting', {ticker: ticker,dayName: dayName}, function (list) {
			result = list;
        });
		$.ajaxSetup({
            async: true
        });
		console.log(result);
		$("#UPresult").html(result[0]);
		$("#DOWNresult").html(result[1]);
	});
});
function getTodayName() {
	var d = new Date();
	var weekday = new Array(7);
    weekday[0] = "Sunday";
    weekday[1] = "Monday";
    weekday[2] = "Tuesday";
    weekday[3] = "Wednesday";
    weekday[4] = "Thursday";
    weekday[5] = "Friday";
    weekday[6] = "Saturday";
    return weekday[d.getDay()];
}