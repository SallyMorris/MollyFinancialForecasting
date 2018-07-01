$(document).ready( function () {
	$('#multipleSelect').multiSelect();
	$('#comparisonResults').hide();
	$('#loading').hide();
	$('#compare').hide();
	var data;
	var tickers;
	var idForStatementTable = 1;
	$("#multipleSelect").change(function() {
		tickers = $('#multipleSelect').val();
		if(typeof tickers !== 'undefined' && tickers.length > 0){
			$('#compare').show();
		}else{
			$('#compare').hide();
			$('#comparisonResults').hide();
		}
	});
	$("#compare").click(function(){
		$('#statementDataTable').empty();
		$('#compare').hide();
		$('#loading').show();
		var index;
		for (index = 0; index < tickers.length; index++) {
		var DateTable = '<table id="StatementTable' + idForStatementTable + '" class="stripe" style="width:100%"><thead><tr><th>Year</th><th>Operating Revenue</th><th>Total Revenue</th><th>Operating Cost Of Revenue</th><th>Total Cost Of Revenue</th><th>Total Gross Profit</th><th>Total Operating Expenses</th><th>Total Operating Income</th><th>Net Income</th><th>Cash And Equivalents</th><th>Note Receivable</th><th>Net Inventory</th><th>Net PPE</th><th>Total Assets</th><th>Leverage Ratio</th><th>Non Controlling Interest Sharing Ratio</th><th>Div Payout Ratio</th><th>Augmented Payout Ratio</th></tr></thead></table>';
		$('<h3>'+$('#multipleSelect option[value='+tickers[index]+']').text()+'</h3>').appendTo('#statementDataTable');
		$(DateTable).appendTo('#statementDataTable');
		$('<br>').appendTo('#statementDataTable');
		if(index!= tickers.length-1)
		{
			$('<hr style="height:1px;border:none;color:#333;background-color:#333;" />').appendTo('#statementDataTable');
		}
		$('<br>').appendTo('#statementDataTable');
	    $.ajaxSetup({
            async: false
        });
        $.getJSON('/api/statement', {ticker: tickers[index]}, function (list) {
            data = list;
        });
        $.ajaxSetup({
            async: true
        });
        var table = $('#StatementTable' + idForStatementTable + '').removeAttr('width').DataTable({
        	"scrollX":        true,
            "scrollCollapse": true,
            "data": data,
            "columns": [
                {"data": "year"},
                {"data": "operatingRevenue"},
                {"data": "totalRevenue"},
                {"data": "operatingCostOfRevenue"},
                {"data": "totalCostOfRevenue"},
                {"data": "totalGrossProfit"},
                {"data": "totalOperatingExpenses"},
                {"data": "totalOperatingIncome"},
                {"data": "netIncome"},
                {"data": "cashAndEquivalents"},
                {"data": "noteReceivable"},
                {"data": "netInventory"},
                {"data": "netPPE"},
                {"data": "totalAssets"},
                {"data": "leverageRatio"},
                {"data": "nonControllingInterestSharingRatio"},
                {"data": "divPayoutRatio"},
                {"data": "augmentedPayoutRatio"}
            ],
            dom: 'Bfrtip',
            buttons: [
                 { extend: 'excel', filename: tickers[index]+' Prices' ,title : tickers[index]+' Prices'}, { extend: 'pdf', filename: tickers[index]+' Prices' ,title :tickers[index]+' Prices'},{ extend: 'print', title :tickers[index]+' Prices'}
            ],
            "order": [[ 0, "desc" ]]
       });
	    idForStatementTable++;
		}
		$('#loading').hide();
		$('#compare').show();
		$('#comparisonResults').show();
	});
});
